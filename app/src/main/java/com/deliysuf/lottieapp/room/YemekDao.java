package com.deliysuf.lottieapp.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.deliysuf.lottieapp.data.Yemek;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface YemekDao {
    @Query("SELECT * FROM Yemek WHERE yemek_adi like '%' || :aramaKelimesi || '%'")
    Single<List<Yemek>> ara(String aramaKelimesi);
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable kaydet(List<Yemek> yemeks);


}
