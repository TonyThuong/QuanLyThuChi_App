package com.example.asmm.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.asmm.R;
import com.example.asmm.adapter.PhanLoaiAdapter;
import com.example.asmm.dao.PhanloaiDao;
import com.example.asmm.model.Phanloai;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import static com.example.asmm.fragment.Fragment_phanloai.phanLoaiAdapters;
import static com.example.asmm.fragment.Fragment_phanloai.recyclerView_pl;

public class Bottom_sheet extends BottomSheetDialogFragment {
    Button btn_them;
    EditText et_tenloai;
    Spinner tt;
    PhanloaiDao dao;
    ArrayList<Phanloai> ds_phanloai;
    public Bottom_sheet(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_pl,container,false);
        et_tenloai = view.findViewById(R.id.edt_tenloai);
        tt= view.findViewById(R.id.sp_trangthai);
        btn_them = view.findViewById(R.id.btnAddpl);
        dao = new PhanloaiDao(getContext());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tt.setAdapter(adapter);

        btn_them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloai = et_tenloai.getText().toString();
                String trangthai = tt.getSelectedItem().toString();
                Phanloai pl = new Phanloai(tenloai,trangthai);
                dao.them(pl);
                capnhat();
                dismiss();
            }
        });

        return view;
    }

    public void capnhat(){
        ds_phanloai = dao.readAll();
        phanLoaiAdapters = new PhanLoaiAdapter(ds_phanloai,getContext());
        recyclerView_pl.setAdapter(phanLoaiAdapters);
    }
}

