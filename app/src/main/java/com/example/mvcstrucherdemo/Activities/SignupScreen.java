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
import com.example.mvcstrucherdemo.databinding.ActivitySignupScreenBinding;

public class SignupScreen extends AppCompatActivity implements View.OnClickListener {

    private ActivitySignupScreenBinding binding;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_signup_screen);
        SharedPreferences sharepreferences = getSharedPreferences(Glob.pref, Context.MODE_PRIVATE);
        editor = sharepreferences.edit();
        init();
    }


    @Override
    public void onClick(View view) {

        if(view == binding.btnSignUp){
            if (isValidation()) {
                savedata();
            }
        }else if(view == binding.txtBacktologin){
            onBackPressed();
        }

    }

    private void savedata() {
        editor.putBoolean(Glob.isLogin,true);
        editor.putString(Glob.Name,binding.edFirstName.getText().toString());
        editor.putString(Glob.Password,binding.edPassword.getText().toString());
        editor.putString(Glob.Email,binding.edEmail.getText().toString());

        editor.commit();

        Intent intent = new Intent(this, ItemDetails.class);
        startActivity(intent);
        finishAffinity();

    }

    private boolean isValidation() {

        if (binding.edFirstName.getText().toString().trim().isEmpty()) {
            binding.edFirstName.requestFocus();
            binding.edFirstName.setError(getResources().getString(R.string.empty_name));
            return false;
        } else if (binding.edEmail.getText().toString().trim().isEmpty()) {
            binding.edEmail.requestFocus();
            binding.edEmail.setError(getResources().getString(R.string.empty_email));
            return false;
        } else if (binding.edPassword.getText().toString().trim().isEmpty()) {
            binding.edPassword.requestFocus();
            binding.edPassword.setError(getResources().getString(R.string.empty_password));
            return false;
        } else if (!Glob.emailValidtion(binding.edEmail.getText().toString().trim())) {
            binding.edEmail.requestFocus();
            binding.edEmail.setError(getResources().getString(R.string.errorValidEmail));
            return false;
        } else if (binding.edPhno.getText().toString().trim().isEmpty()) {
            binding.edPhno.requestFocus();

            binding.edPhno.setError(getResources().getString(R.string.errorPhno));
            return false;
        } else if (!binding.contrycode.isValidFullNumber()) {
            binding.edPhno.requestFocus();

            binding.edPhno.setError(getResources().getString(R.string.errorpno));
            return false;
        } else {
            return true;
        }

    }


    private void init() {
        binding.btnSignUp.setOnClickListener(this);
        binding.txtBacktologin.setOnClickListener(this);

        binding.contrycode.setCountryForNameCode("In");
        binding.contrycode.registerCarrierNumberEditText(binding.edPhno);
        binding.contrycode.isValidFullNumber();

    }
}