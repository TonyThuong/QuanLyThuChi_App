package com.example.asmm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.asmm.fragment.Fragment_gioithieu;
import com.example.asmm.fragment.Fragment_phanloai;
import com.example.asmm.fragment.Fragment_thongke;
import com.example.asmm.fragment.Fragment_thu;

import com.example.asmm.fragment.NHAPHANPHOI_Fragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    DrawerLayout dr_ly;
    NavigationView nv;
    Toolbar tb;
    ActionBarDrawerToggle drawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dr_ly = findViewById(R.id.dr_ly);
        tb = findViewById(R.id.tg_bar);
        nv = findViewById(R.id.nv_view);
//        change color status bar
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.white));

        dr_ly.addDrawerListener(drawerToggle);
        drawerToggle = setupDrawerToggle();

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState == null){
            nv.setCheckedItem(R.id.thongke);
            getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_thongke()).commit();
            setTitle("Thống kê");
        }

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                Class fragmentClass = null;
                switch (item.getItemId()){
//                    case R.id.khoanthu:
//                        fragmentClass = Fragment_thu.class;
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_thu()).commit();
//                        break;
//                    case R.id.khoanchi:
//                        fragmentClass = Fragment_phanloai.class;
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_phanloai()).commit();
//                        break;
                    case R.id.thongke:
                        fragmentClass = Fragment_thongke.class;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_thongke()).commit();
                        break;
                    case R.id.NhaPhanPhoi:
                        fragmentClass = NHAPHANPHOI_Fragment.class;
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_thongke()).commit();
                        break;
//                    case R.id.gioithieu:
//                        fragmentClass = Fragment_gioithieu.class;
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_gioithieu()).commit();
//                        break;
                    case R.id.thoat:
                        Intent i = new Intent(MainActivity.this,Loginl.class);
                        startActivity(i);
                        break;
                    default:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fr_ly,new Fragment_thu()).commit();
                }
                try {
                    fragment = (Fragment) fragmentClass.newInstance();
                }catch (Exception e){
                    e.printStackTrace();
                }
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fr_ly,fragment).commit();

                item.setChecked(true);
                setTitle(item.getTitle());
                dr_ly.closeDrawers();
                return true;
            }
        });

    }
    private ActionBarDrawerToggle setupDrawerToggle(){
        return new ActionBarDrawerToggle(MainActivity.this,dr_ly,tb,R.string.Open,R.string.Close);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
