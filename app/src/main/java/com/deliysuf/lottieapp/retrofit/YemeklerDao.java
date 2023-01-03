package com.deliysuf.lottieapp.retrofit;

import com.deliysuf.lottieapp.data.CRUDCevap;
import com.deliysuf.lottieapp.data.SepetYemeler;
import com.deliysuf.lottieapp.data.Yemekler;

import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface YemeklerDao {
    //http://kasimadalan.pe.hu/yemekler/sepettenYemekSil.php
    //http://kasimadalan.pe.hu/yemekler/sepeteYemekEkle.php
    //http://kasimadalan.pe.hu/yemekler/sepettekiYemekleriGetir.php
    @GET("yemekler/tumYemekleriGetir.php")
    Call<Yemekler> yemekleriYukle();
    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    Call<SepetYemeler> sepettenYemekleriGetir(@Field("kullanici_adi") String kullanici_adi);
    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    Call<CRUDCevap> sepetteYemekYukle(
            @Field("yemek_adi") String yemek_adi ,
            @Field("yemek_resim_adi") String yemek_resim_adi ,
            @Field("yemek_fiyat") int yemek_fiyat ,
            @Field("yemek_siparis_adet") int yemek_spiaris_adet ,
            @Field("kullanici_adi") String kullanici_adi );
    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    Call<CRUDCevap> sepettenYemekSil(
            @Field("sepet_yemek_id") int sepet_yemek_id ,
            @Field("kullanici_adi") String kullaniici_adi);
}
