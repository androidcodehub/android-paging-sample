package com.androidcodehub.androidpagingsample.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.androidcodehub.androidpagingsample.model.DataLoadState;
import com.androidcodehub.androidpagingsample.model.Product;


public class ProductViewModel extends AndroidViewModel {

    private ProductRepository repository;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductRepositoryImpl();
    }

    public LiveData<PagedList<Product>> getProducts() {
        return repository.getProducts();
    }

    public LiveData<DataLoadState> dataLoadStatus() {
        return repository.getDataLoadStatus();
    }

}
