package com.deliysuf.lottieapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.deliysuf.lottieapp.data.Yemek;

@Database(entities = {Yemek.class} , version = 1  )
public abstract class VeriTaban─▒ extends RoomDatabase {
    public abstract YemekDao getYemekDao();
}
