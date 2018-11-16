package com.sabertooth.app_11recyclerviewpractice;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;


class MyViewHolder_1 extends RecyclerView.ViewHolder {
    ImageView img;
    TextView tv;
    LinearLayout details_show_layout;
    public MyViewHolder_1(@NonNull View itemView) {
        super(itemView);
        details_show_layout=itemView.findViewById(R.id.player_details_layout);
        img=itemView.findViewById(R.id.image_view_list_1);
        tv=itemView.findViewById(R.id.text_view_list_1);
    }
}
public class MyAdapater1 extends RecyclerView.Adapter<MyViewHolder_1> {
    Context ctx;
    static ArrayList<packData>tmp_data;
    public MyAdapater1(Context contxt,ArrayList<packData>tmp){
        this.ctx=contxt;
        tmp_data=tmp;
    }
    @NonNull
    @Override
    public MyViewHolder_1 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflat=LayoutInflater.from(ctx).inflate(R.layout.layout_for_list_1,viewGroup,false);
        return new MyViewHolder_1(inflat);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder_1 myViewHolder_1, final int i) {
        myViewHolder_1.img.setImageBitmap(tmp_data.get(i).profilePic);
        myViewHolder_1.tv.setText(tmp_data.get(i).name_);
        myViewHolder_1.details_show_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent show_=new Intent(ctx,PlayerDetails.class);
                show_.putExtra("Index", i);
                ctx.startActivity(show_);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tmp_data.size();
    }
}
