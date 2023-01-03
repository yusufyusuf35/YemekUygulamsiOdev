package com.deliysuf.lottieapp.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.deliysuf.lottieapp.data.SepetYemek;
import com.deliysuf.lottieapp.databinding.SepetItemBinding;
import com.deliysuf.lottieapp.databinding.YemekListAdapterBinding;
import com.deliysuf.lottieapp.ui.viewmodels.SepetViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SepetYemeklerAdapter extends ListAdapter<SepetYemek,SepetYemeklerAdapter.SepetCardViewHolder>{
    private Context mcontext;
    private List<SepetYemek> SepetYemeklerList;
    private int toplamFiyat;
    private SepetViewModel viewModel;

    public void setToplamFiyat(int toplamFiyat) {
        this.toplamFiyat = toplamFiyat;
    }

    public int getToplamFiyat() {
        return toplamFiyat;
    }








public static final DiffUtil.ItemCallback<SepetYemek> diffUtil = new DiffUtil.ItemCallback<SepetYemek>() {
    @Override
    public boolean areItemsTheSame(@NonNull SepetYemek oldItem, @NonNull SepetYemek newItem) {
        return oldItem.getSepet_yemek_id() == newItem.getSepet_yemek_id();
    }

    @Override
    public boolean areContentsTheSame(@NonNull SepetYemek oldItem, @NonNull SepetYemek newItem) {
        return oldItem.getYemek_adi().equals(newItem.getYemek_adi());
    }
};

    public SepetYemeklerAdapter(Context context,List<SepetYemek> yemekler , SepetViewModel viewModel) {
        super(diffUtil);
        this.mcontext = context;
        this.SepetYemeklerList = yemekler;
        this.viewModel = viewModel;


    }


    public List<SepetYemek> getSepetYemeklerList() {
        return SepetYemeklerList;
    }


    public class SepetCardViewHolder extends RecyclerView.ViewHolder{
        private SepetItemBinding binding;
        public SepetCardViewHolder(@NonNull SepetItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }
    @NonNull
    @Override
    public SepetCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SepetItemBinding binding = SepetItemBinding.
                inflate(LayoutInflater.from(mcontext),
                        parent,false);
        return new SepetCardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SepetCardViewHolder holder, int position) {
         SepetYemek yemek = getItem(position);
         SepetItemBinding b = holder.binding;
        //http://kasimadalan.pe.hu/yemekler/resimler/ b.card.setAnimation(AnimationUtils.makeInChildBottomAnimation(mcontext));
         b.setYemek(yemek);
         Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/"+yemek.getYemek_resim_adi()).into(b.sepetYemekImage);
         b.sepetTextFiyat.setText(fiyatHesapla(yemek)+" TL");
    }
    public SepetYemek yemekPosition(int position){

    return getItem(position);
    }
    public int fiyatHesapla(SepetYemek yemek ){
        int money = yemek.getYemek_fiyat() * yemek.getYemek_siparis_adet();

     return money;
    }
    public int toplamFiyatDondur(){
         toplamFiyat = 0;
        if(SepetYemeklerList.size() == 1){
            toplamFiyat = 0;

        }
        for(int i = 0 ; i < SepetYemeklerList.size() ; i++){
           toplamFiyat = toplamFiyat + SepetYemeklerList.get(i).getYemek_fiyat() * SepetYemeklerList.get(i).getYemek_siparis_adet();
        }
        return toplamFiyat;
    }

}
