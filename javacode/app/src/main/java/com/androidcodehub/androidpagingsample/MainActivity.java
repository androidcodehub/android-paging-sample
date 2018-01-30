package com.androidcodehub.androidpagingsample;

import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.androidcodehub.androidpagingsample.ui.ProductAdapter;
import com.androidcodehub.androidpagingsample.ui.ProductViewModel;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ProductListActivity";
    private boolean mTwoPane;
    private ProductViewModel mProductsViewModel;
    private ProductAdapter mAdapter;

    private ProgressBar mLoadProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        mLoadProgressBar = findViewById(R.id.progress_bar);

        View recyclerView = findViewById(R.id.product_list);
        assert recyclerView != null;


        setupRecyclerView((RecyclerView) recyclerView);

        mProductsViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        mProductsViewModel.dataLoadStatus().observe(this, loadStatus -> {
            switch (loadStatus) {
                case LOADING:
                    mLoadProgressBar.setVisibility(View.VISIBLE);
                    break;
                case LOADED:
                    mLoadProgressBar.setVisibility(View.GONE);
                    break;
                case FAILED:
                    mLoadProgressBar.setVisibility(View.GONE);
                    Toast.makeText(this,"Failed to connect to Walmart Service",
                            Toast.LENGTH_LONG);
                    break;
            }
        });
        mProductsViewModel.getProducts().observe(this, pagedList -> {
            mAdapter.setList(pagedList);

        });
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        mAdapter = new ProductAdapter(this, mTwoPane);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL));
    }
}
