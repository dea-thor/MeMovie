package com.snhapp.memovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.movieRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);
        getData();
    }

    void getData()
    {
        Call<Resource> movie = API.getImageService().getPhotolist();

        movie.enqueue(new Callback<Resource>() {
            @Override
            public void onResponse(Call<Resource> call, Response<Resource> response) {

                Resource list = response.body();

                List<Result> all = list.getResults();

                mAdapter = new ExampleAdapter(all,MainActivity.this);
                recyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<Resource> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failed",Toast.LENGTH_LONG).show();

            }
        });

    }




    }

