package com.example.asmm.dialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.example.asmm.tabfragment.Tab_Thu.ds_khoanthu;
import static com.example.asmm.tabfragment.Tab_Thu.khoanthu_adapter;
import static com.example.asmm.tabfragment.Tab_Thu.rv_tthu;

public class Bottom_sheet_edit_thu extends BottomSheetDialogFragment {
    EditText edt_tieude,edt_tien,edt_mota;
    TextView tv_ngay;
    Spinner sp_pl_giaodich;
    Button btn_edit_giaodich;
    GiaoDichDao giaoDichDao;
    ArrayList<Phanloai> ds_loai_thu;
    ArrayList<GiaoDich> ds_thu;
    Adapter_sp_thu adapterSpThu;
    int id;

    public Bottom_sheet_edit_thu(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.bottom_sheet_edit_khoanthu,container,false);
        edt_tieude = view.findViewById(R.id.edt_tieude);
        edt_tien = view.findViewById(R.id.edt_tien);
        edt_mota = view.findViewById(R.id.edt_mota);
        tv_ngay = view.findViewById(R.id.tve_ngay);
        sp_pl_giaodich = view.findViewById(R.id.sp_pl_giaodich_e);
        btn_edit_giaodich = view.findViewById(R.id.btn_giaodich);

        Bundle editBun =getArguments();
        id = Integer.parseInt(editBun.getString("MaGD"));
        String tieu_de =editBun.getString("TieuDe");
        String ngay = editBun.getString("Ngay");
        double tien = editBun.getDouble("Tien");
        String mo_ta = editBun.getString("MoTa");
        String ma_loai = editBun.getString("MaLoai");

        edt_tieude.setText(tieu_de);
        DecimalFormat decimalFormat = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        decimalFormat.applyPattern("#,###,###,###");
        String stFormat =decimalFormat.format(tien);

        edt_tien.setText(stFormat);
        edt_tien.addTextChangedListener(onTextChangedListener());
        edt_mota.setText(mo_ta);
        tv_ngay.setText(ngay);

        giaoDichDao =new GiaoDichDao(getContext());
        ds_loai_thu = new ArrayList<>();
        ds_loai_thu = giaoDichDao.getThu();
        String te  =giaoDichDao.getTen(ma_loai);

        adapterSpThu =new Adapter_sp_thu(ds_loai_thu,getContext());
        sp_pl_giaodich.setAdapter(adapterSpThu);

        for (int i = 0;i < ds_loai_thu.size();i++){
            if (ds_loai_thu.get(i).getTenloai().equals(te)){
                sp_pl_giaodich.setSelection(i);
                break;
            }
        }

        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);

        final int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        final int months = calendar.get(Calendar.MONTH);
        final int years = calendar.get(Calendar.YEAR);

        tv_ngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar1 = Calendar.getInstance();
                int date = calendar1.get(Calendar.DAY_OF_MONTH);
                int month = calendar1.get(Calendar.MONTH);
                int year = calendar1.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        calendar1.set(i,i1,i2);
                        tv_ngay.setText(simpleDateFormat.format(calendar1.getTime()));
                    }
                },years,months,dayOfWeek);
                datePickerDialog.show();

            }
        });

        btn_edit_giaodich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = tv_ngay.getText().toString();
                String tieude = edt_tieude.getText().toString();
                String stT =edt_tien.getText().toString();
                int l = 0;
                try {
                    l  = DecimalFormat.getNumberInstance().parse(stT).intValue();
                    System.out.println(l); //111111.23
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                DecimalFormat format = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                format.setParseBigDecimal(true);
                BigDecimal number = null;
                try {
                    number = (BigDecimal) format.parse(stT);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int so = Integer.parseInt(number+"");

                String mota = edt_mota.getText().toString();
                int index = sp_pl_giaodich.getSelectedItemPosition();
                int maloai = ds_loai_thu.get(index).getId_pl();

                GiaoDich gd =new GiaoDich(id,tieude,date,so,mota,maloai);
                giaoDichDao = new GiaoDichDao(getContext());
                giaoDichDao.update(gd);

                capnhat();
                Toast.makeText(getContext(), "Cập nhật thành công"+number, Toast.LENGTH_SHORT).show();
                dismiss();

            }
        });
        return view;
    }

    public void capnhat(){

        ds_khoanthu = giaoDichDao.getKhoanThu_Chi("Thu");
        khoanthu_adapter = new Khoanthu_Adapter(ds_khoanthu,getContext());
        rv_tthu.setAdapter(khoanthu_adapter);
    }

    private TextWatcher onTextChangedListener() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                edt_tien.removeTextChangedListener(this);

                try {
                    String originalString = s.toString();

                    Long longval;
                    if (originalString.contains(",")) {
                        originalString = originalString.replaceAll(",", "");
                    }
                    longval = Long.parseLong(originalString);

                    DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
                    formatter.applyPattern("#,###,###,###");
                    String formattedString = formatter.format(longval);

                    //setting text after format to EditText
                    edt_tien.setText(formattedString);
                    edt_tien.setSelection(edt_tien.getText().length());
                } catch (NumberFormatException nfe) {
                    nfe.printStackTrace();
                }

                edt_tien.addTextChangedListener(this);
            }
        };
    }
}

