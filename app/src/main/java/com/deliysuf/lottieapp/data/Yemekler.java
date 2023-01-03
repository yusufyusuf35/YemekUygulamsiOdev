package com.deliysuf.lottieapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Yemekler {
    @SerializedName("yemekler")
    private List<Yemek> yemekList;
    @SerializedName("success")
    private int success;

    public Yemekler() {
    }

    public Yemekler(List<Yemek> yemekList, int success) {
        this.yemekList = yemekList;
        this.success = success;
    }

    public List<Yemek> getYemekList() {
        return yemekList;
    }

    public void setYemekList(List<Yemek> yemekList) {
        this.yemekList = yemekList;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}
