package com.example.thuchanh3.DatabaseProvider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.thuchanh3.Model.Item;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ITEM_MANAGER";

    // Table name: Note.
    private static final String TABLE_ITEM = "Item";

    private static final String COLUMN_ITEM_ID ="ItemID";
    private static final String COLUMN_ITEM_NAME ="Name";
    private static final String COLUMN_ITEM_PRICE = "Price";
    private static final String COLUMN_ITEM_IMAGE = "Image";
    private static final String COLUMN_ITEM_TYPE = "Type";

    public SQLiteHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script.
        String script = "CREATE TABLE " + TABLE_ITEM + "("
                + COLUMN_ITEM_ID + " INTEGER PRIMARY KEY," + COLUMN_ITEM_NAME + " TEXT,"
                + COLUMN_ITEM_PRICE + " FLOAT," + COLUMN_ITEM_IMAGE + " TEXT," + COLUMN_ITEM_TYPE + " TEXT" +")";
        // Execute Script.
        db.execSQL(script);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEM);

        // Create tables again
        onCreate(db);
    }
    public void addItem(Item item) {
        Log.i(TAG, "MyDatabaseHelper.addNote ... " + item.getName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ITEM_NAME, item.getName());
        values.put(COLUMN_ITEM_PRICE, item.getPrice());
        values.put(COLUMN_ITEM_IMAGE, item.getImage());
        values.put(COLUMN_ITEM_TYPE, item.getType());

        // Inserting Row
        db.insert(TABLE_ITEM, null, values);

        // Closing database connection
        db.close();
    }

    public List<Item> getAllItems() {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        List<Item> itemList = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ITEM;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setPrice(Float.parseFloat(cursor.getString(2)));
                item.setImage(cursor.getString(3));
                item.setType(cursor.getString(4));
                // Adding note to list
                itemList.add(item);
            } while (cursor.moveToNext());
        }

        // return note list
        return itemList;
    }
}
