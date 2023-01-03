package com.deliysuf.lottieapp.di;

import android.content.Context;

import androidx.room.Room;

import com.deliysuf.lottieapp.repo.YemeklerDaoRepository;
import com.deliysuf.lottieapp.retrofit.ApiUtils;
import com.deliysuf.lottieapp.retrofit.YemeklerDao;
import com.deliysuf.lottieapp.room.VeriTabanı;
import com.deliysuf.lottieapp.room.YemekDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class AppModule {
    @Provides
    @Singleton
    public YemeklerDaoRepository repositoryProvider(YemeklerDao yDao , YemekDao rDao){

        return new YemeklerDaoRepository(yDao , rDao);
    }

    @Provides
    @Singleton
    public YemeklerDao providerDao(){
        return ApiUtils.getYemeklerDao();
    }

    @Provides
    @Singleton
    public YemekDao daoprovider(@ApplicationContext Context mcontext){
        VeriTabanı vt = Room.databaseBuilder(mcontext ,
                VeriTabanı.class , "veritabani").build();
        return vt.getYemekDao();
  }


}
