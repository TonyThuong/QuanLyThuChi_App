package com.example.asmm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.asmm.R;
import com.example.asmm.dao.ThongKeDao;

public class Fragment_tk_chi extends Fragment {
    TextView tong;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tk_chi,container,false);
        tong = view.findViewById(R.id.kq_thu);
        double tongChi = ThongKeDao.tongTienTheoTT(getContext(),"Chi");
        tong.setText("Tổng chi: "+tongChi);
        return view;
    }
}

