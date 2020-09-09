package com.snhapp.memovie;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class API {

    public static String baseUrl = "https://api.themoviedb.org/3/discover/";


    public static ImageService imageService = null;

    public static  ImageService getImageService()
    {
        if(imageService == null)
        {




            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            imageService = retrofit.create(ImageService.class);

        }

        return imageService;

    }





    public interface ImageService
    {

        @GET("movie?api_key=4e44d9029b1270a757cddc766a1bcb63")
        Call<Resource> getPhotolist();
    }

}
