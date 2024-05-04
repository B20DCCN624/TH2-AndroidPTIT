package com.example.androidptit_th2.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.androidptit_th2.fragment.InforFragment;
import com.example.androidptit_th2.fragment.HomeFragment;
import com.example.androidptit_th2.fragment.SearchFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int pageNum;
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.pageNum=behavior;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0: return new HomeFragment();
            case 1: return new InforFragment();
            case 2: return new SearchFragment();
            default:return new HomeFragment();
        }
    }
    @Override
    public int getCount() {
        return pageNum;
    }
}
