package com.example.fastfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.fastfood.Models.ordersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBNAME = "mydatabase.db";
    final static int DBVERSION = 2;

    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(
                "create table orders " +
                        "(id integer primary key autoincrement," +
                        "name text," +
                        "phone text," +
                        "price int," +
                        "img int," +
                        "quantity int," +
                        "description text," +
                        "foodname text)"
        );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("Drop table if exists orders");
        onCreate(db);

    }

    public boolean insertOrder(String name, String phone, int price, int image, String description, String foodname, int quantity) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("img", image);
        values.put("description", description);
        values.put("foodname", foodname);
        values.put("quantity", quantity);
        long id = database.insert("orders", null, values);
        if (id <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<ordersModel> getOrders() {
        ArrayList<ordersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,foodname,img,price from orders ", null);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
         ordersModel ordersModels = new ordersModel();
         ordersModels.setOrderName(cursor.getInt(0)+"");
         ordersModels.setSoldItemName(cursor.getString(1));
         ordersModels.setOrderImage(cursor.getInt(2));
         ordersModels.setPrice(cursor.getInt(3)+"");
         orders.add(ordersModels);
            }
        }
        cursor.close();
        database.close();
        return orders; 

    }
     public Cursor getOrderById(int id){

         SQLiteDatabase database = this.getWritableDatabase();
         Cursor cursor = database.rawQuery("Select * from orders where id = " +id, null);
         if (cursor !=null)
             cursor.moveToFirst();



         return cursor;
     }
    public boolean updateorder(String name, String phone, int price, int image, String description, String foodname, int quantity,int id) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("img", image);
        values.put("description", description);
        values.put("foodname", foodname);
        values.put("quantity", quantity);
        long row = database.update("orders",values,"id="+id,null);
        if (row <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public int deletorder(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        return database.delete("orders" ,"id ="+id,null);


    }

}


