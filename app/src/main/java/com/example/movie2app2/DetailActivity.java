package com.example.movie2app2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.movie2app2.Retrofit.ApiClient;

public class DetailActivity extends AppCompatActivity {

    String title,image,date,language,description;
    Double avgRate;

    ImageView imageIv;

    TextView titleTv,dateTv,languageTv,descriptionTv,avgRateTv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);



        if (getIntent().getExtras() != null){

            title= getIntent().getExtras().getString("title","erorr");
            image= getIntent().getExtras().getString("image","erorr");
            date= getIntent().getExtras().getString("date","erorr");
            language = getIntent().getExtras().getString("language","erorr");
            description= getIntent().getExtras().getString("description","erorr");
            avgRate = getIntent().getExtras().getDouble("avgRate",-1d);

            Log.e("test***",title);
            Log.e("test***",image);
            Log.e("test***",date);
            Log.e("test***",language);
            Log.e("test***",description);
            Log.e("test***",avgRate+"");



        }
        setReferance();
        setData();


    }


    public void setReferance(){
        imageIv=findViewById(R.id.imageView5);
        titleTv=findViewById(R.id.textView16);
        dateTv=findViewById(R.id.textView17);
        languageTv=findViewById(R.id.textView18);
        descriptionTv=findViewById(R.id.textView20);
        avgRateTv=findViewById(R.id.textView21);


    }

    public void setData(){

        Glide.with(this)
                // url of image
                .load(image)
//                        .error(R.drawable.arrow_back)
                .listener(new RequestListener<String, GlideDrawable>()
                {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource)
                    {
                        Log.e("Glide erorr**", e.getMessage());
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }

                })
                .error(getResources().getDrawable(R.drawable.ic_menu_gallery))
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .skipMemoryCache(true)
                .into(imageIv);


       titleTv.setText(title);
       dateTv.setText(date);
       languageTv.setText(language);
       descriptionTv.setText(description);
       avgRateTv.setText(avgRate+"");


    }


}
