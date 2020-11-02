package com.example.mvcstrucherdemo.Service;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;

import com.example.mvcstrucherdemo.Utils.Glob;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Services {
    private static Services mInstance;
    private static Retrofit.Builder retrofit_builder;
    SharedPreferences sharedPreferences;
    static OkHttpClient.Builder okHttpClientBuilder;

    private Services() {

    }

    public static Services getInstance() {

        okHttpClientBuilder = new OkHttpClient.Builder();

        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder newRequest = request.newBuilder()/*.header(Glob.API_KEY, Glob.API_KEY_VALUE)*/;
                return chain.proceed(newRequest.build());
            }
        });

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (mInstance == null) {
            mInstance = new Services();
            retrofit_builder = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClientBuilder.build());
        }
        return mInstance;
    }

    public void callOperation(final String url, final ServicesOperationCompletion listener, Map<String, String> headers, Map<String, String> params, final Activity activity) {
        if (Glob.isNetworkAvailable(activity)) {
            Glob.ProgressDialog(activity);
            Retrofit retrofit = retrofit_builder.baseUrl(ServiceConstants.BASE_URL).build();
            BackendLessService dispatcher = retrofit.create(BackendLessService.class);
            Call<JsonObject> call = null;
            if (url.equals(ServiceConstants.ItemEndPoint)) {
                call = dispatcher.itemlist();
            }

            call.enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    Glob.progressDialog.dismiss();
                    if (response.code() == 200) {
                        if (response != null) {
                            listener.onResponseReceived(response.body(), url);
                        }
                    }  else {
                        listener.onError(response.message());
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    Glob.progressDialog.dismiss();
                    listener.onError(t.getMessage());
                    t.printStackTrace();

                }
            });
        } else {
            Glob.NetworkDialog(activity);
        }
    }



    public static interface ServicesOperationCompletion {

        public void onResponseReceived(JsonObject responseData, String CallType);

        public void onError(String errorMessage);
    }
}
