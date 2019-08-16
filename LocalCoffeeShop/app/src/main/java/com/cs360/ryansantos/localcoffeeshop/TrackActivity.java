package com.cs360.ryansantos.localcoffeeshop;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TrackActivity extends AppCompatActivity {

    ListView order;
    ArrayList<Order> orderList;
    OrderAdapter adapter;
    OrderDBHandler dbHandler;
    SQLiteDatabase db;
    double total;
    boolean listEmpty = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        order = (ListView) findViewById(R.id.orderListView);
        TextView empty = (TextView) findViewById(R.id.empty);
        TextView status = (TextView) findViewById(R.id.orderstatus_text);
        TextView totalText = (TextView) findViewById(R.id.totalText);
        TextView totalPrice = (TextView) findViewById(R.id.totalPrice);

        orderList = new ArrayList<>();
        dbHandler = new OrderDBHandler(this);

        db = this.dbHandler.getWritableDatabase();

        total = dbHandler.getTotalPrice();

        String stringPrice = Double.toString(total);

        totalPrice.setText(stringPrice);
        loadListViewData();

        //Removes static text fields if no order is placed
        if (listEmpty == true){
            status.setVisibility(View.GONE);
            totalPrice.setVisibility(View.GONE);
            totalText.setVisibility(View.GONE);
        }

        order.setEmptyView(empty);

    }

    // Loads order information into ListView
    private void loadListViewData() {
        orderList = dbHandler.getAllData();

        if ( orderList.isEmpty() ){
            listEmpty = true;
        }

        adapter = new OrderAdapter(this, orderList);
        order.setAdapter(adapter);
    }
}
