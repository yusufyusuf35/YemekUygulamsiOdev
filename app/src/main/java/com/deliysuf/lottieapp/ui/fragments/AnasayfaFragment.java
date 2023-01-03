package com.deliysuf.lottieapp.ui.fragments;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuProvider;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.SearchView;

import com.deliysuf.lottieapp.R;
import com.deliysuf.lottieapp.Util.Siralama;
import com.deliysuf.lottieapp.data.Restorant;
import com.deliysuf.lottieapp.data.Yemek;
import com.deliysuf.lottieapp.databinding.AdetDialogBinding;
import com.deliysuf.lottieapp.databinding.FragmentAnasayfaBinding;
import com.deliysuf.lottieapp.databinding.YemekListAdapterBinding;
import com.deliysuf.lottieapp.ui.adapters.YemeklerListAdapter;
import com.deliysuf.lottieapp.ui.viewmodels.AnasayfFragmentViewModel;

import java.util.Collections;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AnasayfaFragment extends Fragment implements SearchView.OnQueryTextListener {
private FragmentAnasayfaBinding binding;
private YemeklerListAdapter adapter;
private AnasayfFragmentViewModel viewModel;


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
          binding = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa,container,
                  false);
        ((AppCompatActivity)getActivity()).setSupportActionBar(binding.toolbar);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);



        binding.toolbar.setNavigationOnClickListener(view ->{
            Navigation.findNavController(view).navigate(R.id.acilisFragment);
        });

       viewModel.yemeklerList.observe(getViewLifecycleOwner(),list -> {

           adapter = new YemeklerListAdapter(requireContext(),list,viewModel);
           binding.setAdapter(adapter);


       });


       takeRestorant();
           menu();
           touch();

        return binding.getRoot();
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).
                get(AnasayfFragmentViewModel.class);
    }






    @Override
    public void onResume() {
        super.onResume();
        viewModel.yemekleriYukle();
        viewModel.sepet();
    }




    public void menu() {
        requireActivity().addMenuProvider(new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.anasayfa_menu, menu);
                MenuItem item = menu.findItem(R.id.actionAra);
                SearchView searchView = (SearchView) item.getActionView();
                searchView.setOnQueryTextListener(AnasayfaFragment.this);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                return false;
            }
        } , getViewLifecycleOwner() , Lifecycle.State.RESUMED);

    }

    @Override
    public boolean onQueryTextSubmit(String s) {
       viewModel.ara(s);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        viewModel.ara(s);
        return true;
    }




    public void touch(){
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN ,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                Collections.swap(adapter.getYemeklerList() , viewHolder.getAdapterPosition() , target.getAdapterPosition());
                adapter.notifyItemMoved(viewHolder.getAdapterPosition(),target.getAdapterPosition());
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {


            }
        }).attachToRecyclerView(binding.yemeklerListesi);
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void takeRestorant(){
      Restorant restoran = AnasayfaFragmentArgs.fromBundle(getArguments()).getRestorant();
        binding.secilenRestorant.setImageResource(requireContext().getResources().getIdentifier(restoran.getResim_ad() , "drawable" , requireContext().getOpPackageName()));
        binding.textView8.setText(restoran.getAd());
    }





}