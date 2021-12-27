package com.example.asmm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmm.DbHelper.DbHelper;
import com.example.asmm.model.Phanloai;

import java.util.ArrayList;

public class PhanloaiDao {
    SQLiteDatabase db1;
    DbHelper db;
    public PhanloaiDao(Context context){
        db = new DbHelper(context);
    }

    public ArrayList<Phanloai> readAll(){
        ArrayList<Phanloai> ds_pl = new ArrayList<>();
        db1 = db.getReadableDatabase();
        Cursor cursor = db1.query("LOAI_TC",null,null,null,null,null,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            int id = cursor.getInt(0);
            String tenloai = cursor.getString(1);
            String trangthai = cursor.getString(2);
            ds_pl.add(new Phanloai(id,tenloai,trangthai));
            cursor.moveToNext();
        }
        return ds_pl;
    }

    public void them(Phanloai pl){
        db1 = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenLoai",pl.getTenloai());
        values.put("TrangThai",pl.getTrangthai());
        db1.insert("LOAI_TC",null,values);
    }

    public void delete(int id){
        db1 = db.getWritableDatabase();
        db1.delete("LOAI_TC","MaLoai=?",new String[]{id+""});
    }

    public void capnhat(Phanloai pl){
        db1 = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenLoai",pl.getTenloai());
        values.put("TrangThai",pl.getTrangthai());
        db1.update("LOAI_TC",values,"MaLoai=?",new String[]{pl.getId_pl()+""});
    }

}

