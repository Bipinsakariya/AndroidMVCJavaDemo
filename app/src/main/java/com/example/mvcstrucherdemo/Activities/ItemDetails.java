package com.example.mvcstrucherdemo.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.mvcstrucherdemo.Adapters.ItemAdapter;
import com.example.mvcstrucherdemo.Model.Item;
import com.example.mvcstrucherdemo.Model.Response;
import com.example.mvcstrucherdemo.R;
import com.example.mvcstrucherdemo.Service.ServiceConstants;
import com.example.mvcstrucherdemo.Service.Services;
import com.example.mvcstrucherdemo.Utils.Glob;
import com.example.mvcstrucherdemo.databinding.ActivityItemdetailsScreenBinding;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDetails extends AppCompatActivity implements Services.ServicesOperationCompletion {

    private ActivityItemdetailsScreenBinding binding;
    private List<Item> itemlist = new ArrayList<>();
    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_itemdetails_screen);
        init();
        fetchitemdata();

    }

    private void fetchitemdata() {


        Services.getInstance().callOperation(ServiceConstants.ItemEndPoint, this, null, null, this);

    }

    private void init() {

        binding.recyclerItems.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        itemAdapter = new ItemAdapter(this, itemlist);
        binding.recyclerItems.setAdapter(itemAdapter);

    }

    @Override
    public void onResponseReceived(JsonObject responseData, String CallType) {
        Gson gson = new Gson();
        Response event = gson.fromJson(responseData, Response.class);

        if (CallType.equals(ServiceConstants.ItemEndPoint)) {
            if(event.getItems()!=null && event.getItems().size()!=0){
                itemlist.addAll(event.getItems());
                itemAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onError(String errorMessage) {
        Glob.errorDialg(this, errorMessage);
    }
}