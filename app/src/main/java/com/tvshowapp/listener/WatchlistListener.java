package com.tvshowapp.listener;

import com.tvshowapp.models.TvShow;

public interface WatchlistListener {
    void onTvShowClicked(TvShow tvShow);
    void remoteTvShowFromWatchlist(TvShow tvShow, int position);
}
