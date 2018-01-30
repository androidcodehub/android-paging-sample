package com.androidcodehub.androidpagingsample.ui;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.androidcodehub.androidpagingsample.model.DataLoadState;
import com.androidcodehub.androidpagingsample.model.Product;


public interface ProductRepository {

    public LiveData<PagedList<Product>> getProducts();
    public LiveData<DataLoadState> getDataLoadStatus();
}
