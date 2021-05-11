package com.example.fastfood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.fastfood.Adapters.MainAdapter;
import com.example.fastfood.Models.MainModel;
import com.example.fastfood.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModel> list  = new ArrayList<>();
        list.add(new MainModel(R.drawable.burger ,"Burger","10","extra chess burger"));
        list.add(new MainModel(R.drawable.burger ,"pizza","12","extra chess pizza"));
        list.add(new MainModel(R.drawable.burger ,"chicken","15","extra chess chicken"));
        list.add(new MainModel(R.drawable.burger ,"egg","5","extra chess egg"));
        list.add(new MainModel(R.drawable.burger ,"rice","14","extra chess rice"));

        MainAdapter adapter = new MainAdapter(list,this);
        binding.recycleView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recycleView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this,ordersActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}