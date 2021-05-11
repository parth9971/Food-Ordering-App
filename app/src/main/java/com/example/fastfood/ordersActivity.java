package com.example.fastfood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.fastfood.Adapters.orderAdapter;
import com.example.fastfood.Models.ordersModel;
import com.example.fastfood.databinding.ActivityOrdersBinding;

import java.util.ArrayList;

public class ordersActivity extends AppCompatActivity {

    ActivityOrdersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        DBHelper helper = new DBHelper(this);
        ArrayList<ordersModel> list = helper.getOrders();
        orderAdapter orderAdapter = new orderAdapter(list,this);


        binding.orderRecycleView.setAdapter(orderAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.orderRecycleView.setLayoutManager(layoutManager);

    }
}