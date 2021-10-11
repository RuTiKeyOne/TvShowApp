package com.tvshowapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tvshowapp.nerwork.ApiClient;
import com.tvshowapp.nerwork.ApiService;
import com.tvshowapp.responses.TvShowDetailsResponse;
import com.tvshowapp.responses.TvShowResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTvShowRepository {
    private ApiService apiService;

    public SearchTvShowRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<TvShowResponse> searchTvShow(String query, int page){
        MutableLiveData<TvShowResponse> data = new MutableLiveData<>();
        apiService.searchTvShow(query, page).enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
