package com.deliysuf.lottieapp.retrofit;

import retrofit2.Retrofit;

public class ApiUtils {
    public static final String kullanici_adi = "yusuf";
    public static final String BASE_URL = "http://kasimadalan.pe.hu/";
    public static final YemeklerDao getYemeklerDao(){
        return RetrofitClient.getClient(BASE_URL).create(YemeklerDao.class);
    }


}
