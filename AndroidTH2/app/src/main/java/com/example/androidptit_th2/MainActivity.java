package com.example.androidptit_th2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.androidptit_th2.adapter.SongAdapter;
import com.example.androidptit_th2.adapter.ViewPagerAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView navigationView;
    private ViewPager viewPager;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.nav);
        viewPager = findViewById(R.id.viewPager);

        ViewPagerAdapter adapter =new ViewPagerAdapter(getSupportFragmentManager(), 3);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch(position){
                    case 0:navigationView.getMenu().findItem(R.id.list).setChecked(true);
                        break;

                    case 1:navigationView.getMenu().findItem(R.id.info).setChecked(true);
                        break;

                    case 2:navigationView.getMenu().findItem(R.id.search).setChecked(true);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.list) {
                    viewPager.setCurrentItem(0);
                } else if (itemId == R.id.info) {
                    viewPager.setCurrentItem(1);
                } else if (itemId == R.id.search) {
                    viewPager.setCurrentItem(2);
                }
                return true;
            }
        });
    }
}