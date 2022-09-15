package com.example.animequiz;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AnimeDB extends SQLiteOpenHelper {

    public AnimeDB(@Nullable Context context) {
        super(context, "animequizdb.db", null, 1);
    }

    //SI sale error, tener en cuenta video 006 Creating the Database Class in android studio app
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE \"animequiztable\" (\n" +
                "\t\"anime_ID\"\tINTEGER,\n" +
                "\t\"anime_name\"\tTEXT,\n" +
                "\t\"anime_image\"\tTEXT\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS AnimeQuizTable");
        onCreate(sqLiteDatabase);
    }
}
