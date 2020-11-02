package com.example.mvcstrucherdemo.Utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.appcompat.app.AlertDialog;

import com.example.mvcstrucherdemo.Activities.LoginScreen;
import com.example.mvcstrucherdemo.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Glob {

     public static final String pref = "MVCDEMO";
     public static final String isLogin = "ISlogin";
     public static final String Email = "email";
     public static final String Password = "password";
     public static final String Name = "name";

     public static ProgressDialog progressDialog;

     public static void errorDialg(Activity context, String msg) {

          AlertDialog.Builder dialog = new AlertDialog.Builder(context);
          dialog.setTitle(context.getString(R.string.dialog_title));
          dialog.setMessage(msg);
          dialog.setPositiveButton(context.getString(R.string.ok), new DialogInterface.OnClickListener() {
               @Override
               public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
               }
          });
          dialog.show();
     }

     public static boolean emailValidtion(String email) {
          String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
          Pattern p = Pattern.compile(EMAIL_STRING);
          Matcher m = p.matcher(email);
          boolean check = m.matches();
          return check;
     }

     public static boolean isNetworkAvailable(Activity activity) {
          ConnectivityManager connectivityManager
                  = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
          NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
          return activeNetworkInfo != null && activeNetworkInfo.isConnected();
     }

     public static void ProgressDialog(Context context) {
          progressDialog = new ProgressDialog(context);
          progressDialog.setCancelable(false);
          progressDialog.setMessage("Wait");
          progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
          progressDialog.show();
     }

     public static void NetworkDialog(final Activity activity) {
          android.app.AlertDialog.Builder builder;
          builder = new android.app.AlertDialog.Builder(activity);
          builder.setMessage("Please check your network connection!!").setTitle("Network Error").setCancelable(false).setPositiveButton(activity.getString(R.string.ok), new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();

               }
          });
          android.app.AlertDialog alert = builder.create();
          if (!alert.isShowing()) {
               alert.show();
          }
     }

}
