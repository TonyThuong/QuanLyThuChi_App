package com.example.asmm.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.asmm.DbHelper.DbHelper;
import com.example.asmm.model.GiaoDich;
import com.example.asmm.model.Phanloai;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GiaoDichDao {
    SQLiteDatabase db1;
    DbHelper db;
    public GiaoDichDao(Context context){
        db = new DbHelper(context);
    }

    public ArrayList<Phanloai> getThu(){
        ArrayList<Phanloai> ds_pl = new ArrayList<>();
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM LOAI_TC WHERE Trangthai LIKE 'Thu'", null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String tenloai = cursor.getString(1);
                String trangthai = cursor.getString(2);
                ds_pl.add(new Phanloai(id,tenloai,trangthai));
            } while (cursor.moveToNext());
        }
        return ds_pl;
    }

    public ArrayList<Phanloai> getChi(){
        ArrayList<Phanloai> ds_pl = new ArrayList<>();
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM LOAI_TC WHERE Trangthai = '"+"Chi"+"'", null);
        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(0);
                String tenloai = cursor.getString(1);
                String trangthai = cursor.getString(2);
                ds_pl.add(new Phanloai(id,tenloai,trangthai));
            } while (cursor.moveToNext());
        }
        return ds_pl;
    }

    public ArrayList<GiaoDich> getKhoanThu_Chi(String trangthai){
        ArrayList<GiaoDich> ds_giaodich = new ArrayList<>();
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT * FROM KHOAN_TC INNER JOIN LOAI_TC ON KHOAN_TC.Maloai = LOAI_TC.Maloai AND LOAI_TC.trangthai LIKE '"+trangthai+"'",null);
        if (cursor.moveToFirst()){
            do {
                Date date1 = null;
                int Magiaodich = cursor.getInt(0);
                String Tieude = cursor.getString(1);
                String Ngay = cursor.getString(2);
                double Tien = Double.parseDouble(cursor.getString(3));
                String Mota = cursor.getString(4);
                int Maloai = cursor.getInt(5);
                ds_giaodich.add(new GiaoDich(Magiaodich,Tieude,Ngay,Tien,Mota,Maloai));
            } while (cursor.moveToNext());
        }
        return ds_giaodich;
    }

    public void them(GiaoDich gd) throws ParseException {
        db1 = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TieuDe",gd.getTieuDe());
        values.put("Ngay", gd.getNgay());
        values.put("Tien",gd.getTien());
        values.put("MoTa",gd.getMoTa());
        values.put("MaLoai",gd.getMaLoai());

        db1.insert("KHOAN_TC",null,values);
    }

    public String getTen(String id){
        String ten = "";
        db1 = db.getWritableDatabase();
        Cursor cursor = db1.rawQuery("SELECT Tenloai FROM LOAI_TC WHERE Maloai = '"+id+"'",null);
        if (cursor.moveToFirst()){
            do {
                ten = cursor.getString(0);
            } while (cursor.moveToNext());
        }
        return ten;
    }

    public void delete(int id){
        db1 = db.getWritableDatabase();
        db1.delete("KHOAN_TC","MaLoai=?",new String[]{id+""});
    }

    public void capnhat(Phanloai pl){
        db1 = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TenLoai",pl.getTenloai());
        values.put("TrangThai",pl.getTrangthai());
        db1.update("LOAI_TC",values,"MaLoai=?",new String[]{pl.getId_pl()+""});
    }
    public void update(GiaoDich gd){
        db1 = db.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("TieuDe", gd.getTieuDe());
        values.put("Ngay", gd.getNgay());
        values.put("Tien", gd.getTien());
        values.put("MoTa", gd.getMoTa());
        values.put("MaLoai", gd.getMaLoai());
        db1.update("KHOAN_TC",values, "MaGD=?", new String[]{gd.getMaGD()+""});
    }

}

