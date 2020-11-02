package com.example.mvcstrucherdemo.Service;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.QueryMap;

interface BackendLessService {

    @GET(ServiceConstants.ItemEndPoint)
    Call<JsonObject> itemlist();


}
