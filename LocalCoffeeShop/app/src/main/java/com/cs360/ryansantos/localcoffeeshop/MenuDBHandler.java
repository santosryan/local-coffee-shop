package com.cs360.ryansantos.localcoffeeshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;


public class MenuDBHandler extends SQLiteOpenHelper {

    Context context;

    // database name and version
    private static final int DB_VER = 1;
    private static final String DB_NAME = "menuDB.db";

    // table
    public static final String TABLE_MENU = "menu";

    // columns
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PRICE = "price";

    // constructor
    public MenuDBHandler(Context context)
    {
        super(context, DB_NAME, null, DB_VER);
    }


    // This method creates the Menu table when the DB is initialized.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MENU_TABLE = "CREATE TABLE " +
                TABLE_MENU + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + " TEXT," +
                COLUMN_PRICE + " REAL" + ")";
        db.execSQL(CREATE_MENU_TABLE);
    }

    // This method closes an open DB if a new one is created.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MENU);
        onCreate(db);
    }

    // This method is used to add a Menu Item record to the database.
    public void addMenuItem(String itemName, double itemPrice) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, itemName);
        values.put(COLUMN_PRICE, itemPrice);

        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_MENU, null, values);
        db.close();
    }


    public ArrayList<Menu> getAllData(){
        ArrayList<Menu> arrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_MENU;
        Cursor cursor = db.rawQuery(query, null);

        while(cursor.moveToNext()) {
            String name = cursor.getString(1);
            double price = cursor.getDouble(2);
            Menu menu = new Menu(price, name);

            arrayList.add(menu);
        }

        return arrayList;

    }

    // implements the search/find functionality
    public Menu searchMenuItem(String itemID) {
        String query = "SELECT * FROM " +
                TABLE_MENU + " WHERE " + COLUMN_NAME +
                " = \"" + itemID + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Menu menuItem = new Menu();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            menuItem.setID(Integer.parseInt(cursor.getString(0)));
            menuItem.setName(cursor.getString(1));
            menuItem.setPrice(Double.parseDouble(cursor.getString(2)));
            cursor.close();
        } else {
            menuItem = null;
        }
        db.close();
        return menuItem;
    }


    // implements the delete menu item functionality
    public boolean deleteMenuItem(String itemName) {
        boolean result = false;

        String query = "SELECT * FROM " + TABLE_MENU +
                " WHERE " + COLUMN_NAME + " = \"" + itemName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Menu menuItem = new Menu();

        if (cursor.moveToFirst()) {
            menuItem.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_MENU, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(menuItem.getID())});
                    cursor.close();
                    result = true;
        }
        db.close();
        return result;
    }

    public void clearDatabase(SQLiteDatabase db) {
        String clearDBQuery = "DELETE FROM "+ TABLE_MENU;
        db.execSQL(clearDBQuery);
    }

}
