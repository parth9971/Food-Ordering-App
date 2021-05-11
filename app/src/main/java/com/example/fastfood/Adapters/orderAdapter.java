package com.example.fastfood.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfood.DBHelper;
import com.example.fastfood.Models.ordersModel;
import com.example.fastfood.R;
import com.example.fastfood.detailActivity;

import java.util.ArrayList;

public class orderAdapter extends RecyclerView.Adapter<orderAdapter.viewholder> {

    ArrayList<ordersModel> list;
    Context context;

    public orderAdapter(ArrayList<ordersModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_sample, parent, false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final ordersModel ordersModel = list.get(position);
        holder.orderImage.setImageResource(ordersModel.getOrderImage());
        holder.soldItemName.setText(ordersModel.getSoldItemName());
        holder.orderNumber.setText(ordersModel.getOrderName());
        holder.price.setText(ordersModel.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, detailActivity.class);
                intent.putExtra("id",Integer.parseInt(ordersModel.getOrderName()));
                intent.putExtra("type",2);

                context.startActivity(intent);

            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete item")
                        .setMessage("Are you want to delete this item")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DBHelper helper = new DBHelper(context);
                                if (helper.deletorder(ordersModel.getOrderName())>0){
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();






                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        ImageView orderImage;
        TextView soldItemName, orderNumber, price;

        public viewholder(@NonNull View itemView) {
            super(itemView);
            orderImage = itemView.findViewById(R.id.orderimage);
            soldItemName = itemView.findViewById(R.id.orderIdNAme);
            orderNumber = itemView.findViewById(R.id.orderNumber);
            price = itemView.findViewById(R.id.orderPricenumber);
        }
    }


}
