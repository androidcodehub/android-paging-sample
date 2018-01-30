package com.androidcodehub.androidpagingsample.api;



import com.androidcodehub.androidpagingsample.model.Product;

import java.util.List;


public class ProductResponse {
    public List<Product> products;
    public int totalProducts;
    public int pageNumber;
    public int pageSize;
    public int status;
}
