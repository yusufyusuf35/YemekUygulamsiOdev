package com.deliysuf.lottieapp.data;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
@Entity
public class Yemek {
    @PrimaryKey
    @SerializedName("yemek_id")
    private int yemek_id;
    @ColumnInfo(name = "yemek_adi")
    @SerializedName("yemek_adi")
    private String yemek_adi;
    @ColumnInfo(name = "yemek_resim_adi")
    @SerializedName("yemek_resim_adi")
    private String yemek_resim_adi;
    @ColumnInfo(name = "yemek_fiyat")
    @SerializedName("yemek_fiyat")
    private int yemek_fiyat;



    public Yemek(int yemek_id, String yemek_adi, String yemek_resim_adi, int yemek_fiyat) {
        this.yemek_id = yemek_id;
        this.yemek_adi = yemek_adi;
        this.yemek_resim_adi = yemek_resim_adi;
        this.yemek_fiyat = yemek_fiyat;

    }

    public Yemek() {
    }



    public int getYemek_id() {
        return yemek_id;
    }

    public void setYemek_id(int yemek_id) {
        this.yemek_id = yemek_id;
    }

    public String getYemek_adi() {
        return yemek_adi;
    }

    public void setYemek_adi(String yemek_adi) {
        this.yemek_adi = yemek_adi;
    }

    public String getYemek_resim_adi() {
        return yemek_resim_adi;
    }

    public void setYemek_resim_adi(String yemek_resim_adi) {
        this.yemek_resim_adi = yemek_resim_adi;
    }



    public int getYemek_fiyat() {
        return yemek_fiyat;
    }

    public void setYemek_fiyat(int yemek_fiyat) {
        this.yemek_fiyat = yemek_fiyat;
    }
}
