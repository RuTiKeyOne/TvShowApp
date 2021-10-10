package com.tvshowapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tvshowapp.R;
import com.tvshowapp.databinding.ActivityWatchlistBinding;
import com.tvshowapp.databinding.ActivityWatchlistBindingImpl;
import com.tvshowapp.viewmodels.WatchlistViewModel;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class WatchlistActivity extends AppCompatActivity {

    private ActivityWatchlistBinding activityWatchlistBinding;
    private WatchlistViewModel watchlistViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityWatchlistBinding = DataBindingUtil.setContentView(this, R.layout.activity_watchlist);
        init();
    }

    private void init() {
        watchlistViewModel = new ViewModelProvider(this).get(WatchlistViewModel.class);
        activityWatchlistBinding.imageWatchlistBack.setOnClickListener(view -> onBackPressed());
    }

    private void loadWatchlist() {
        activityWatchlistBinding.setIsLoading(true);
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(watchlistViewModel.loadWatchlist().subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvShows -> {
                        activityWatchlistBinding.setIsLoading(false);
                        Toast.makeText(getApplicationContext(), "Watchlist: " + tvShows.size(), Toast.LENGTH_LONG).show();
                }));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadWatchlist();
    }

}