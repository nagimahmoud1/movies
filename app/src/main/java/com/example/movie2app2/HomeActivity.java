package com.example.movie2app2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toolbar;

import com.example.movie2app2.Movies.MoviesListner;
import com.example.movie2app2.Movies.MoviesResponse;
import com.example.movie2app2.Retrofit.ApiCalling;

public class HomeActivity extends AppCompatActivity implements MoviesListner {

    ApiCalling apiCalling;
    RecyclerView adsRv;
    MoviesAdapter moviesAdapter;
    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        apiCalling = new ApiCalling(this);
        apiCalling.getMovies(this);



    }






    @Override
    public void getMoviesListner(boolean status, String msg ,MoviesResponse moviesResponse) {

        Log.e("status**" , status+"");
        if(status == true)
        {
            adsRv = findViewById(R.id.rv);
            moviesAdapter = new MoviesAdapter(moviesResponse.getResults() , this);
            linearLayoutManager = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL ,false);
            adsRv.setAdapter(moviesAdapter);
            adsRv.setLayoutManager(linearLayoutManager);
        }

    }
}
