package com.tvshowapp.responses;

import com.google.gson.annotations.SerializedName;
import com.tvshowapp.models.TvShowDetails;

public class TvShowDetailsResponse {

    @SerializedName("tvShow")
    private TvShowDetails tvShowDetails;

    public TvShowDetails getTvShowDetails() {
        return tvShowDetails;
    }
}
