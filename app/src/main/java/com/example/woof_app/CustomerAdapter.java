package com.example.woof_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class CustomerAdapter extends RecyclerView .Adapter<CustomerAdapter.MyViewHolder>{

    private Context context;
    Activity activity;
    private ArrayList menu_id, menu_name, menu_amount;



    CustomerAdapter(Activity activity, Context context, ArrayList menu_id, ArrayList menu_name, ArrayList menu_amount){
        this.activity = activity;
        this.context = context;
        this.menu_id = menu_id;
        this.menu_name = menu_name;
        this.menu_amount = menu_amount;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_my_row, parent,false);
        return new MyViewHolder(view);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.menu_id_txt.setText(String.valueOf(menu_id.get(position)));
        holder.menu_name_txt.setText(String.valueOf(menu_name.get(position)));
        holder.menu_amount_txt.setText(String.valueOf(menu_amount.get(position)));
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateMenu.class);
                intent.putExtra("id", String.valueOf(menu_id.get(position)));
                intent.putExtra("menu", String.valueOf(menu_name.get(position)));
                intent.putExtra("amount", String.valueOf(menu_amount.get(position)));
                activity.startActivityForResult(intent, 1 );

            }
        });
    }

    @Override
    public int getItemCount() {

        return menu_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView menu_id_txt, menu_name_txt, menu_amount_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            menu_id_txt = itemView.findViewById(R.id.menu_id_txt);
            menu_name_txt = itemView.findViewById(R.id.menu_name_txt);
            menu_amount_txt = itemView.findViewById(R.id.menu_amount_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
