package com.deliysuf.lottieapp.Util;

import com.deliysuf.lottieapp.data.SepetYemek;
import com.deliysuf.lottieapp.data.Yemek;

import java.util.Comparator;

public class Siralama implements Comparator<Yemek>{
    @Override
    public int compare(Yemek y1, Yemek y2) {
        return new Integer(y1.getYemek_fiyat()).compareTo(y2.getYemek_fiyat());
    }
}
