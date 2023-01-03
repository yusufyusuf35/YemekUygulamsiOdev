package com.deliysuf.lottieapp.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.deliysuf.lottieapp.data.CRUDCevap;
import com.deliysuf.lottieapp.data.SepetYemek;
import com.deliysuf.lottieapp.data.SepetYemeler;
import com.deliysuf.lottieapp.data.Yemek;
import com.deliysuf.lottieapp.data.Yemekler;
import com.deliysuf.lottieapp.retrofit.ApiUtils;
import com.deliysuf.lottieapp.retrofit.YemeklerDao;
import com.deliysuf.lottieapp.room.YemekDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YemeklerDaoRepository {
private MutableLiveData<List<Yemek>> yemeklerList;
private MutableLiveData<List<SepetYemek>> sepetYemekList;
private YemeklerDao yDao;
private YemekDao rDao;

    public MutableLiveData<List<SepetYemek>> getSepetYemekList() {
        return sepetYemekList;
    }
    public MutableLiveData<List<Yemek>> getYemeklerList() {
        return yemeklerList;
    }

    @Inject
    public YemeklerDaoRepository( YemeklerDao yDao , YemekDao rDao) {
       yemeklerList = new MutableLiveData<>();
        sepetYemekList = new MutableLiveData<>();
        this.yDao = yDao;
        this.rDao = rDao;
    }


    public void yemekleriYukle(){
           yDao.yemekleriYukle().enqueue(new Callback<Yemekler>() {
               @Override
               public void onResponse(Call<Yemekler> call, Response<Yemekler> response) {
                   List<Yemek> liste = response.body().getYemekList();
                   yerlestir(liste);
                   yemeklerList.setValue(liste);
               }
               @Override
               public void onFailure(Call<Yemekler> call, Throwable t) {}
           });
    }
    public void sepettekiYemekleriYukle(){
        yDao.sepettenYemekleriGetir(ApiUtils.kullanici_adi).enqueue(new Callback<SepetYemeler>() {
            @Override
            public void onResponse(Call<SepetYemeler> call, Response<SepetYemeler> response) {
                ArrayList<SepetYemek> sepetList = (ArrayList<SepetYemek>) response.body().getSepet_yemekler();

                sepetYemekList.setValue(sepetList);

                String a = String.valueOf(response.body().getSuccess());
                Log.e("cevap",a);
            }
            @Override
            public void onFailure(Call<SepetYemeler> call, Throwable t) {}
        });
    }

    public void sepeteYemekYukle(String yemek_adi ,
                                 String yemek_resim_adi ,
                                 int yemek_fiyat ,
                                 int yemek_siparis_adet ,
                                 String kullanici_adi  ){
        yDao.sepetteYemekYukle(yemek_adi ,
                yemek_resim_adi ,
                yemek_fiyat ,
                yemek_siparis_adet ,
                kullanici_adi).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                String a = String.valueOf(response.body().getSuccess());
                Log.e("success",a);
            }
            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {}
        });
    }
    public void sepettenYemekSil(int sepet_yemek_id ){
        yDao.sepettenYemekSil(sepet_yemek_id , ApiUtils.kullanici_adi).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                int success = response.body().getSuccess();
                if (success == 1){
                    sepettekiYemekleriYukle();
                }
            }
            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {}
        });
    }
    public void yerlestir(List<Yemek> yemekStream){


        rDao.kaydet(yemekStream).
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                      Log.e("yukleme" , "basarili");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });

    }
    public void arama (String ara){
       rDao.ara(ara).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new SingleObserver<List<Yemek>>() {
           @Override
           public void onSubscribe(Disposable d) {

           }

           @Override
           public void onSuccess(List<Yemek> yemeks) {
             yemeklerList.setValue(yemeks);
           }

           @Override
           public void onError(Throwable e) {

           }
       });


    }

}
