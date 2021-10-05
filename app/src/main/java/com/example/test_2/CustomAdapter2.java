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

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder> {

    Context context;
    ArrayList V_id, V_vaccinne, V_date; //V_vetname;

    CustomAdapter2(Context context, ArrayList V_id, ArrayList V_vaccinne,
                  ArrayList V_date){
        this.context = context;
        this.V_id = V_id;
        this.V_vaccinne = V_vaccinne;
        this.V_date = V_date;
        //this.V_vetname = V_vetname;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_my_row2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.V_id_txt.setText(String.valueOf((V_id.get(position))) );
        holder.V_vaccinne_txt.setText(String.valueOf((V_vaccinne.get(position))) );
        holder.V_date_txt.setText(String.valueOf((V_date.get(position))) );
        //holder.V_vetname_txt.setText(String.valueOf((V_vetname.get(position))) );
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DailySchEdit.class);
                intent.putExtra("vid", String.valueOf(V_id.get(position)));
                intent.putExtra("vaccine", String.valueOf(V_vaccinne.get(position)));
                intent.putExtra("vdate", String.valueOf(V_date.get(position)));
                //intent.putExtra("sergeon", String.valueOf(V_vetname.get(position)));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return V_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView V_id_txt, V_vaccinne_txt, V_date_txt;


        LinearLayout mainLayout;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            V_id_txt = itemView.findViewById(R.id.V_id_txt);
            V_vaccinne_txt = itemView.findViewById(R.id.V_vaccinne_txt);
            V_date_txt = itemView.findViewById(R.id.V_date_txt);
            //V_vetname_txt = itemView.findViewById(R.id.V_vetname_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);

        }
    }
}