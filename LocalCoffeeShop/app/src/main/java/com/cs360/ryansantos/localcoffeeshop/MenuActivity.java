package com.cs360.ryansantos.localcoffeeshop;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;


import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    ListView menu;
    MenuDBHandler dbHandler;
    ArrayList<Menu> arrayList;
    ArrayList<Cart> cartArrayList;
    MenuAdapter adapter;
    Context context;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menu = (ListView) findViewById(R.id.listView);
        
        dbHandler = new MenuDBHandler(this);
        arrayList = new ArrayList<>();

        db = this.dbHandler.getWritableDatabase();
        dbHandler.clearDatabase(db);

        // Creates menu
        dbHandler.addMenuItem("hot coffee", 2.29);
        dbHandler.addMenuItem("iced coffee", 2.69);
        dbHandler.addMenuItem("cappuccino", 3.99);
        dbHandler.addMenuItem("latte", 3.99);
        dbHandler.addMenuItem("english breakfast tea", 2.09);
        dbHandler.addMenuItem("earl grey tea", 2.09);
        dbHandler.addMenuItem("green tea", 2.09);
        dbHandler.addMenuItem("strawberry kiwi smoothie", 3.29);
        dbHandler.addMenuItem("mango smoothie", 3.29);
        dbHandler.addMenuItem("peanut butter banana smoothie", 3.29);
        dbHandler.addMenuItem("chocolate shake", 3.59);
        dbHandler.addMenuItem("vanilla shake", 3.59);
        dbHandler.addMenuItem("bacon egg n cheese bagel", 3.29);
        dbHandler.addMenuItem("ham egg n cheese english muffin", 3.29);
        dbHandler.addMenuItem("ham and cheese", 6.99);
        dbHandler.addMenuItem("grilled cheese", 5.99);

        loadListViewData();

    }

    // Brings user to cart
    public void onClick(View view) {
            Intent intent = new Intent(MenuActivity.this, CartActivity.class);
            startActivity(intent);
    }


    // Displays menu items and prices
    private void loadListViewData() {

        arrayList = dbHandler.getAllData();
        adapter = new MenuAdapter(this,arrayList);
        menu.setAdapter(adapter);
    }

}
