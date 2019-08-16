package com.cs360.ryansantos.localcoffeeshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {

    Context context;
    ArrayList<Order> arrayList = new ArrayList<Order>();

    public OrderAdapter(){}

    public OrderAdapter(Context context, ArrayList<Order> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    // Creates listview for order
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.custom_orderlistview, parent, false);
        final TextView t1_name = (TextView) convertView.findViewById(R.id.item_name);
        final TextView t2_price = (TextView) convertView.findViewById(R.id.item_price);


        Order order = arrayList.get(position);
        String stringPrice = Double.toString(order.getPrice());

        t1_name.setText(order.getName());
        t2_price.setText(stringPrice);


        return convertView;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }
}
