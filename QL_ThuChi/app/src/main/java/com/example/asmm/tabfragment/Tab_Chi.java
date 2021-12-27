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
import com.example.asmm.adapter.Khoanchi_Adapter;
import com.example.asmm.dao.GiaoDichDao;
import com.example.asmm.dialog.Bottom_sheet_them_chi;
import com.example.asmm.dialog.Bottom_sheet_them_thu;
import com.example.asmm.model.GiaoDich;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Tab_Chi extends Fragment {
    public static RecyclerView rv_chi;
    public static Khoanchi_Adapter khoanchi_adapter;
    public static ArrayList<GiaoDich> ds_khoanchi;
    FloatingActionButton fl_chi;
    GiaoDichDao gd_dao;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_chi,container,false);
        rv_chi = view.findViewById(R.id.rv_chi);
        fl_chi = view.findViewById(R.id.fl_add_chi);
        rv_chi.setLayoutManager(new LinearLayoutManager(getContext()));

        fl_chi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("trangthai", "Loai chi");

                Bottom_sheet_them_chi bottom_sheet_chi = new Bottom_sheet_them_chi();
                bottom_sheet_chi.setArguments(args);
                bottom_sheet_chi.show(getFragmentManager(),bottom_sheet_chi.getTag());
            }
        });

        ds_khoanchi = new ArrayList<>();
        gd_dao = new GiaoDichDao(getContext());

        ds_khoanchi = gd_dao.getKhoanThu_Chi("Chi");
        khoanchi_adapter =new Khoanchi_Adapter(ds_khoanchi,getContext());
        rv_chi.setAdapter(khoanchi_adapter);
        return view;
    }
}

