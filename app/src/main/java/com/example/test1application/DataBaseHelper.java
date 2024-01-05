package com.example.test1application;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "test.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "food";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "_name";
    private static final String COLUMN_CAL = "_cal";
    private static final String COLUMN_GOAL = "_goal";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_CAL + " INTEGER, " +
                COLUMN_GOAL + " BOOL )";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void restartDB()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
        db.close();
    }

    public long addFood(FoodModel foodModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, foodModel.getName());
        cv.put(COLUMN_CAL, foodModel.getCalories());
        cv.put(COLUMN_GOAL, foodModel.isGoalAchieved());

        long res = db.insert(TABLE_NAME, null, cv);

        db.close();
        return res;
    }

    public long updateFood(int id, FoodModel foodModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, foodModel.getName());
        cv.put(COLUMN_CAL, foodModel.getCalories());
        cv.put(COLUMN_GOAL, foodModel.isGoalAchieved());

        long res = db.update(TABLE_NAME, cv, COLUMN_ID + " = " + id, null);

        db.close();
        return res;
    }

    public long deleteFood(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        long res = db.delete(TABLE_NAME, COLUMN_ID + " = " + id, null);

        db.close();
        return res;
    }

    public List<FoodModel> getAllFood()
    {
        SQLiteDatabase db =this.getReadableDatabase();
        List<FoodModel> foods = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        while(cursor.moveToNext())
        {
            foods.add(new FoodModel(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getInt(3) == 1));
        }
        return foods;
    }
}


