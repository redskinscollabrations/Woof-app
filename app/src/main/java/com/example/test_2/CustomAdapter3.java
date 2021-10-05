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

public class CustomAdapter3 extends RecyclerView.Adapter<CustomAdapter3.MyViewHolder> {

    Context context;
    ArrayList vac_id, vac_task, vac_date;

    CustomAdapter3(Context context, ArrayList vac_id, ArrayList vac_task, ArrayList vac_date){
        this.context = context;
        this.vac_id = vac_id;
        this.vac_task = vac_task;
        this.vac_date = vac_date;
    }

    @NonNull
    @Override
    public CustomAdapter3.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_my_row2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter3.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.V_id_txt.setText(String.valueOf((vac_id.get(position))));
        holder.V_vaccinne_txt.setText(String.valueOf((vac_task.get(position))));
        holder.V_date_txt.setText(String.valueOf((vac_date.get(position))));
        holder.mainLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VaccinneEdit.class);
                intent.putExtra("vid", String.valueOf(vac_id.get(position)));
                intent.putExtra("vtask", String.valueOf(vac_task.get(position)));
                intent.putExtra("vdate", String.valueOf(vac_date.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vac_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView V_id_txt, V_vaccinne_txt, V_date_txt;
        LinearLayout mainLayout2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            V_id_txt = itemView.findViewById(R.id.V_id_txt);
            V_vaccinne_txt = itemView.findViewById(R.id.V_vaccinne_txt);
            V_date_txt = itemView.findViewById(R.id.V_date_txt);

            mainLayout2 = itemView.findViewById(R.id.mainLayout2);
        }
    }
}
