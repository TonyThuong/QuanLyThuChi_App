package com.example.asmm.DbHelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context,"thuchi",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Tạo bảng loại thu chi
        String sql="CREATE TABLE LOAI_TC(MaLoai integer primary key autoincrement,"+"TenLoai text,TrangThai text)";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO LOAI_TC(TenLoai,TrangThai) VALUES('Lại ngân hàng','Thu')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai,TrangThai) VALUES('Lương','Thu')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai,TrangThai) VALUES('Bán hàng','Thu')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai,TrangThai) VALUES('Thu nợ','Thu')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai,TrangThai) VALUES('Sinh hoạt hằng ngày','Chi')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai,TrangThai) VALUES('Đóng tiền học','Chi')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO LOAI_TC(TenLoai,TrangThai) VALUES('Du lịch','Chi')";
        sqLiteDatabase.execSQL(sql);


        //Tạo bảng khoản thu chi
        sql = "CREATE TABLE KHOAN_TC(MaGD integer primary key autoincrement,"+"TieuDe text,Ngay date,Tien float,MoTa text,"+"MaLoai interger references LOAI_TC(MaLoai))";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO KHOAN_TC(TieuDe,Ngay,Tien,MoTa,MaLoai) VALUES('Lương tháng 1/2020','30/07/2020','1000','Lương',2)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO KHOAN_TC(TieuDe,Ngay,Tien,MoTa,MaLoai) VALUES('Bán','30/07/2020','2000','Áo',3)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO KHOAN_TC(TieuDe,Ngay,Tien,MoTa,MaLoai) VALUES('Tiền lãi 2019','01/01/2020','5000','Lãi',1)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO KHOAN_TC(TieuDe,Ngay,Tien,MoTa,MaLoai) VALUES('Mua quần áo','20/07/2020','1500','Áo',5)";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO KHOAN_TC(TieuDe,Ngay,Tien,MoTa,MaLoai) VALUES('Ăn uống','10/08/2020','500','Ăn vặt',5)";
        sqLiteDatabase.execSQL(sql);


        sql="CREATE TABLE QUANTRI(username text primary key,password text)";
        sqLiteDatabase.execSQL(sql);
        sql= "INSERT INTO QUANTRI VALUES('admin','admin')";
        sqLiteDatabase.execSQL(sql);
        sql= "INSERT INTO QUANTRI VALUES('anvt','123')";
        sqLiteDatabase.execSQL(sql);

        sql="CREATE TABLE NhaPhanPhoi(MaNPP integer primary key autoincrement,"+" TenNPP text )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO NhaPhanPhoi(MaNPP,TenNPP) VALUES('01','Vinamilk')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO NhaPhanPhoi(MaNPP,TenNPP) VALUES('02','Tony')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO NhaPhanPhoi(MaNPP,TenNPP) VALUES('03','Thuong')";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS LOAI_TC");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS KHOAN_THU");
        onCreate(sqLiteDatabase);
    }
}
