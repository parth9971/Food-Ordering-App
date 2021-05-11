package com.example.fastfood;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.fastfood.databinding.ActivityDetailBinding;

public class detailActivity extends AppCompatActivity {

    ActivityDetailBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        DBHelper helper = new DBHelper(this);
        if (getIntent().getIntExtra("type", 0) == 1) {

            final int image = getIntent().getIntExtra("image", 0);
            int price = Integer.parseInt(getIntent().getStringExtra("price"));
            String name = getIntent().getStringExtra("name");
            String des = getIntent().getStringExtra("des");

            binding.detailImage.setImageResource(image);
            binding.finalprice.setText(String.valueOf(price));
            binding.detailname.setText(name);
            binding.detailDescrption.setText(des);




            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = helper.insertOrder(binding.namebox.getText().toString(),
                            binding.phonebox.getText().toString(),
                            price,
                            image,
                            des,
                            name,
                            Integer.parseInt(binding.quantity.getText().toString())
                    );
                    if (isInserted)
                        Toast.makeText(detailActivity.this, "data success", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(detailActivity.this, "not success", Toast.LENGTH_SHORT).show();

                }
            });

        }
        else {
                 int id =getIntent().getIntExtra("id",0);
            Cursor cursor = helper.getOrderById(id);
            int image =cursor.getInt(4);
            binding.detailImage.setImageResource(image);
            binding.finalprice.setText(String.valueOf(cursor.getInt(3)));
            binding.detailname.setText(cursor.getString(7));
            binding.detailDescrption.setText(cursor.getString(6));
            binding.namebox.setText(cursor.getString(1));
            binding.phonebox.setText(cursor.getString(2));
            binding.insertButton.setText("Update Now");
            binding.insertButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
             boolean isUpdated =  helper.updateorder(binding.namebox.getText().toString(),
                            binding.phonebox.getText().toString(),
                            Integer.parseInt(binding.finalprice.getText().toString()),
                            image,
                            binding.detailDescrption.getText().toString(),
                            binding.detailname.getText().toString(),
                            1,
                            id



                            );
             if (isUpdated)
                 Toast.makeText(detailActivity.this, "Updated", Toast.LENGTH_SHORT).show();
             else
                 Toast.makeText(detailActivity.this, "Not Updated", Toast.LENGTH_SHORT).show();

                 }
            });



        }
    }
}