package com.deliysuf.lottieapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.deliysuf.lottieapp.databinding.ActivityMainBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AcilisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acilis);
    }
}