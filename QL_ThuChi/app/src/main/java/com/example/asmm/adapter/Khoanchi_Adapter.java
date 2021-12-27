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
import com.example.asmm.dialog.Bottom_sheet_edit_chi;
import com.example.asmm.model.GiaoDich;

import java.text.DecimalFormat;
import java.util.ArrayList;

import static com.example.asmm.tabfragment.Tab_Chi.ds_khoanchi;
import static com.example.asmm.tabfragment.Tab_Chi.khoanchi_adapter;
import static com.example.asmm.tabfragment.Tab_Chi.rv_chi;
import static com.example.asmm.tabfragment.Tab_Thu.ds_khoanthu;
import static com.example.asmm.tabfragment.Tab_Thu.khoanthu_adapter;
import static com.example.asmm.tabfragment.Tab_Thu.rv_tthu;

public class Khoanchi_Adapter extends RecyclerView.Adapter<Khoanchi_Adapter.MyViewHolder> {
    private ArrayList<GiaoDich> ds_chi;
    private Context context;
    GiaoDichDao gd_dao;

    public Khoanchi_Adapter(ArrayList<GiaoDich> ds_chi
            , Context context) {
        this.ds_chi = ds_chi;
        this.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_tieude;
        public TextView tv_ngay;
        public TextView tv_tien;
        private ImageView img_xoa_chi;
        private ImageView img_edit_chi;
        public MyViewHolder(View v) {
            super(v);
            tv_tieude = v.findViewById(R.id.tv_tieu_de_chi);
            tv_ngay = v.findViewById(R.id.tv_ngay_chi);
            tv_tien = v.findViewById(R.id.tv_tien_chi);
            img_xoa_chi = v.findViewById(R.id.iv_xoa_chi);
            img_edit_chi = v.findViewById(R.id.iv_sua_chi);
        }
    }

    @Override
    public Khoanchi_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_khoan_chi, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.tv_tieude.setText(ds_chi.get(position).getTieuDe());
        holder.tv_ngay.setText(ds_chi.get(position).getNgay()+"");
        //Dinh dang hien thi so tien
        DecimalFormat formatter = new DecimalFormat("#,###");
        String s = formatter.format(ds_chi.get(position).getTien());
        holder.tv_tien.setText(s);
        holder.img_xoa_chi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có chắc muốn xóa "+ds_chi.get(position).getTieuDe());
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                gd_dao = new GiaoDichDao(context);
                                gd_dao.delete(ds_chi.get(position).getMaGD());
                                Toast.makeText(context, "Xóa thành công "+ds_chi.get(position).getTieuDe(), Toast.LENGTH_SHORT).show();
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

        holder.img_edit_chi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("MaGD", ds_chi.get(position).getMaGD()+"");
                args.putString("Tieude", ds_chi.get(position).getTieuDe()+"");
                args.putString("MoTa", ds_chi.get(position).getMoTa()+"");
                args.putDouble("Tien", ds_chi.get(position).getTien());
                args.putString("Maloai", ds_chi.get(position).getMaLoai()+"");

                Bottom_sheet_edit_chi bottom_sheet = new Bottom_sheet_edit_chi();
                //bottom_sheet.show(((AppCompatActivity)context).getSupportFragmentManager(),"TAG");
                bottom_sheet.setArguments(args);
                bottom_sheet.show(((AppCompatActivity) context).getSupportFragmentManager(),bottom_sheet.getTag());
            }
        });

    }
    @Override
    public int getItemCount() {
        return ds_chi.size();
    }

    public void capnhat(){
        ds_chi = gd_dao.getKhoanThu_Chi("Chi");
        khoanchi_adapter =new Khoanchi_Adapter(ds_chi,context);
        rv_chi.setAdapter(khoanchi_adapter);
    }


}

