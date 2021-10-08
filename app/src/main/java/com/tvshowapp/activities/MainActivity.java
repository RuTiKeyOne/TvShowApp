package com.tvshowapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.tvshowapp.R;
import com.tvshowapp.adapters.TvShowAdapter;
import com.tvshowapp.databinding.ActivityMainBinding;
import com.tvshowapp.models.TvShow;
import com.tvshowapp.viewmodels.MostPopularTvShowsViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MostPopularTvShowsViewModel viewModel;
    private ActivityMainBinding activityMainBinding;
    private List<TvShow> tvShows = new ArrayList<>();
    private TvShowAdapter tvShowAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    private void init(){
        activityMainBinding.tvShowRecycleView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTvShowsViewModel.class);
        tvShowAdapter = new TvShowAdapter(tvShows);
        activityMainBinding.tvShowRecycleView.setAdapter(tvShowAdapter);
        getMostPopularTvShows();
    }

    private void getMostPopularTvShows(){
        activityMainBinding.setIsLoading(true);
        viewModel.getMostPopularTvShows(0).observe(this, mostPopularTvShowsResponse -> {
            activityMainBinding.setIsLoading(false);
            if(mostPopularTvShowsResponse != null){
                if(mostPopularTvShowsResponse.getTvShows() != null){
                    tvShows.addAll(mostPopularTvShowsResponse.getTvShows());
                    tvShowAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}