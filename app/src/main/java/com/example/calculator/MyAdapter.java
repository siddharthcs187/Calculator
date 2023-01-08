package com.example.calculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private ArrayList calc;

    public MyAdapter(Context context, ArrayList calc) {
        this.context = context;
        this.calc = calc;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.calcentry, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.calc.setText(String.valueOf(calc.get(position)));
    }

    @Override
    public int getItemCount() {
        return calc.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView calc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            calc=itemView.findViewById(R.id.calcview);
        }
    }
}
