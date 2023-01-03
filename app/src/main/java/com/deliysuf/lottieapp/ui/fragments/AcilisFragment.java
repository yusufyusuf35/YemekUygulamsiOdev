package com.deliysuf.lottieapp.ui.fragments;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deliysuf.lottieapp.R;
import com.deliysuf.lottieapp.data.Restorant;
import com.deliysuf.lottieapp.databinding.FragmentAcilisBinding;
import com.deliysuf.lottieapp.ui.adapters.RestorantAdapter;
import com.deliysuf.lottieapp.ui.adapters.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class AcilisFragment extends Fragment {
private ViewPagerAdapter pagerAdapter;
private RestorantAdapter restorantAdapter;
private FragmentAcilisBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       ArrayList<String> resimList = new ArrayList<>();
       resimList.add("burgerindirim");
       resimList.add("doner");
       resimList.add("durumindirim");
       ArrayList<Restorant> restorantList = new ArrayList<>();
       Restorant dominos = new Restorant("Dominos pizza" , "dominos");
       Restorant mcdonald = new Restorant("Mcdonals","mcdonas");
       Restorant ramiz = new Restorant("Burger King" , "king");
       Restorant kofteciYusuf = new Restorant("Köfteci Yusuf" , "yusuf");
       restorantList.add(dominos);
       restorantList.add(mcdonald);
       restorantList.add(ramiz);
       restorantList.add(kofteciYusuf);
        binding = DataBindingUtil.inflate(inflater ,R.layout.fragment_acilis, container , false);

        restorantAdapter = new RestorantAdapter(requireContext() , restorantList);
        pagerAdapter = new ViewPagerAdapter(requireContext() , resimList , binding.viewPager22);
        binding.setViewPagerAdapter(pagerAdapter);
        binding.setAdapter(restorantAdapter);
       calback();

        return binding.getRoot();
    }



    private Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
            binding.viewPager22.setCurrentItem(binding.viewPager22.getCurrentItem()+1);
        }
    };

    public void calback(){
        binding.viewPager22.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.viewPager22.removeCallbacks(slideRunnable);
                binding.viewPager22.postDelayed(slideRunnable , 2000);
            }
        });
    }
    public void search(){
        binding.searcBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}