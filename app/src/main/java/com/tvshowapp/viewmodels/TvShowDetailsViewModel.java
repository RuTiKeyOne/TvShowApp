package com.tvshowapp.viewmodels;

import android.app.Application;
import android.database.DatabaseUtils;


import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Database;

import com.tvshowapp.database.TvShowDatabase;
import com.tvshowapp.models.TvShow;
import com.tvshowapp.repositories.TvShowDetailsRepository;
import com.tvshowapp.responses.TvShowDetailsResponse;

import org.jetbrains.annotations.NotNull;

import io.reactivex.Completable;

public class TvShowDetailsViewModel extends AndroidViewModel {
    private TvShowDetailsRepository tvShowDetailsRepository;
    private TvShowDatabase tvShowDatabase;

    public TvShowDetailsViewModel(@NotNull Application application) {
        super(application);
        tvShowDetailsRepository = new TvShowDetailsRepository();
        tvShowDatabase = TvShowDatabase.getTvShowDatabase(application);
    }

    public LiveData<TvShowDetailsResponse> getTvShowDetails(String tvShowId){
        return tvShowDetailsRepository.getTvShowDetails(tvShowId);
    }

    public Completable addToWatchlist(TvShow tvShow){
        return tvShowDatabase.tvShowDao().addToWatchlist(tvShow);
    }
}
