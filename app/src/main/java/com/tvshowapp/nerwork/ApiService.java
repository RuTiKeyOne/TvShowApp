package com.tvshowapp.nerwork;

import com.tvshowapp.responses.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("most-popular")
    Call<TvShowResponse> getMostPopularTvShows(@Query("page") int page);

}
