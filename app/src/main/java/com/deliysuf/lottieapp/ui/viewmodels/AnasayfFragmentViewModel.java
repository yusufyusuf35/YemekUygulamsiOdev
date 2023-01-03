package com.deliysuf.lottieapp.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.deliysuf.lottieapp.data.SepetYemek;
import com.deliysuf.lottieapp.data.Yemek;
import com.deliysuf.lottieapp.repo.YemeklerDaoRepository;
import com.deliysuf.lottieapp.retrofit.YemeklerDao;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AnasayfFragmentViewModel extends ViewModel {
    public MutableLiveData<List<Yemek>> yemeklerList;
    public MutableLiveData<List<SepetYemek>> sepetim;
    private YemeklerDaoRepository yDao;
    @Inject
    public AnasayfFragmentViewModel(YemeklerDaoRepository yDao) {
        this.yDao = yDao;
        yemekleriYukle();
        sepet();
        yemeklerList = yDao.getYemeklerList();
        sepetim = yDao.getSepetYemekList();

    }
    public void yemekleriYukle(){
        yDao.yemekleriYukle();
    }
    public void sepeteYemekYukle( String yemek_adi,
                                  String yemek_resim_adi,
                                  int yemek_fiyat,
                                  int yemek_siparis_adet,
                                  String kullanici_adi ){
        yDao.sepeteYemekYukle(
                yemek_adi ,
                yemek_resim_adi ,
                yemek_fiyat ,
                yemek_siparis_adet ,
                kullanici_adi );
    }
    public void ara (String arama){
        yDao.arama(arama);
    }
    public void sepettenYemekSil(int id){
        yDao.sepettenYemekSil(id);
    }
    public void sepet(){

        yDao.sepettekiYemekleriYukle();
        sepetim = yDao.getSepetYemekList();
    }
}
