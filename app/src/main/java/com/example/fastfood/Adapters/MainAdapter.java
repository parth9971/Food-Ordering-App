package com.example.fastfood.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastfood.Models.MainModel;
import com.example.fastfood.R;
import com.example.fastfood.detailActivity;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.viewholder> {

    ArrayList<MainModel> list;
    Context context;

    public MainAdapter(ArrayList<MainModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_food,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {

        final MainModel mainModel = list.get(position);
        holder.foodImage.setImageResource(mainModel.getImage());
        holder.mainName.setText(mainModel.getName());
        holder.price.setText(mainModel.getPrice());
        holder.description.setText(mainModel.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, detailActivity.class);
                intent.putExtra("image",mainModel.getImage());
                intent.putExtra("name",mainModel.getName());
                intent.putExtra("des",mainModel.getDescription());
                intent.putExtra("price",mainModel.getPrice());
                intent.putExtra("type",1);
               context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        ImageView foodImage;
        TextView mainName , price , description;


        public viewholder(@NonNull View itemView) {
            super(itemView);

            foodImage = itemView.findViewById(R.id.imageView);
            mainName = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.orderPricenumber);
            description=itemView.findViewById(R.id.description);
        }
    }

}
