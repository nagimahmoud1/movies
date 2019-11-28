package com.example.movie2app2.Retrofit;

import com.example.movie2app2.Movies.MoviesResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {



    @GET("discover/movie")
    Call<MoviesResponse> getMovies(@QueryMap Map<String, String> queryMap);
}
