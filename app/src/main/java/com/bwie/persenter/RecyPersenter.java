package com.bwie.persenter;

import com.bwie.View.IRecyView;
import com.bwie.bean.Shop;
import com.bwie.model.IRecyModel;
import com.bwie.model.RecyModel;
import com.bwie.utils.Okonballck;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;

public class RecyPersenter {
    private IRecyView view;
    private IRecyModel model;

    public RecyPersenter(IRecyView view) {
        this.view = view;
        this.model =new RecyModel();
    }
    public void Datashop(){
        model.recy(new Okonballck() {
            @Override
            public void onfiled(Call call, IOException e) {

            }

            @Override
            public void onsuccess(String result) {
                Gson gson=new Gson();
                Shop shop = gson.fromJson(result, Shop.class);
                shop.getData();
                view.shopData(shop);
            }
        });
    }
}
