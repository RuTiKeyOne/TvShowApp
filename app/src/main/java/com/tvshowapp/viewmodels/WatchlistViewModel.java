package com.tvshowapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.AndroidViewModel;

import com.tvshowapp.dao.TvShowDao;
import com.tvshowapp.database.TvShowDatabase;
import com.tvshowapp.models.TvShow;

import java.util.List;

import io.reactivex.Flowable;

public class WatchlistViewModel extends AndroidViewModel {

    private TvShowDatabase tvShowDatabase;

    public WatchlistViewModel(@NonNull Application application) {
        super(application);
        tvShowDatabase = TvShowDatabase.getTvShowDatabase(application);
    }

    public Flowable<List<TvShow>> loadWatchlist(){
        return tvShowDatabase.tvShowDao().getWatchlist();
    }
}
