package com.arun.wallpaperpexels;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {

    private static Retrofit retrofit = null;
    public static final String API ="563492ad6f91700001000001ee9e635e29f3430189e0f85c4f3701c1";

    public static ApiInterface getApiInterface() {
        if (retrofit==null){
            retrofit = new Retrofit.Builder().baseUrl(ApiInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }return retrofit.create(ApiInterface.class);
    }
}
