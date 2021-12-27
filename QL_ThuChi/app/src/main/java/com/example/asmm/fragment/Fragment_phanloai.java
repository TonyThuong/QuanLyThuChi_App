package com.example.asmm.fragment;

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
import com.example.asmm.adapter.PhanLoaiAdapter;
import com.example.asmm.dao.PhanloaiDao;
import com.example.asmm.dialog.Bottom_sheet;
import com.example.asmm.model.Phanloai;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Fragment_phanloai extends Fragment {
    FloatingActionButton fl;
    public static PhanLoaiAdapter phanLoaiAdapters;
    PhanloaiDao phanloaiDao;
    public static RecyclerView recyclerView_pl;
    ArrayList<Phanloai> list_pl;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phanloai, container , false);
        fl = view.findViewById(R.id.fl_pl);
        recyclerView_pl = view.findViewById(R.id.rv_pl);
        recyclerView_pl.setLayoutManager(new LinearLayoutManager(getContext()));
        list_pl = new ArrayList<>();
        phanloaiDao = new PhanloaiDao(getContext());

        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bottom_sheet bottom_sheet = new Bottom_sheet();
                bottom_sheet.show(getFragmentManager(),"TAG");
            }
        });

        list_pl = phanloaiDao.readAll();
        phanLoaiAdapters = new PhanLoaiAdapter(list_pl,getContext());
        recyclerView_pl.setAdapter(phanLoaiAdapters);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}

