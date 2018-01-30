package com.androidcodehub.androidpagingsample.ui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import com.androidcodehub.androidpagingsample.api.Service;
import com.androidcodehub.androidpagingsample.model.Product;



public class ProductDataFactory implements DataSource.Factory<Integer, Product> {

    public MutableLiveData<ProductDataSource> datasourceLiveData = new MutableLiveData<>();

    @Override
    public DataSource<Integer, Product> create() {

        ProductDataSource dataSource = new ProductDataSource(Service.get());
        datasourceLiveData.postValue(dataSource);
        return dataSource;
    }
}
