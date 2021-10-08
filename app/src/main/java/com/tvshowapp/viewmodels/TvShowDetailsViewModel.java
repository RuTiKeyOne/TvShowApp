package com.tvshowapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tvshowapp.repositories.TvShowDetailsRepository;
import com.tvshowapp.responses.TvShowDetailsResponse;

public class TvShowDetailsViewModel extends ViewModel {
    private TvShowDetailsRepository tvShowDetailsRepository;

    public TvShowDetailsViewModel() {
        tvShowDetailsRepository = new TvShowDetailsRepository();
    }

    public LiveData<TvShowDetailsResponse> getTvShowDetails(String tvShowId){
        return tvShowDetailsRepository.getTvShowDetails(tvShowId);
    }
}
