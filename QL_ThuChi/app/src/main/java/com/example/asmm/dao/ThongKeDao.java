package com.example.asmm.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.asmm.DbHelper.DbHelper;

public class ThongKeDao {
    public static double tongTienTheoTT(Context context, String trangthai){
        double tongTien = 0;
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();
        String str = "select SUM(Tien) as TongTien "
                +"from KHOAN_TC join LOAI_TC "
                +"on KHOAN_TC.MaLoai = LOAI_TC.MaLoai "
                +"where TrangThai = '"+ trangthai +"'";

        Cursor cs = db.rawQuery(str,null);
        cs.moveToFirst();
        if (cs.getCount() >= 0){
            tongTien = cs.getDouble(0);
        }
        return tongTien;
    }
}

