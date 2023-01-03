package com.deliysuf.lottieapp.data;

import java.io.Serializable;

public class Restorant implements Serializable {
    private String ad;
    private String resim_ad;

    public Restorant() {
    }

    public Restorant(String ad, String resim_ad) {
        this.ad = ad;
        this.resim_ad = resim_ad;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getResim_ad() {
        return resim_ad;
    }

    public void setResim_ad(String resim_ad) {
        this.resim_ad = resim_ad;
    }
}
