package com.example.asmm.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


import com.example.asmm.R;
import com.example.asmm.dao.GiaoDichDao;
import com.example.asmm.dialog.Bottom_sheet_edit_thu;
import com.example.asmm.model.GiaoDich;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.asmm.tabfragment.Tab_Thu.ds_khoanthu;
import static com.example.asmm.tabfragment.Tab_Thu.khoanthu_adapter;
import static com.example.asmm.tabfragment.Tab_Thu.rv_tthu;

public class Khoanthu_Adapter extends RecyclerView.Adapter<Khoanthu_Adapter.MyViewHolder> {
    private ArrayList<GiaoDich> ds_thu;
    private Context context;
    GiaoDichDao giaodich_dao;

    public Khoanthu_Adapter(ArrayList<GiaoDich> ds_thu, Context context) {
        this.ds_thu = ds_thu;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_tieude;
        public TextView tv_ngay;
        public TextView tv_tien;
        private ImageView img_xoa_thu;
        private ImageView img_edit_thu;
        public MyViewHolder(View v) {
            super(v);
            tv_tieude = v.findViewById(R.id.tv_tieu_de_thu);
            tv_ngay = v.findViewById(R.id.tv_ngay_thu);
            tv_tien = v.findViewById(R.id.tv_tien_thu);
            img_xoa_thu = v.findViewById(R.id.iv_xoa_thu);
            img_edit_thu = v.findViewById(R.id.iv_sua_thu);
        }
    }

    @Override
    public Khoanthu_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoan_thu, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_tieude.setText(ds_thu.get(position).getTieuDe());
        holder.tv_ngay.setText(ds_thu.get(position).getNgay()+"");
        //Dinh dang hien thi so tien
        DecimalFormat formatter = new DecimalFormat("#,###");
        String s = formatter.format(ds_thu.get(position).getTien());
        holder.tv_tien.setText(s);
        holder.img_xoa_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa "+ds_thu.get(position).getTieuDe());
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                giaodich_dao = new GiaoDichDao(context);
                                giaodich_dao.delete(ds_thu.get(position).getMaGD());
                                Toast.makeText(context, "Xóa thành công "+ds_thu.get(position).getTieuDe(), Toast.LENGTH_SHORT).show();

                                capnhat();
                                dialog.cancel();
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });

        holder.img_edit_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("MaGD", ds_thu.get(position).getMaGD()+"");
                args.putString("Tieude", ds_thu.get(position).getTieuDe()+"");
                args.putString("Ngay", ds_thu.get(position).getNgay()+"");
                args.putString("MoTa", ds_thu.get(position).getMoTa()+"");
                args.putDouble("Tien", ds_thu.get(position).getTien());
                args.putString("Maloai", ds_thu.get(position).getMaLoai()+"");

                Bottom_sheet_edit_thu bottom_sheet = new Bottom_sheet_edit_thu();
                //bottom_sheet.show(((AppCompatActivity)context).getSupportFragmentManager(),"TAG");
                bottom_sheet.setArguments(args);
                bottom_sheet.show(((AppCompatActivity) context).getSupportFragmentManager(),bottom_sheet.getTag());
            }
        });

    }
    @Override
    public int getItemCount() {
        return ds_thu.size();
    }

    public void capnhat(){
        ds_thu = giaodich_dao.getKhoanThu_Chi("Thu");
        khoanthu_adapter = new Khoanthu_Adapter(ds_thu,context);
        rv_tthu.setAdapter(khoanthu_adapter);

    }


}


