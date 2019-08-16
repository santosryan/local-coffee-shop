package com.cs360.ryansantos.localcoffeeshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class OrderDBHandler extends SQLiteOpenHelper {

    Context context;
    private double total_price;

    // database name and version
    private static final int DB_VER = 1;
    private static final String DB_NAME = "orderDB.db";

    // table
    public static final String TABLE_ORDER = "ordertable";

    // columns
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";

    // constructor
    public OrderDBHandler(Context context)
    {
        super(context, DB_NAME, null, DB_VER);
    }


    // This method creates the Menu table when the DB is initialized.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ORDER_TABLE = "CREATE TABLE " +
                TABLE_ORDER + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + " TEXT," +
                COLUMN_PRICE + " REAL" + ")";
        db.execSQL(CREATE_ORDER_TABLE);

    }

    // This method closes an open DB if a new one is created.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        onCreate(db);
    }


    // This method is used to add item to Order record to the database.
    public void addOrderItem(String itemName, double itemPrice) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, itemName);
        values.put(COLUMN_PRICE, itemPrice);

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_ORDER, null, values);
        db.close();
    }


    // Takes all of the information in the table and inserts it into an ArrayList of Orders
    public ArrayList<Order> getAllData(){
        ArrayList<Order> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ORDER;
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()) {
            String name = cursor.getString(1);
            double price = cursor.getDouble(2);
            Order order = new Order(price, name);

            arrayList.add(order);
        }

        //cursor.close();

        return arrayList;

    }

    // implements the search/find functionality
    public Order searchOrderItem(String itemID) {
        String query = "SELECT * FROM " +
                TABLE_ORDER + " WHERE " + COLUMN_NAME +
                " = \"" + itemID + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Order orderItem = new Order();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            orderItem.setID(Integer.parseInt(cursor.getString(0)));
            orderItem.setName(cursor.getString(1));
            orderItem.setPrice(Double.parseDouble(cursor.getString(2)));
            cursor.close();
        } else {
            orderItem = null;
        }
        db.close();
        return orderItem;
    }

    // implements the delete menu item functionality
    public boolean deleteOrderItem(String itemName) {
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_ORDER +
                " WHERE " + COLUMN_NAME + " = \"" + itemName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Order orderItem = new Order();

        if (cursor.moveToFirst()) {
            orderItem.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_ORDER, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(orderItem.getID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public void clearDatabase(SQLiteDatabase db) {
        String clearDBQuery = "DELETE FROM "+ TABLE_ORDER;
        db.execSQL(clearDBQuery);
    }

    // Returns the total price of the order
    public double getTotalPrice() {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_ORDER;
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()) {
            double price = cursor.getDouble(2);

            total_price = total_price + price;
        }

        return total_price;
    }
}
