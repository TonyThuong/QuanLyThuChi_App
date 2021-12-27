package com.example.asmm.tabfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asmm.R;
import com.example.asmm.adapter.Khoanthu_Adapter;
import com.example.asmm.dao.GiaoDichDao;
import com.example.asmm.dialog.Bottom_sheet_them_thu;
import com.example.asmm.model.GiaoDich;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Tab_Thu extends Fragment {
    FloatingActionButton fl_thu;
    public static RecyclerView rv_tthu;
    public static ArrayList<GiaoDich> ds_khoanthu;
    public static Khoanthu_Adapter khoanthu_adapter;
    GiaoDichDao gd_dao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_thu,container,false);
        fl_thu = view.findViewById(R.id.fl_add_thu);
        rv_tthu = view.findViewById(R.id.rv_thu);
        rv_tthu.setLayoutManager(new LinearLayoutManager(getContext()));

        fl_thu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("trangthai", "Loai thu");

                Bottom_sheet_them_thu bottom_sheet = new Bottom_sheet_them_thu();
                bottom_sheet.setArguments(args);
                bottom_sheet.show(getFragmentManager(),bottom_sheet.getTag());
            }
        });

        ds_khoanthu = new ArrayList<>();
        gd_dao =new GiaoDichDao(getContext());

        ds_khoanthu = gd_dao.getKhoanThu_Chi("Thu");
        khoanthu_adapter = new Khoanthu_Adapter(ds_khoanthu,getContext());
        rv_tthu.setAdapter(khoanthu_adapter);

        return view;
    }
}

