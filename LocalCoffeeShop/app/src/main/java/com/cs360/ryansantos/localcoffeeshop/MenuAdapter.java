package com.cs360.ryansantos.localcoffeeshop;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {

    Context context;
    ArrayList<Menu> arrayList;
    CartDBHandler cartHandler;
    OrderDBHandler orderHandler;
    SQLiteDatabase cartDB;

    public MenuAdapter(Context context, ArrayList<Menu> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    // Creates list view for menu
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_listview, null);

        final TextView t1_name = (TextView) convertView.findViewById(R.id.item_name);
        final TextView t2_price = (TextView) convertView.findViewById(R.id.item_price);

        final Menu menu = arrayList.get(position);
        String stringPrice = Double.toString(menu.getPrice());

        t1_name.setText(menu.getName());
        t2_price.setText(stringPrice);

        Button addItem = (Button) convertView.findViewById(R.id.additem_button);


        // Implements add item button to add items to cart
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cartHandler = new CartDBHandler(context);
                orderHandler = new OrderDBHandler(context);

                String name = t1_name.getText().toString();
                String price_text = t2_price.getText().toString();

                double price = Double.parseDouble(price_text);

                cartHandler.addCartItem(name, price);
                orderHandler.addOrderItem(name, price);

                Toast.makeText(context.getApplicationContext(), "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }


    @Override
    public int getCount() {

        return this.arrayList.size();
    }


}
