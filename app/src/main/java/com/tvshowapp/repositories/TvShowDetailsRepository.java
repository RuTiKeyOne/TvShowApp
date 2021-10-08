package com.tvshowapp.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.tvshowapp.models.TvShowDetails;
import com.tvshowapp.nerwork.ApiClient;
import com.tvshowapp.nerwork.ApiService;
import com.tvshowapp.responses.TvShowDetailsResponse;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowDetailsRepository {
    private ApiService apiService;

    public TvShowDetailsRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public LiveData<TvShowDetailsResponse> getTvShowDetails(String tvShowId){
        MutableLiveData<TvShowDetailsResponse> tvShowDetailsResponse = new MutableLiveData<>();
        apiService.getTvShowDetails(tvShowId).enqueue(new Callback<TvShowDetailsResponse>() {
            @Override
            public void onResponse(@NotNull Call<TvShowDetailsResponse> call,@NotNull Response<TvShowDetailsResponse> response) {
                tvShowDetailsResponse.setValue(response.body());
            }

            @Override
            public void onFailure(@NotNull Call<TvShowDetailsResponse> call,@NotNull Throwable t) {
                tvShowDetailsResponse.setValue(null);
            }
        });

        return tvShowDetailsResponse;
    }
}
