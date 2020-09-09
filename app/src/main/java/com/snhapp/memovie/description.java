package com.snhapp.memovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class description extends AppCompatActivity {

    ImageView poster,icond;
    TextView title,descpText,rating,releaseDateTxt;
    ProgressBar progressBar;
    CardView cardView;

    int id;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        poster = (ImageView)findViewById(R.id.backGroundImageView);
        title = (TextView)findViewById(R.id.titleText);
        icond = findViewById(R.id.dateIcon);
        rating = (TextView)findViewById(R.id.rating);
        cardView = findViewById(R.id.textCardView);
        releaseDateTxt = findViewById(R.id.releasetDateText);
        descpText = (TextView)findViewById(R.id.descriptionText);
        progressBar = findViewById(R.id.progress_loader);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        progressBar.setVisibility(View.VISIBLE);
        getData();



    }


    void getData()
    {
        Call<Resource> movie = API.getImageService().getPhotolist();

        movie.enqueue(new Callback<Resource>() {
            @Override
            public void onResponse(Call<Resource> call, Response<Resource> response) {

                Resource ff = response.body();
                List<Result> gg = ff.getResults();
                Result current = gg.get(id);
                String posterPath = current.getPosterPath();
                Glide
                        .with(getApplicationContext())
                        .load("http://image.tmdb.org/t/p/w300//"+posterPath)
                        .into(poster)

                ;
                progressBar.setVisibility(View.INVISIBLE);
                title.setText(gg.get(id).getTitle());
                rating.setText(current.getVoteAverage()+"/10");
                icond.setVisibility(View.VISIBLE);
                cardView.setVisibility(View.VISIBLE);

                releaseDateTxt.setText(current.getReleaseDate());


                descpText.setText(current.getOverview());


            }

            @Override
            public void onFailure(Call<Resource> call, Throwable t) {
                Toast.makeText(description.this,"Failed",Toast.LENGTH_LONG).show();

            }
        });

    }
}
