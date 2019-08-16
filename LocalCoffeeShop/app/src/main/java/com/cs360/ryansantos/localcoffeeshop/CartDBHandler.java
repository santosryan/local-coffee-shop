package com.cs360.ryansantos.localcoffeeshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class CartDBHandler extends SQLiteOpenHelper {

    Context context;
    private double total_price;

    // database name and version
    private static final int DB_VER = 1;
    private static final String DB_NAME = "cartDB.db";

    // table
    public static final String TABLE_CART = "cart";

    // columns
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";

    // constructor
    public CartDBHandler(Context context)
    {
        super(context, DB_NAME, null, DB_VER);
    }


    // This method creates the Menu table when the DB is initialized.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CART_TABLE = "CREATE TABLE " +
                TABLE_CART + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + " TEXT," +
                COLUMN_PRICE + " REAL" + ")";
        db.execSQL(CREATE_CART_TABLE);

    }

    // This method closes an open DB if a new one is created.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        onCreate(db);
    }

    // This method is used to add item to Order record to the database.
    public void addCartItem(String itemName, double itemPrice) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, itemName);
        values.put(COLUMN_PRICE, itemPrice);

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_CART, null, values);
        db.close();
    }


    public ArrayList<Cart> getAllData(){
        ArrayList<Cart> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        total_price = 0;
        String query = "SELECT * FROM " + TABLE_CART;
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()) {
            String name = cursor.getString(1);
            double price = cursor.getDouble(2);
            Cart cart = new Cart(price, name);

            total_price = price + total_price;

            arrayList.add(cart);
        }

        //cursor.close();

        return arrayList;

    }

    // implements the search/find functionality
    public Cart searchCartItem(String itemID) {
        String query = "SELECT * FROM " +
                TABLE_CART + " WHERE " + COLUMN_NAME +
                " = \"" + itemID + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Cart cartItem = new Cart();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            cartItem.setID(Integer.parseInt(cursor.getString(0)));
            cartItem.setName(cursor.getString(1));
            cartItem.setPrice(Double.parseDouble(cursor.getString(2)));
            cursor.close();
        } else {
            cartItem = null;
        }
        db.close();
        return cartItem;
    }

    // implements the delete menu item functionality
    public boolean deleteCartItem(String itemName) {
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_CART +
                " WHERE " + COLUMN_NAME + " = \"" + itemName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Cart cartItem = new Cart();

        if (cursor.moveToFirst()) {
            cartItem.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_CART, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(cartItem.getID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public void clearDatabase(SQLiteDatabase db) {
        String clearDBQuery = "DELETE FROM "+ TABLE_CART;
        db.execSQL(clearDBQuery);
    }

    public double getTotalPrice() { return this.total_price; }

}
