package com.example.main_app;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class myadapter extends FirebaseRecyclerAdapter<model,myadapter.myViewholder> {

    public myadapter(@NonNull FirebaseRecyclerOptions<model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final myViewholder holder, @SuppressLint("RecyclerView") final int position, @NonNull final model model) {

        holder.vetname.setText(model.getVetname());
        holder.vet_type.setText(model.getVet_type());
        holder.address.setText(model.getAddress());
        holder.city.setText(model.getCity());
        holder.mobile.setText(model.getMobile());
        //Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogPlus dialogPlus=DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.dialogcontent))
                        .setExpanded(true,1100)
                        .create();

                //dialogPlus.show();
                View myview=dialogPlus.getHolderView();
                final EditText vetname=myview.findViewById(R.id.etxt_vetname);
                final EditText vet_type=myview.findViewById(R.id.etxt_vettype);
                final EditText address=myview.findViewById(R.id.etxt_address);
                final EditText city=myview.findViewById(R.id.etxt_city);
                final EditText mobile=myview.findViewById(R.id.etxt_mobile);
                //final EditText purl=myview.findViewById(R.id.etxt_purl);
                Button submit=myview.findViewById(R.id.usubmit);

                vetname.setText(model.getVetname());
                vet_type.setText(model.getVet_type());
                address.setText(model.getAddress());
                city.setText(model.getCity());
                mobile.setText(model.getMobile());
                //purl.setText(model.getPurl());

                dialogPlus.show();

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String,Object> map=new HashMap<>();
                        map.put("vetname",vetname.getText().toString());
                        map.put("vet_type",vet_type.getText().toString());
                        map.put("address",address.getText().toString());
                        map.put("city",city.getText().toString());
                        map.put("mobile",mobile.getText().toString());
                        //map.put("purl",purl.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Vet")
                                .child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        dialogPlus.dismiss();

                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        dialogPlus.dismiss();

                                    }
                                });


                    }
                });



            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(holder.img.getContext());
                builder.setTitle("Delete Panel");
                builder.setMessage("Delete...");


                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Vet")
                                .child(getRef(position).getKey()).removeValue();

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();

            }
        });

    } //End of OnBindViewMethod.

    @NonNull
    @Override
    public myViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myViewholder(view);
    }

    class myViewholder extends RecyclerView.ViewHolder{

        CircleImageView img;
        ImageView edit, delete;
        TextView vetname, vet_type, address, city, mobile, purl;
        public myViewholder(@NonNull View itemView) {

            super(itemView);
            img=(CircleImageView) itemView.findViewById(R.id.img1);
            vetname=(TextView)itemView.findViewById(R.id.txt_vetname);
            vet_type=(TextView)itemView.findViewById(R.id.txt_vettype);
            address=(TextView)itemView.findViewById(R.id.txt_address);
            city=(TextView)itemView.findViewById(R.id.txt_city);
            mobile=(TextView)itemView.findViewById(R.id.txt_mobile);

            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
        }
    }
}
