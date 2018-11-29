package com.sabertooth.app_13firebaselogin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.IOException;

//tr picasso koi?
public class ImageDetailsActivity extends AppCompatActivity {
    private static final int REQ_CODE = 1;
    String image_url;
    ImageView imageView;
    Uri imageUri;
    EditText et_name;
    TextView tv;
    Button bt_update, bt_logout;
    ProgressBar pgb;
    FirebaseAuth mauth;

    @Override
    protected void onStart() {
        super.onStart();
        if (mauth.getCurrentUser() == null) {
            finish();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        tv = findViewById(R.id.text_view_user_name);
        et_name = findViewById(R.id.edit_text_user_name);
        bt_update = findViewById(R.id.button_upload_name_image);
        bt_logout = findViewById(R.id.button_logoout_user);
        imageView = findViewById(R.id.image_user_view);
        pgb = findViewById(R.id.progress_bar_image_upload);

        mauth = FirebaseAuth.getInstance();

        loadUserInformation();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showImageChooser();
            }
        });
        bt_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update_user();
            }
        });
        bt_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mauth.signOut();
                finish();
                Intent intent = new Intent(ImageDetailsActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadUserInformation() {
        FirebaseUser user = mauth.getCurrentUser();
        if (user != null && user.getPhotoUrl() != null && user.getDisplayName() != null) {
            String photo = user.getPhotoUrl().toString();
            Log.d("LOG_PH_1", photo);
            String displayName = user.getDisplayName();
            if (!photo.isEmpty()) {
                Picasso.get().setLoggingEnabled(true);
                Picasso.get().load(photo).into(imageView);
                tv.setText(displayName);
                tv.setVisibility(View.VISIBLE);
            }
        }
    }

    private void update_user() {
        pgb.setVisibility(View.VISIBLE);
        final String DisplayName = et_name.getText().toString();
        if (DisplayName.isEmpty()) {
            Toast.makeText(ImageDetailsActivity.this, "Name Shouldn't be Empty", Toast.LENGTH_LONG).show();
            return;
        }
        FirebaseUser user = mauth.getCurrentUser();
        if (user != null && image_url != null) {
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                    .setDisplayName(DisplayName)
                    .setPhotoUri(Uri.parse(image_url))
                    .build();
            Log.d("LOG_PH2", Uri.parse(image_url).toString());
            Log.d("LOG_PH3", image_url);
            user.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        tv.setText(DisplayName);
                        pgb.setVisibility(View.GONE);
                        Toast.makeText(ImageDetailsActivity.this, "Updated", Toast.LENGTH_LONG).show();
                        et_name.setText(null);
                    }
                }
            });
        }
    }

    private void showImageChooser() {
        Intent intt = new Intent();
        intt.setType("image/*");
        intt.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intt, "Select Picture"), REQ_CODE);
    }

    @Override
    protected void onActivityResult(int req_code, int res_code, Intent data) {
        super.onActivityResult(req_code, res_code, data);
        if (req_code == REQ_CODE && res_code == RESULT_OK && null != data) {
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                //Bitmap result=Bitmap.createScaledBitmap(bitmap,200,200,false);
                //           imageUri=getImageUri(getApplicationContext(),result);
                imageView.setImageBitmap(bitmap);
                uploadImageInFirebaseStorage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImageInFirebaseStorage() {
        StorageReference profileImageRef =
                FirebaseStorage.getInstance().getReference("profilepics/" + System.currentTimeMillis() + ".jpg");
        if (imageUri != null) {
            Log.d("LOG_PH4", imageUri.toString());
            pgb.setVisibility(View.VISIBLE);
            final StorageReference photoStorageReference = profileImageRef.child("profilepics/" + System.currentTimeMillis() + ".jpg");
            photoStorageReference.putFile(imageUri).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()) {
                        throw task.getException();
                    }
                    return photoStorageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    pgb.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        Uri downloadUri = task.getResult();
                        image_url=downloadUri.toString();
                        Log.d("WHAT",image_url);
                    } else {
                        Toast.makeText(getApplicationContext(), "upload failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
