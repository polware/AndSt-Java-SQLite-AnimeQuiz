package com.example.animequiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DatabaseDAO {

    //Este método hace 10 consultas aleatorias a la base de datos
    public ArrayList<AnimeModel> getRamdonQuestions(AnimeDB animeDB) {
        ArrayList<AnimeModel> modelArrayList = new ArrayList<>();
        SQLiteDatabase liteDatabase = animeDB.getWritableDatabase();
        Cursor cursor = liteDatabase.rawQuery("SELECT * FROM animequiztable ORDER BY RANDOM () LIMIT 10", null);
        int animeIdIndex = cursor.getColumnIndex("anime_ID");
        int animeNameIndex = cursor.getColumnIndex("anime_name");
        int animeImageIndex = cursor.getColumnIndex("anime_image");
        while (cursor.moveToNext()) {
            AnimeModel model = new AnimeModel(cursor.getInt(animeIdIndex),
                    cursor.getString(animeNameIndex), cursor.getString(animeImageIndex));
            modelArrayList.add(model);
        }
        return modelArrayList;
    }

    //Este método trae las opciones de las respuestas: 2 erradas
    public ArrayList<AnimeModel> getRamdonOptions(AnimeDB animeDB, int anime_id) {
        ArrayList<AnimeModel> modelArrayList = new ArrayList<>();
        SQLiteDatabase liteDatabase = animeDB.getWritableDatabase();
        Cursor cursor = liteDatabase.rawQuery("SELECT * FROM animequiztable WHERE anime_id !="+anime_id+" ORDER BY RANDOM () LIMIT 2", null);
        int animeIdIndex = cursor.getColumnIndex("anime_ID");
        int animeNameIndex = cursor.getColumnIndex("anime_name");
        int animeImageIndex = cursor.getColumnIndex("anime_image");
        while (cursor.moveToNext()) {
            AnimeModel model = new AnimeModel(cursor.getInt(animeIdIndex),
                    cursor.getString(animeNameIndex), cursor.getString(animeImageIndex));
            modelArrayList.add(model);
        }
        return modelArrayList;
    }

}
