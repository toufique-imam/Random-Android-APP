package com.sabertooth.app_11recyclerviewpractice;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

class MyViewHolder_1 extends RecyclerView.ViewHolder{
    ImageView img;
    TextView tv;
    public MyViewHolder_1(@NonNull View itemView) {
        super(itemView);
        img=itemView.findViewById(R.id.image_view_list_1);
        tv=itemView.findViewById(R.id.text_view_list_1);
    }
}
public class MyAdapater1 extends RecyclerView.Adapter<MyViewHolder_1> {
    Context ctx;
    ArrayList<packData>tmp_data;
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
    public void onBindViewHolder(@NonNull MyViewHolder_1 myViewHolder_1, int i) {
        myViewHolder_1.img.setImageBitmap(tmp_data.get(i).profilePic);
        myViewHolder_1.tv.setText(tmp_data.get(i).name_);
    }

    @Override
    public int getItemCount() {
        return tmp_data.size();
    }
}
