package com.example.movie2app2.Retrofit;

import android.content.Context;
import android.util.Log;

import com.example.movie2app2.Movies.MoviesListner;
import com.example.movie2app2.Movies.MoviesResponse;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiCalling  {

    private ApiClient apiClient;
    private ApiInterface apiInterface;
    private Context context;

    public ApiCalling(Context context) {


        this.context = context;
        apiClient = new ApiClient(context);
        apiInterface = apiClient.getClient().create(ApiInterface.class);

    }


    public void getMovies(final MoviesListner moviesListner) {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("api_key", "2562a20c7157865fb9a54cab92727a48");


        Call<MoviesResponse> call = apiInterface.getMovies(queryMap);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {


                if (response.isSuccessful()) {
                    moviesListner.getMoviesListner(true, null,response.body());
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                moviesListner.getMoviesListner(false , null,null);

            }
        });
    }


}
