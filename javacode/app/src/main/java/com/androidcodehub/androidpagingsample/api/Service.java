package com.androidcodehub.androidpagingsample.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class Service {

    private static final Service service = new Service();
    private static WalmartApi api;

    private static final String API_URL
            = "https://walmartlabs-test.appspot.com/_ah/api/walmart/v1/walmartproducts/"+
                "56330880-82c2-47eb-9d5d-03db3a49e3bf/";

    private Service() {


        api = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                //.client(client)
                .build()
                .create(WalmartApi.class);
    }

    public static WalmartApi get() {
        return service.api;
    }
}
