package com.sabertooth.bittobyte_workshop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

class MyViewHolder extends RecyclerView.ViewHolder{
    TextView tv;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        tv=itemView.findViewById(R.id.Text_view_1);
    }
}
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context contxt;

    public MyAdapter(Context contxt) {
        this.contxt = contxt;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflat=LayoutInflater.from(contxt).inflate(R.layout.show_list,viewGroup,false);
        return  new MyViewHolder(inflat);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.tv.setText("hello World "+i);
    }

    @Override
    public int getItemCount() {
        return 6;
    }
}
