package com.tvshowapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import com.tvshowapp.R;
import com.tvshowapp.viewmodels.MostPopularTvShowsViewModel;

public class MainActivity extends AppCompatActivity {

    private MostPopularTvShowsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MostPopularTvShowsViewModel.class);
        getMostPopularTvShows();
    }

    private void getMostPopularTvShows(){
        viewModel.getMostPopularTvShows(0).observe(this, mostPopularTvShowsResponse -> {
            Toast.makeText(
                    getApplicationContext(),
                    "Total pages: " + mostPopularTvShowsResponse.getTotalPages(),
                    Toast.LENGTH_LONG
            ).show();
        });
    }
}