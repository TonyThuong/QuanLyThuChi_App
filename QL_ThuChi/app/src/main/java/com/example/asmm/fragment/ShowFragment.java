//package com.example.asmm.fragment;
//
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.asmm.R;
//import com.example.asmm.adapter.PhanLoaiAdapter;
//import com.example.asmm.dao.NhaPhanPhoiDao;
//import com.example.asmm.dao.PhanloaiDao;
//import com.example.asmm.dialog.Bottom_sheet;
//import com.example.asmm.model.NhaPhanPhoi;
//import com.example.asmm.model.Phanloai;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//
//
//public class ShowFragment extends Fragment {
//
//    FloatingActionButton fl;
//    public static PhanLoaiAdapter ShowAdapters;
//    NhaPhanPhoiDao NhaPhanPhoiDao;
//    public static RecyclerView recyclerView_pl;
//    ArrayList<NhaPhanPhoi> ds_npp;
//
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_phanloai, container , false);
//        fl = view.findViewById(R.id.ds_npp);
//        recyclerView_pl = view.findViewById(R.id.rv_pl);
//        recyclerView_pl.setLayoutManager(new LinearLayoutManager(getContext()));
//        ds_npp = new ArrayList<>();
//        NhaPhanPhoiDao = new NhaPhanPhoiDao(getContext());
//
////        fl.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Bottom_sheet bottom_sheet = new Bottom_sheet();
////                bottom_sheet.show(getFragmentManager(),"TAG");
////            }
////        });
//
//        ds_npp = NhaPhanPhoiDao.getDanhSach();
//        ShowAdapters = new PhanLoaiAdapter(ds_npp,getContext());
//        recyclerView_pl.setAdapter(ShowAdapters);
//
//        return view;
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//}