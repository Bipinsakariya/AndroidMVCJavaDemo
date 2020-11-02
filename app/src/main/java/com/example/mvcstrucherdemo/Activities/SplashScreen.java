package com.example.mvcstrucherdemo.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.mvcstrucherdemo.R;
import com.example.mvcstrucherdemo.Utils.Glob;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences sharepreferences = getSharedPreferences(Glob.pref, Context.MODE_PRIVATE);
        Boolean islogin = sharepreferences.getBoolean(Glob.isLogin,false);

        if(islogin){
            startActivity(new Intent(this, ItemDetails.class));
            finish();
        }else{
            startActivity(new Intent(this,LoginScreen.class));
            finish();
        }

    }
}