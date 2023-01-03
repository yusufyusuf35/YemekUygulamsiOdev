package com.deliysuf.lottieapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SepetYemeler {

    @SerializedName("sepet_yemekler")
    private List<SepetYemek> sepet_yemekler;
    @SerializedName("success")
    private int success;

    public SepetYemeler() {
    }

    public SepetYemeler(List<SepetYemek> sepet_yemekler, int success) {
        this.sepet_yemekler = sepet_yemekler;
        this.success = success;
    }

    public List<SepetYemek> getSepet_yemekler() {
        return sepet_yemekler;
    }

    public void setSepet_yemekler(List<SepetYemek> sepet_yemekler) {
        this.sepet_yemekler = sepet_yemekler;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
