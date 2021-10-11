package com.tvshowapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.tvshowapp.repositories.SearchTvShowRepository;
import com.tvshowapp.responses.TvShowResponse;

public class SearchViewModel extends ViewModel {
    private SearchTvShowRepository searchTvShowRepository;

    public SearchViewModel() {
        searchTvShowRepository = new SearchTvShowRepository();
    }

    public LiveData<TvShowResponse> searchTvShow(String query, int page){
        return searchTvShowRepository.searchTvShow(query, page);
    }
}
