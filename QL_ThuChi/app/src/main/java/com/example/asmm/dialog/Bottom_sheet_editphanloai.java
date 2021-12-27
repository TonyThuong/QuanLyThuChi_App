package com.example.asmm.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

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

public class Bottom_sheet_editphanloai extends BottomSheetDialogFragment {
    Button btn_cn;
    EditText et_tenloai_edit;
    Spinner tt_edit;
    PhanloaiDao dao;
    ArrayList<Phanloai> ds_phanloai;
    public Bottom_sheet_editphanloai(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_edit_pl,container,false);
        et_tenloai_edit = view.findViewById(R.id.edt_tenloai_edit);
        tt_edit= view.findViewById(R.id.sp_trangthai_edit);
        btn_cn = view.findViewById(R.id.btnEditpl);
        dao = new PhanloaiDao(getContext());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tt_edit.setAdapter(adapter);

        Bundle mBundle = getArguments();
        final int idd = Integer.parseInt(mBundle.getString("id"));
        String ten_loai = mBundle.getString("tenloai");
        String trang_thai = mBundle.getString("trangthai");
        selectSpinnerValue(tt_edit, trang_thai);

        et_tenloai_edit.setText(ten_loai);

        btn_cn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenloai = et_tenloai_edit.getText().toString();
                String trangthai = tt_edit.getSelectedItem().toString();
                Phanloai pl = new Phanloai(idd,tenloai,trangthai);
                dao.capnhat(pl);
                capnhat();
                dismiss();
            }
        });

        return view;
    }

    private void selectSpinnerValue(Spinner spinner, String myString)
    {
        int index = 0;
        for(int i = 0; i < spinner.getCount(); i++){
            if(spinner.getItemAtPosition(i).toString().equals(myString)){
                spinner.setSelection(i);
                break;
            }
        }
    }

    public void capnhat(){
        ds_phanloai = dao.readAll();
        phanLoaiAdapters = new PhanLoaiAdapter(ds_phanloai,getContext());
        recyclerView_pl.setAdapter(phanLoaiAdapters);
    }
}
