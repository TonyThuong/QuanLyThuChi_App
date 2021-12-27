package com.example.asmm.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.asmm.R;
import com.example.asmm.dao.ThongKeDao;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Fragment_thongke extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongke, container , false);

        BottomNavigationView bottomNavigationView = view.findViewById(R.id.bottom_navi);
        if (savedInstanceState == null){
            loadFragment(new Fragment_tk_thu());
        }

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment;
                switch (item.getItemId()){
                    case R.id.tk_thu:
                        fragment = new Fragment_tk_thu();
                        loadFragment(fragment);
                        break;
                    case R.id.tk_chi:
                        fragment = new Fragment_tk_chi();
                        loadFragment(fragment);
                        break;
                }
                return false;
            }
        });
        return view;
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fr_ly_tk, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}

