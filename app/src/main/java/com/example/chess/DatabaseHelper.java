package com.example.chess;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Game.db";
    public static final String TABLE_NAME = "Game_Table";
    public static final String COL1 = "ID";
    public static final String COL2 = "PLAYERS";
    public static final String COL3 = "WINNER";
    public static final String COL4 = "TIME";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, PLAYERS TEXT, WINNER TEXT, TIME DATETIME)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String players, String winner, String datetime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2,players);
        contentValues.put(COL3,winner);
        contentValues.put(COL4,datetime);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }
        return true;
    }


    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
        return res;

    }

    public ArrayList<Game> getAllGames(){
        String query = "select * from " + TABLE_NAME + " ORDER BY TIME DESC;";
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Game> games = new ArrayList<>();
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String players = cursor.getString(1);
                String winner = cursor.getString(2);
                String datetime = cursor.getString(3);
                games.add(new Game(id, players, winner, datetime));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return games;
    }

    public void deleteGame(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, COL1 + "	= ?", new String[]{String.valueOf(id)});
    }
}
