package com.deliysuf.lottieapp.ui.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.deliysuf.lottieapp.data.SepetYemek;
import com.deliysuf.lottieapp.data.Yemek;
import com.deliysuf.lottieapp.repo.YemeklerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;


@HiltViewModel
public class SepetViewModel extends ViewModel {
    public MutableLiveData<List<SepetYemek>> sepetList;
    public MutableLiveData<Integer> sepetSayi;
    private YemeklerDaoRepository yDao;

    @Inject
    public SepetViewModel (YemeklerDaoRepository yDao) {
        this.yDao = yDao;
        sepettekiYemekleriYukle();
        sepetList = yDao.getSepetYemekList();
    }
    public void sepettekiYemekleriYukle(){
     yDao.sepettekiYemekleriYukle();
    }
    public void sepettenYemekSil(int sepet_yemek_id){
       yDao.sepettenYemekSil(sepet_yemek_id);
    }
}
