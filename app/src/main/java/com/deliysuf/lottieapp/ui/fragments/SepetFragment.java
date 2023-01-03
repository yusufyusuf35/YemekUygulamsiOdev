package com.deliysuf.lottieapp.ui.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListUpdateCallback;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.deliysuf.lottieapp.R;
import com.deliysuf.lottieapp.Util.Siralama;
import com.deliysuf.lottieapp.data.SepetYemek;
import com.deliysuf.lottieapp.databinding.FragmentSepetBinding;
import com.deliysuf.lottieapp.ui.adapters.SepetYemeklerAdapter;
import com.deliysuf.lottieapp.ui.viewmodels.SepetViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SepetFragment extends Fragment {
    private FragmentSepetBinding binding;
    private SepetViewModel viewModel;
    private SepetYemeklerAdapter  adapter;
    private Bundle listState;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).
                get(SepetViewModel.class);
        listState = new Bundle();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_sepet,
                container ,
                false);
        binding.setSepetFragment(this);
        binding.toolbar3.setTitle("Sepet");
        viewModel.sepetList.observe(getViewLifecycleOwner() , sepetList->{
            adapter = new SepetYemeklerAdapter(requireContext() , sepetList , viewModel);
            adapter.submitList(sepetList);
            binding.setAdapter(adapter);


            if(adapter.getToplamFiyat() == 0 || adapter.getSepetYemeklerList().size() == 0){
                binding.fragmentFiyat.setText(String.valueOf(0)+" TL");
            }
            binding.fragmentFiyat.setText(String.valueOf(adapter.toplamFiyatDondur())+" TL");
            if(listState != null){
                Parcelable stateOfList = listState.getParcelable("LIST");
                binding.sepetRecycler.getLayoutManager().onRestoreInstanceState(stateOfList);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0 ,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

                adapter.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());

                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                viewModel.sepettenYemekSil(adapter.yemekPosition(viewHolder.getAdapterPosition()).getSepet_yemek_id());
                Parcelable state = binding.sepetRecycler.getLayoutManager().onSaveInstanceState();
                listState.putParcelable("LIST" , state);

                if(adapter.getSepetYemeklerList().size() == 1){
                    adapter.setToplamFiyat(0);
                    adapter.getSepetYemeklerList().remove(viewHolder.getAdapterPosition());
                    adapter.notifyDataSetChanged();
                    binding.fragmentFiyat.setText("0 TL");
                }
            }
        }).attachToRecyclerView(binding.sepetRecycler);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.sepettekiYemekleriYukle();
    }





}