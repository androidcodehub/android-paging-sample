package com.androidcodehub.androidpagingsample.ui;

import android.arch.lifecycle.LiveData;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.arch.paging.PagedList.Config.Builder;
import android.support.annotation.MainThread;


import com.androidcodehub.androidpagingsample.AppsExecutor;
import com.androidcodehub.androidpagingsample.model.DataLoadState;
import com.androidcodehub.androidpagingsample.model.Product;

import static android.arch.lifecycle.Transformations.switchMap;


public class ProductRepositoryImpl implements  ProductRepository{

    //private Executor networkExecutor;
    ProductDataFactory dataSourceFactory;
    private static final int PAGE_SIZE = 30;
    private LiveData<PagedList<Product>> products;

    public ProductRepositoryImpl() {
        dataSourceFactory = new ProductDataFactory();
        //this.networkExecutor = networkExecutor;
    }

    @Override
    @MainThread
    public LiveData<PagedList<Product>> getProducts() {

        PagedList.Config config = new Builder()
                                        .setInitialLoadSizeHint(PAGE_SIZE)
                                        .setPageSize(PAGE_SIZE)
                                        .build();


        products = new LivePagedListBuilder(dataSourceFactory, config)
                        .setInitialLoadKey(1)
                        .setBackgroundThreadExecutor(AppsExecutor.networkIO())
                        .build();

        return products;
    }

    public LiveData<DataLoadState> getDataLoadStatus(){
        return switchMap(dataSourceFactory.datasourceLiveData,
                            dataSource -> dataSource.loadState);
    }
}
