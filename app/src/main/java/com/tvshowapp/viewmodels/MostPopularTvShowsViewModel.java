package com.tvshowapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tvshowapp.repositories.MostPopularTvShowsRepository;
import com.tvshowapp.responses.TvShowResponse;

public class MostPopularTvShowsViewModel extends ViewModel {

    private MostPopularTvShowsRepository mostPopularTvShowsRepository;

    public MostPopularTvShowsViewModel() {
        mostPopularTvShowsRepository = new MostPopularTvShowsRepository();
    }

    public LiveData<TvShowResponse> getMostPopularTvShows(int page){
        return mostPopularTvShowsRepository.getMostPopularTvShows(page);
    }
}
