package com.tvshowapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

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
    private int currentPage = 1;
    private int totalAvailablePages = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    private void init() {
        activityMainBinding.tvShowRecycleView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTvShowsViewModel.class);
        tvShowAdapter = new TvShowAdapter(tvShows);
        activityMainBinding.tvShowRecycleView.setAdapter(tvShowAdapter);
        activityMainBinding.tvShowRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(!activityMainBinding.tvShowRecycleView.canScrollVertically(1)){
                    if(currentPage <= totalAvailablePages){
                        currentPage+=1;
                        getMostPopularTvShows();
                    }
                }
            }
        });
        getMostPopularTvShows();
    }

    private void getMostPopularTvShows() {
        toggleLoading();
        viewModel.getMostPopularTvShows(currentPage).observe(this, mostPopularTvShowsResponse -> {
            toggleLoading();
            if (mostPopularTvShowsResponse != null) {
                totalAvailablePages = mostPopularTvShowsResponse.getTotalPages();
                if (mostPopularTvShowsResponse.getTvShows() != null) {
                    int oldCount = tvShows.size();
                    tvShows.addAll(mostPopularTvShowsResponse.getTvShows());
                    tvShowAdapter.notifyItemRangeInserted(oldCount, tvShows.size());
                }
            }
        });
    }

    private void toggleLoading() {
        if (currentPage == 1) {
            if (activityMainBinding.getIsLoading() != null && activityMainBinding.getIsLoading()) {
                activityMainBinding.setIsLoading(false);
            } else {
                activityMainBinding.setIsLoading(true);
            }
        } else {
            if (activityMainBinding.getIsLoadingMore() != null && activityMainBinding.getIsLoadingMore()) {
                activityMainBinding.setIsLoadingMore(false);
            } else {
                activityMainBinding.setIsLoadingMore(true);
            }
        }
    }
}