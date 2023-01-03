package com.deliysuf.lottieapp.ui.adapters;

import static android.app.ProgressDialog.show;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.deliysuf.lottieapp.R;
import com.deliysuf.lottieapp.Util.Siralama;
import com.deliysuf.lottieapp.data.SepetYemek;
import com.deliysuf.lottieapp.data.Yemek;
import com.deliysuf.lottieapp.databinding.AdetDialogBinding;
import com.deliysuf.lottieapp.databinding.YemekListAdapterBinding;
import com.deliysuf.lottieapp.retrofit.ApiUtils;
import com.deliysuf.lottieapp.ui.viewmodels.AnasayfFragmentViewModel;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class YemeklerListAdapter extends RecyclerView.Adapter<YemeklerListAdapter.YemekListHolder> {
    private Context mcontext;
    private Dialog dialog;
    private AdetDialogBinding bindng;
    int adet ;

    private List<Yemek> yemeklerList;
    public List<SepetYemek> sepetYemekList;



    public List<Yemek> getYemeklerList() {
        return yemeklerList;
    }
    public void setYemeklerList(List<Yemek> yemeklerList) {
        this.yemeklerList = yemeklerList;
    }
    private AnasayfFragmentViewModel viewModel;

    public YemeklerListAdapter(Context mcontext, List<Yemek> yemeklerList, AnasayfFragmentViewModel viewModel ) {
        this.mcontext = mcontext;
        this.yemeklerList = yemeklerList;
        this.viewModel = viewModel;


    }
    public class YemekListHolder extends RecyclerView.ViewHolder{
      private YemekListAdapterBinding binding;
        public YemekListHolder(YemekListAdapterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public YemekListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        YemekListAdapterBinding binding = YemekListAdapterBinding.
                inflate(LayoutInflater.from(mcontext),
                        parent,false);
         bindng = AdetDialogBinding.inflate(LayoutInflater.from(mcontext),parent,false);

        return new YemekListHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull YemekListHolder holder, int position) {
        dialog = new Dialog(mcontext);
        Yemek yemek = yemeklerList.get(position);
        YemekListAdapterBinding b = holder.binding;
        b.isim.setText(yemek.getYemek_adi().toString());
        b.fiyat.setText(String.valueOf(yemek.getYemek_fiyat()+" TL"));
        Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/"+yemek.getYemek_resim_adi()).into(b.YemekImage);
        b.YemekImage.setOnClickListener(view -> {
            Picasso.get().load("http://kasimadalan.pe.hu/yemekler/resimler/"+yemek.getYemek_resim_adi()).into(bindng.resimImage);
             openDialog(yemek);
         });
    }

    @Override
    public int getItemCount() {
        return yemeklerList.size();
    }
    public void openDialog(Yemek yemek){

        adet = 0 ;
        bindng.textAdet.setText(String.valueOf(adet));
        bindng.textFiyatDialog.setText("0 TL");
        dialog.setContentView(bindng.getRoot());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



         bindng.artirCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adet = adet+1;
                bindng.textAdet.setText(String.valueOf(adet));
                bindng.textFiyatDialog.setText(String.valueOf(yemek.getYemek_fiyat()*adet)+" TL");
            }
        });
        bindng.azaltCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adet != 0){
                adet = adet-1;
                bindng.textAdet.setText(String.valueOf(adet));
                    bindng.textFiyatDialog.setText(yemek.getYemek_fiyat() * adet +" TL");
                }

            }
        });
        bindng.sepeteEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              List<SepetYemek> a = viewModel.sepetim.getValue();
                 if(a != null){
                 for(SepetYemek i : a){
                  if(i.getYemek_adi().equals(yemek.getYemek_adi())){
                  viewModel.sepettenYemekSil(i.getSepet_yemek_id());}
              }}

                viewModel.sepeteYemekYukle(
                        yemek.getYemek_adi() ,
                        yemek.getYemek_resim_adi() ,
                        yemek.getYemek_fiyat() ,
                        adet ,
                        ApiUtils.kullanici_adi);
                viewModel.sepet();




               Toast.makeText(mcontext , "yemek eklendi" , Toast.LENGTH_LONG).show();

            }

        });
        bindng.yemekAdiAdetText.setText(yemek.getYemek_adi());

        dialog.show();

    }


}
