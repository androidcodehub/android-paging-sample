package com.androidcodehub.androidpagingsample.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;


import com.androidcodehub.androidpagingsample.api.ProductResponse;
import com.androidcodehub.androidpagingsample.api.WalmartApi;
import com.androidcodehub.androidpagingsample.model.DataLoadState;
import com.androidcodehub.androidpagingsample.model.Product;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;


public class ProductDataSource extends PageKeyedDataSource<Integer, Product> {

    private WalmartApi walmartApi;

    public final MutableLiveData loadState;

    public ProductDataSource(WalmartApi walmartApi) {

        this.walmartApi = walmartApi;
        loadState = new MutableLiveData<DataLoadState>();

    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Product> callback) {

        loadState.postValue(DataLoadState.LOADING);

        Call<ProductResponse> request = walmartApi.getProducts(1, params.requestedLoadSize);

        Response<ProductResponse> response = null;
        try{
            response = request.execute();
            if(response != null) {
                callback.onResult(response.body().products, 1, 2);
            }else {
                callback.onResult(null, null,2);
            }
            loadState.postValue(DataLoadState.LOADED);
        }catch (IOException ex) {
            loadState.postValue(DataLoadState.FAILED);
        }

    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Product> callback) {


        loadState.postValue(DataLoadState.LOADING);

        Call<ProductResponse> request = walmartApi.getProducts(params.key, params.requestedLoadSize);

        Response<ProductResponse> response = null;
        try{
            response = request.execute();
            if(response != null) {
                Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                callback.onResult(response.body().products, adjacentKey);
            }else {
                callback.onResult(null, params.key - 1);
            }
            loadState.postValue(DataLoadState.LOADED);
        }catch (IOException ex) {
            //networkState.postValue();
        }
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Product> callback) {

        loadState.postValue(DataLoadState.LOADING);

        Call<ProductResponse> request = walmartApi.getProducts(params.key , params.requestedLoadSize);

        Response<ProductResponse> response = null;
        try{
            response = request.execute();
            if(response != null) {
                callback.onResult(response.body().products, params.key + 1);
            }else {
                callback.onResult(null, params.key + 1 );
            }
            loadState.postValue(DataLoadState.LOADED);
        }catch (IOException ex) {
            //networkState.postValue();
            loadState.postValue(DataLoadState.FAILED);
        }
    }
}
