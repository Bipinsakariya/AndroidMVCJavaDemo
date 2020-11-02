package com.example.mvcstrucherdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mvcstrucherdemo.Activities.HomeScreen;
import com.example.mvcstrucherdemo.Activities.Login;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText username,pwd, cpwd, mobile;
    Button signUp;
    public static final String email = "nameKey";
    public static final String password = "passwordKey";
    public static final String c_password = "c_password";
    public static final String mobileNumber = "mobileKey";
    private SharedPreferences sh_pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sh_pref = getSharedPreferences("Sharepref", Context.MODE_PRIVATE);
        init();

    }

    private void init() {
        username = (EditText)findViewById(R.id.ed_username);
        pwd = (EditText)findViewById(R.id.ed_password);
        cpwd = (EditText)findViewById(R.id.ed_c_password);
        mobile = (EditText)findViewById(R.id.ed_mobile);
        signUp = (Button)findViewById(R.id.btn_SignUp);

        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String user  = username.getText().toString();
        String p  = pwd.getText().toString();
        String cp  = cpwd.getText().toString();
        String m  = mobile.getText().toString();

        SharedPreferences.Editor editor = sh_pref.edit();

        editor.putString(email, user);
        editor.putString(password, p);
        editor.putString(c_password, cp);
        editor.putString(mobileNumber, m );
        editor.commit();

        if(user.equals("") && p.equals("") && cp.equals("") && m.equals("")){
            Toast.makeText(getApplicationContext(), "Please fill all fills ",Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
    }
}