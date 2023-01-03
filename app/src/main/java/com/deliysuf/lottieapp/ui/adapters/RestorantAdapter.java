package com.deliysuf.lottieapp.ui.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.deliysuf.lottieapp.R;
import com.deliysuf.lottieapp.data.Restorant;
import com.deliysuf.lottieapp.databinding.RestorantItemBinding;
import com.deliysuf.lottieapp.ui.fragments.AcilisFragmentDirections;

import java.util.List;

public class RestorantAdapter extends RecyclerView.Adapter<RestorantAdapter.RestorantHolder> {
    private Context mcontext;
    private List<Restorant> restorantList;

    public RestorantAdapter(Context mcontext, List<Restorant> restorantList) {
        this.mcontext = mcontext;
        this.restorantList = restorantList;
    }
    public class RestorantHolder extends RecyclerView.ViewHolder{
        private RestorantItemBinding binding ;
        public RestorantHolder( RestorantItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public RestorantHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RestorantItemBinding binding = RestorantItemBinding.inflate(LayoutInflater.from(mcontext) , parent , false);

        return new RestorantHolder(binding);
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onBindViewHolder(@NonNull RestorantHolder holder, int position) {
        Restorant restorant = restorantList.get(position);

        holder.binding.restorantText.setText(restorant.getAd());
        holder.binding.restorantImage.setImageResource(mcontext.getResources().getIdentifier(restorant.getResim_ad() , "drawable",mcontext.getOpPackageName()));
        holder.binding.restorantImage.setOnClickListener(view -> {
            AcilisFragmentDirections.ToAnasayfaFragment direction = AcilisFragmentDirections.toAnasayfaFragment(restorant);
            Navigation.findNavController(view).navigate(direction);
        });
    }

    @Override
    public int getItemCount() {
        return restorantList.size();
    }


}
