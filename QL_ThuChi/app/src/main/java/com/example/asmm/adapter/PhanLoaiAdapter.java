package com.example.asmm.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmm.R;
import com.example.asmm.dao.PhanloaiDao;
import com.example.asmm.dialog.Bottom_sheet;
import com.example.asmm.dialog.Bottom_sheet_editphanloai;
import com.example.asmm.model.Phanloai;

import java.util.ArrayList;

import static com.example.asmm.fragment.Fragment_phanloai.phanLoaiAdapters;
import static com.example.asmm.fragment.Fragment_phanloai.recyclerView_pl;

public class PhanLoaiAdapter extends RecyclerView.Adapter<PhanLoaiAdapter.ViewHolder> {
    private ArrayList<Phanloai> ds_phanloai;
    private Context context;
    PhanloaiDao dao_pl;

    public PhanLoaiAdapter(ArrayList<Phanloai> ds_phanloai,Context context){
        this.ds_phanloai = ds_phanloai;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView ten_pl;
        public TextView tt_pl;
        private ImageView delete_pl;
        private ImageView edit_pl;
        public ViewHolder(@NonNull View v) {
            super(v);
            ten_pl = v.findViewById(R.id.tv_tenloai);
            tt_pl = v.findViewById(R.id.tv_trangthai);
            delete_pl = v.findViewById(R.id.xoa_pl);
            edit_pl = v.findViewById(R.id.sua_pl);
        }
    }

    @NonNull
    @Override
    public PhanLoaiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phanloai,parent,false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PhanLoaiAdapter.ViewHolder holder, final int position) {
        holder.ten_pl.setText(ds_phanloai.get(position).getTenloai());
        holder.tt_pl.setText(ds_phanloai.get(position).getTrangthai());
        holder.delete_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Bạn có muốn xóa "+ds_phanloai.get(position).getId_pl());
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dao_pl = new PhanloaiDao(context);
                                dao_pl.delete(ds_phanloai.get(position).getId_pl());
                                dialog.cancel();
                                capnhat();
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

        holder.edit_pl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("id",ds_phanloai.get(position).getId_pl()+"");
                bundle.putString("tenloai",ds_phanloai.get(position).getTenloai()+"");
                bundle.putString("trangthai",ds_phanloai.get(position).getTrangthai()+"");
                Bottom_sheet_editphanloai bottom_sheet_editphanloai = new Bottom_sheet_editphanloai();
//                bottom_sheet_editphanloai.show(((AppCompatActivity)context).getSupportFragmentManager(),"TAG");
                bottom_sheet_editphanloai.setArguments(bundle);
                bottom_sheet_editphanloai.show(((AppCompatActivity) context).getSupportFragmentManager(),bottom_sheet_editphanloai.getTag());
            }
        });
    }

    @Override
    public int getItemCount() {
        return ds_phanloai.size();
    }

    public void capnhat(){
        ds_phanloai = dao_pl.readAll();
        phanLoaiAdapters = new PhanLoaiAdapter(ds_phanloai,context);
        recyclerView_pl.setAdapter(phanLoaiAdapters);
    }
}

