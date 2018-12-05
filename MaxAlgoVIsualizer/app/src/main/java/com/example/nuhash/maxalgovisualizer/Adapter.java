package com.example.nuhash.maxalgovisualizer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuhash.maxalgovisualizer.Main2Activity.*;

import java.util.ArrayList;

import static android.view.View.GONE;
import static com.example.nuhash.maxalgovisualizer.Main2Activity.graph_data;

class viewHolder extends RecyclerView.ViewHolder {
    EditText from, to, cap;
    Button input;

    public viewHolder(@NonNull View itemView) {
        super(itemView);
        from = itemView.findViewById(R.id.in_from);
        to = itemView.findViewById(R.id.in_to);
        cap = itemView.findViewById(R.id.in_cap);
        input = itemView.findViewById(R.id.in_button);
    }
}

public class Adapter extends RecyclerView.Adapter<viewHolder> {
    int cnt;
    boolean fla;
    Context context;

    Adapter(Context ctx, int m, boolean mark) {
        context = ctx;
        cnt = m;
        fla = mark;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflat = LayoutInflater.from(context).inflate(R.layout.in_edges, viewGroup, false);
        return new viewHolder(inflat);
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder viewHolder, int i) {
        if (!fla) viewHolder.cap.setVisibility(GONE);
        viewHolder.input.setOnClickListener(new View.OnClickListener() {
            Integer fx, tr, ca;
            @Override
            public void onClick(View v) {
                String s;
                try {
                    s = viewHolder.from.getText().toString();
                    fx = Integer.valueOf(s);
                    s = viewHolder.to.getText().toString();
                    tr = Integer.valueOf(s);
                    if (fla) {
                        s = viewHolder.cap.getText().toString();
                        ca = Integer.valueOf(s);
                    } else {
                        ca = 1;
                    }
                    Node_cls tmp = new Node_cls();
                    tmp.cap = ca;
                    tmp.from = fx;
                    tmp.to = tr;
                    graph_data.add(tmp);
                    viewHolder.input.setVisibility(GONE);
                    viewHolder.cap.setInputType(0);
                    viewHolder.from.setInputType(0);
                    viewHolder.to.setInputType(0);
                } catch (Exception e) {
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return cnt;
    }
}
