package com.bwie.model;

import com.bwie.utils.OkHttpUtils;

import okhttp3.Callback;

public  class RecyModel implements  IRecyModel {

    @Override
    public void recy(Callback callback) {
          OkHttpUtils.getOkHttpUtils().doGet("http://120.27.23.105/product/getProducts?pscid=39&page=1",callback);
    }
}
