package com.example.mvcstrucherdemo.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.example.mvcstrucherdemo.R;
import com.example.mvcstrucherdemo.Utils.Glob;
import com.example.mvcstrucherdemo.databinding.ActivityLoginScreenBinding;

public class LoginScreen extends AppCompatActivity implements View.OnClickListener {

    private SharedPreferences sharepreferences;
    private ActivityLoginScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_login_screen);
        sharepreferences = getSharedPreferences(Glob.pref, Context.MODE_PRIVATE);
        init();
    }

    private void init() {
        binding.btnLogin.setOnClickListener(this);
        binding.txtSignup.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view == binding.btnLogin){
            if(isValidation()){
                if(sharepreferences.getString(Glob.Email,"").equals(binding.edEmail.getText().toString()) &&
                        sharepreferences.getString(Glob.Password,"").equals(binding.edPassword.getText().toString())){
                    sharepreferences.edit().putBoolean(Glob.isLogin,true).apply();
                    Intent intent = new Intent(this, ItemDetails.class);
                    startActivity(intent);
                    finishAffinity();

                }else {
                    Glob.errorDialg(this,"Your account is not exits so please create a new account.");
                }
            }

        }else  if(view == binding.txtSignup){
            startActivity(new Intent(this,SignupScreen.class));
        }
    }

    private boolean isValidation() {


        if(binding.edEmail.getText().toString().trim().isEmpty()){
            binding.edEmail.requestFocus();
            binding.edEmail.setError(getResources().getString(R.string.empty_email));
            return false;
        }else if(binding.edPassword.getText().toString().trim().isEmpty()){
            binding.edPassword.requestFocus();
            binding.edPassword.setError(getResources().getString(R.string.empty_password));
            return false;
        }else if(!Glob.emailValidtion(binding.edEmail.getText().toString().trim())){
            binding.edEmail.requestFocus();
            binding.edEmail.setError(getResources().getString(R.string.errorValidEmail));
            return false;
        }
        else{
            return true;
        }
    }
}