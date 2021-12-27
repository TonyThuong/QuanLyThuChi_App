package com.example.asmm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.asmm.R;
import com.example.asmm.adapter.TabAdapter;
import com.example.asmm.tabfragment.Tab_Chi;
import com.example.asmm.tabfragment.Tab_Thu;
import com.google.android.material.tabs.TabLayout;


import java.util.zip.Inflater;

public class Fragment_thu extends Fragment {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thu, container , false);

        viewPager = view.findViewById(R.id.viewPage);
        tabLayout = view.findViewById(R.id.tabLayout);

        adapter = new TabAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new Tab_Thu(),"Khoản thu");
        adapter.addFragment(new Tab_Chi(),"Khoản chi");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }
}

