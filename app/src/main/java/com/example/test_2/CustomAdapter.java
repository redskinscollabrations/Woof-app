package com.example.test_2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context context;
    ArrayList Daily_id, Daily_task, Daily_date;

    CustomAdapter(Context context, ArrayList Daily_id, ArrayList Daily_task,
                  ArrayList Daily_date){
        this.context = context;
        this.Daily_id = Daily_id;
        this.Daily_task = Daily_task;
        this.Daily_date = Daily_date;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.daily_id_txt.setText(String.valueOf((Daily_id.get(position))) );
        holder.daily_task_txt.setText(String.valueOf((Daily_task.get(position))) );
        holder.daily_date_txt.setText(String.valueOf((Daily_date.get(position))) );
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DailySchEdit.class);
                intent.putExtra("id", String.valueOf(Daily_id.get(position)));
                intent.putExtra("task", String.valueOf(Daily_task.get(position)));
                intent.putExtra("date", String.valueOf(Daily_date.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Daily_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView daily_id_txt, daily_task_txt, daily_date_txt;


        LinearLayout mainLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            daily_id_txt = itemView.findViewById(R.id.daily_id_txt);
            daily_task_txt = itemView.findViewById(R.id.daily_task_txt);
            daily_date_txt = itemView.findViewById(R.id.daily_date_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}
