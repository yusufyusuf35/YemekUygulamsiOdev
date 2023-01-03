package com.deliysuf.lottieapp.ui.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.deliysuf.lottieapp.databinding.InidirimCardBinding;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ResimHolder> {
    private Context mcontext;
    private List<String> listIndirim;
    private ViewPager2 viewPager2;

    public class ResimHolder extends RecyclerView.ViewHolder{
        private InidirimCardBinding binding;
        public ResimHolder(@NonNull InidirimCardBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
    public ViewPagerAdapter(Context mcontext, List<String> listIndirim , ViewPager2 viewPager2) {
        this.mcontext = mcontext;
        this.listIndirim = listIndirim;
        this.viewPager2 = viewPager2;
    }

    @NonNull
    @Override
    public ResimHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        InidirimCardBinding binding = InidirimCardBinding.inflate(LayoutInflater.from(mcontext),parent,false);
        return new ResimHolder(binding);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void onBindViewHolder(@NonNull ResimHolder holder, int position) {
         String name = listIndirim.get(position);

          holder.binding.indirimImage.setImageResource(mcontext.getResources().getIdentifier(name,"drawable",mcontext.getOpPackageName()));
          if(position == listIndirim.size()-2){
              viewPager2.post(sliderRunnable);
          }

    }

    @Override
    public int getItemCount() {
        return listIndirim.size();
    }

    private Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
         listIndirim.addAll(listIndirim);
         notifyDataSetChanged();
        }
    };


}
