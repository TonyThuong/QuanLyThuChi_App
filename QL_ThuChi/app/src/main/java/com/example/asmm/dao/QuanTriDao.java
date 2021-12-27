package com.example.asmm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmm.DbHelper.DbHelper;
import com.example.asmm.Loginl;
import com.example.asmm.model.User;


public class QuanTriDao {
    DbHelper helper;
    public QuanTriDao(Context context){
        helper = new DbHelper(context);
    }
    public boolean checkLogin(User user){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cs = db.rawQuery("SELECT * FROM QUANTRI WHERE username=? AND password=?",
                new String[]{user.getUsername(),user.getPassword()});
        if (cs.getCount() <= 0){
            return false;
        }
        return true;
    }

    public boolean checkOldPw(String oldPw){
        //lấy ra Username --> check trong db xem pass của user đúng hay k?
        String pwd = Loginl.USER.getPassword();
        if(!oldPw.equals(pwd)){
            return false;
        }
        return true;
    }
    public boolean updatePass(User user){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password",user.getPassword());
        int row = db.update("QUANTRI",values,"username=?",new String[]{user.getUsername()});
        return row>0;
    }
}
