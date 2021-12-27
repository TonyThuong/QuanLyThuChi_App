package com.example.asmm.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmm.DbHelper.DbHelper;
import com.example.asmm.model.NhaPhanPhoi;
import com.example.asmm.model.Phanloai;

import java.util.ArrayList;

public class NhaPhanPhoiDao {
    SQLiteDatabase db1;
    DbHelper db;
    public NhaPhanPhoiDao(Context context){
        db = new DbHelper(context);
    }

    public ArrayList<NhaPhanPhoi> getDanhSach(){
        ArrayList<NhaPhanPhoi> ds_npp = new ArrayList<>();
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.query("NhaPhanPhoi",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                int MaNPP = cursor.getInt(0);
                String tenNPP = cursor.getString(1);
                ds_npp.add(new NhaPhanPhoi(MaNPP,tenNPP));
            } while (cursor.moveToNext());
        }
        return ds_npp;
    }
}
