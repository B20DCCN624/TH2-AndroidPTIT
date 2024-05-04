package com.example.androidptit_th2.dal;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.androidptit_th2.adapter.SongAdapter;
import com.example.androidptit_th2.model.Song;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Song.db";
    private static int DATABASE_VERSION = 1;
    private SongAdapter songAdapter;


    public SQLiteHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreateDB = "CREATE TABLE songs("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "work TEXT,"+
                "content TEXT," +
                "date TEXT," +
                "status TEXT," +
                "collaborate INTEGER DEFAULT 0)";
        db.execSQL(sqlCreateDB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    //getAll
    public List<Song> getAll() {
        List<Song> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor rs = sqLiteDatabase.query("songs",
                null, null, null,
                null, null, null);
        while ((rs != null) && (rs.moveToNext())) {
            int id= rs.getInt(0);
            String work = rs.getString(1);
            String content = rs.getString(2);
            String date = rs.getString(3);
            String status = rs.getString(4);
            boolean collaborate = rs.getInt(5) == 1;
            list.add(new Song(id, work, content, date, status, collaborate));
        }
        return list;
    }

    //add
    public long addWork(Song i){
        ContentValues values = new ContentValues();
        values.put("work", i.getName());
        values.put("content", i.getContent());
        values.put("date", i.getDate());
        values.put("status", i.getStatus());
        values.put("collaborate", i.isCollaborate() ? 1 : 0);
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        long result = sqLiteDatabase.insert("songs", null, values);

        // Sau khi thêm một mục mới, cập nhật dữ liệu cho RecyclerView
        if (songAdapter != null) {
            List<Song> newList = getAll();
            songAdapter.setData(newList);
        }
        return result;
    }

    //Update
    public int updateWork(Song song) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("work", song.getName());
        values.put("content", song.getContent());
        values.put("date", song.getDate());
        values.put("status", song.getStatus());
        values.put("collaborate", song.isCollaborate() ? 1 : 0);
        return sqLiteDatabase.update("songs", values, "id = ?", new String[]{String.valueOf(song.getId())});

    }

    //Delete
    public boolean deleteWork(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int result = sqLiteDatabase.delete("songs", "id = ?", new String[]{String.valueOf(id)});
        return result > 0;
    }

    //Search
    public List<Song> searchByName(String key, String orderBy) {
        List<Song> list= new ArrayList<>();
        String whereClause = "work LIKE ?";
        String[] whereArgs = {"%" + key + "%"};
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        Cursor rs = sqLiteDatabase.query("songs",
                null, whereClause, whereArgs,
                null, null, orderBy);
        while ((rs != null) && (rs.moveToNext())) {
            int id= rs.getInt(0);
            String work = rs.getString(1);
            String content = rs.getString(2);
            String date = rs.getString(3);
            String status = rs.getString(4);
            boolean collaborate = rs.getInt(5) == 1;
            list.add(new Song(id, work, content, date, status, collaborate));
        }
        return list;
    }
}
