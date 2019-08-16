package com.cs360.ryansantos.localcoffeeshop;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CartAdapter extends BaseAdapter {

    Context context;
    ArrayList<Cart> arrayList = new ArrayList<Cart>();
    ArrayList<Order> orderArrayList = new ArrayList<Order>();
    CartDBHandler cartHandler;
    CartAdapter adapter;
    OrderDBHandler orderDBHandler;
    OrderAdapter orderAdapter;

    public CartAdapter(){}

    public CartAdapter(Context context, ArrayList<Cart> arrayList) {
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

    // Creates list view for cart
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_cartlistview, null);
        final TextView t1_name = (TextView) convertView.findViewById(R.id.item_name);
        final TextView t2_price = (TextView) convertView.findViewById(R.id.item_price);

        Cart cart = arrayList.get(position);
        String stringPrice = Double.toString(cart.getPrice());

        t1_name.setText(cart.getName());
        t2_price.setText(stringPrice);

        Button removeItem = (Button) convertView.findViewById(R.id.removeitem_button);


        // Implements remove item button in cart
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.removeitem_button) {
                    cartHandler = new CartDBHandler(context);
                    adapter = new CartAdapter(context, arrayList);

                    orderDBHandler = new OrderDBHandler(context);
                    orderAdapter = new OrderAdapter(context, orderArrayList);

                    String name = t1_name.getText().toString();

                    cartHandler.deleteCartItem(name);
                    orderDBHandler.deleteOrderItem(name);

                    arrayList.remove(position);

                    Toast.makeText(context.getApplicationContext(), "Removed from Cart", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(context, CartActivity.class);
                    context.startActivity(intent);

                }
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }


}