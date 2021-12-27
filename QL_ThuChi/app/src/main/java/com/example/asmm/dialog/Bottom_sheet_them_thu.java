package com.example.asmm.dialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.asmm.R;
import com.example.asmm.adapter.Adapter_sp_thu;
import com.example.asmm.adapter.Khoanthu_Adapter;
import com.example.asmm.dao.GiaoDichDao;
import com.example.asmm.model.GiaoDich;
import com.example.asmm.model.Phanloai;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.asmm.tabfragment.Tab_Thu.ds_khoanthu;
import static com.example.asmm.tabfragment.Tab_Thu.khoanthu_adapter;
import static com.example.asmm.tabfragment.Tab_Thu.rv_tthu;

public class Bottom_sheet_them_thu extends BottomSheetDialogFragment {
    EditText edt_tieude,edt_tien,edt_mota;
    TextView tv_ngay, tv_trangthai;
    Spinner sp_pl_giaodich;
    Button btn_them_giaodich;
    GiaoDichDao giaodich_dao;
    ArrayList<Phanloai> ds_loai_thu;
    ArrayList<GiaoDich> ds_thu;
    Adapter_sp_thu adapterSpThu;
    String trangthai_;

    public Bottom_sheet_them_thu(){
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_gd, container, false);
        edt_tieude = view.findViewById(R.id.edt_tieude);
        edt_tien = view.findViewById(R.id.edt_tien);
        edt_mota = view.findViewById(R.id.edt_mota);
        tv_ngay = view.findViewById(R.id.tv_ngay);
        tv_trangthai = view.findViewById(R.id.tv_trangthai);
        sp_pl_giaodich = view.findViewById(R.id.sp_pl_giaodich);
        btn_them_giaodich = view.findViewById(R.id.btnAddgd);

        //getBundle
        Bundle getdata = getArguments();
        trangthai_= getdata.getString("trangthai");
        tv_trangthai.setText(trangthai_);

        giaodich_dao = new GiaoDichDao(getContext());
        ds_loai_thu = new ArrayList<>();

        ds_loai_thu = giaodich_dao.getThu();
        adapterSpThu = new Adapter_sp_thu(ds_loai_thu,getContext());
        sp_pl_giaodich.setAdapter(adapterSpThu);

        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);

        final int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        final int months = cal.get(Calendar.MONTH);
        final int years = cal.get(Calendar.YEAR);

        tv_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                int date = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        calendar.set(i,i1,i2);
                        tv_ngay.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },years,months,dayOfWeek);
                datePickerDialog.show();
            }
        });


        btn_them_giaodich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String Ngay = tv_ngay.getText().toString();
                String date = tv_ngay.getText().toString();
                String tieude = edt_tieude.getText().toString();
                double tien = Double.parseDouble(edt_tien.getText().toString());
                //String phanloai = sp_pl_giaodich.getSelectedItem().toString();
                String mota = edt_mota.getText().toString();
                int index = sp_pl_giaodich.getSelectedItemPosition();
                int Maloai = ds_loai_thu.get(index).getId_pl();

                GiaoDich gd = new GiaoDich(tieude,date,tien,mota,Maloai);
                giaodich_dao = new GiaoDichDao(getContext());
                try {
                    giaodich_dao.them(gd);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                capnhat();
                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        return view;
    }

    public void capnhat(){
        ds_thu = giaodich_dao.getKhoanThu_Chi("Thu");
        khoanthu_adapter = new Khoanthu_Adapter(ds_thu,getContext());
        rv_tthu.setAdapter(khoanthu_adapter);
    }
}
