package com.cs360.ryansantos.localcoffeeshop;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.view.View.GONE;

public class CartActivity extends AppCompatActivity {

    ListView cart;
    ArrayList<Cart> cartList;
    CartAdapter adapter;
    CartDBHandler dbHandler;
    SQLiteDatabase db;
    private boolean orderSubmitted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        cart = (ListView) findViewById(R.id.cartListView);
        TextView empty = (TextView) findViewById(R.id.empty);
        Button submit = (Button) findViewById(R.id.submit_button);

        dbHandler = new CartDBHandler(this);
        cartList = new ArrayList<>();

        db = this.dbHandler.getWritableDatabase();

        loadListViewData();

        // Only allows user to submit an order if an item is added to cart
        if (cartList.isEmpty()){
            submit.setVisibility(View.GONE);
        }

        cart.setEmptyView(empty);

    }

    // Implements functionality of the submit button and brings user to Order Submitted screen
    public void onClick(View v) {
        if (v.getId() == R.id.submit_button) {
            orderSubmitted = true;
            Intent intent = new Intent(CartActivity.this, OrderPlacedActivity.class);
            startActivity(intent);
        }
    }

    // Loads all of the data into the ListView
    private void loadListViewData() {

        cartList = dbHandler.getAllData();
        adapter = new CartAdapter(this, cartList);
        cart.setAdapter(adapter);
    }


}
