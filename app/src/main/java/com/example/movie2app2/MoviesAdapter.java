package com.example.movie2app2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.movie2app2.Movies.Result ;
import com.example.movie2app2.Retrofit.ApiClient;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ItemHolder> {
    public List<Result> adsList;
    Context context;

    public MoviesAdapter(List<Result> adsList , Context context)
    {
        this.adsList = adsList;
        this.context = context;

    }

    @NonNull
    @Override
    public MoviesAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.itm_ads, viewGroup, false);
        final MoviesAdapter.ItemHolder itemHolder = new MoviesAdapter.ItemHolder(itemView);
        return itemHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemHolder itemHolder, int position) {


        // get data from list of response of ads
        final Result itemData = adsList.get(position);

        // set data of items of recycle view
        itemHolder.titleTv.setText(itemData.getTitle());
        itemHolder.dateTv.setText(itemData.getReleaseDate());
        itemHolder.rateTv.setText(itemData.getVoteAverage()+"");


        itemHolder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,DetailActivity.class);
                intent.putExtra("title",itemData.getTitle());
                intent.putExtra("image",ApiClient.IMAGE_PATH+itemData.getPosterPath());
                intent.putExtra("date",itemData.getReleaseDate());
                intent.putExtra("avgRate",itemData.getVoteAverage());
                intent.putExtra("language",itemData.getOriginalLanguage());
                intent.putExtra("description",itemData.getOriginalTitle());

                context.startActivity(intent);

            }
        });



        // download image from path and show it
        if (itemData.getPosterPath() != null && itemData.getPosterPath() != "")
        {
            Glide.with(context)
                    // url of image
                    .load(ApiClient.IMAGE_PATH+itemData.getPosterPath())
//                        .error(R.drawable.arrow_back)
                    .listener(new RequestListener<String, GlideDrawable>()
                    {
                        @Override
                        public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource)
                        {
                            Log.e("Glide erorr**", "failed to load image");
                            // Toast.makeText(getApplicationContext() , getResources().getString(R.string.load_photo_error) , Toast.LENGTH_LONG).show();
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            return false;
                        }

                    })
                    .error(context.getResources().getDrawable(R.drawable.ic_icons8_movie))
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .skipMemoryCache(true)
                    .into(itemHolder.adIv);
        }

    }

    @Override
    public int getItemCount() {
        return adsList.size();
    }


    public class ItemHolder extends RecyclerView.ViewHolder {



        ImageView adIv ;
        TextView titleTv , rateTv , dateTv;
        ConstraintLayout layout;
        public ItemHolder(@NonNull View itemView) {
            super(itemView);
            layout=itemView.findViewById(R.id.container);
            adIv=itemView.findViewById(R.id.imageView2);
            titleTv=itemView.findViewById(R.id.textView);
            rateTv=itemView.findViewById(R.id.textView2);
            dateTv=itemView.findViewById(R.id.textView3);


        }
    }
}


