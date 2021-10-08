package com.tvshowapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.tvshowapp.R;
import com.tvshowapp.adapters.ImageSliderAdapter;
import com.tvshowapp.databinding.ActivityTvShowDetailsBinding;
import com.tvshowapp.viewmodels.TvShowDetailsViewModel;

public class TvShowDetailsActivity extends AppCompatActivity {

    private ActivityTvShowDetailsBinding activityTvShowDetailsBinding;
    private TvShowDetailsViewModel tvShowDetailsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTvShowDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show_details);
        init();
    }

    private void init() {
        tvShowDetailsViewModel = new ViewModelProvider(this).get(TvShowDetailsViewModel.class);
        getTvShowDetails();
    }

    private void getTvShowDetails() {
        activityTvShowDetailsBinding.setIsLoading(true);
        String tvShowId = String.valueOf(getIntent().getIntExtra("id", -1));
        tvShowDetailsViewModel.getTvShowDetails(tvShowId).observe(
                this, tvShowDetailsResponse -> {
                    activityTvShowDetailsBinding.setIsLoading(false);
                    if (tvShowDetailsResponse.getTvShowDetails() != null
                            && tvShowDetailsResponse.getTvShowDetails().getImagePath() != null) {
                        loadSliderImage(tvShowDetailsResponse.getTvShowDetails().getPictures());
                    }
                }

        );
    }

    private void loadSliderImage(String[] sliderImages) {
        activityTvShowDetailsBinding.sliderViewPager.setOffscreenPageLimit(1);
        activityTvShowDetailsBinding.sliderViewPager.setAdapter(new ImageSliderAdapter(sliderImages));
        activityTvShowDetailsBinding.sliderViewPager.setVisibility(View.VISIBLE);
        activityTvShowDetailsBinding.viewFadingEdge.setVisibility(View.VISIBLE);
        setupSliderIndicators(sliderImages.length);
        activityTvShowDetailsBinding.sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentSliderIndicator(position);
            }
        });
    }

    private void setupSliderIndicators(int count) {
        ImageView[] indicator = new ImageView[count];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicator.length; i++) {
            indicator[i] = new ImageView(getApplicationContext());
            indicator[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.background_slider_indicator_inactive
            ));
            indicator[i].setLayoutParams(layoutParams);
            activityTvShowDetailsBinding.layoutSliderIndicators.addView(indicator[i]);
        }
        activityTvShowDetailsBinding.layoutSliderIndicators.setVisibility(View.VISIBLE);
        setCurrentSliderIndicator(0);
    }

    private void setCurrentSliderIndicator(int position) {
        int childCount = activityTvShowDetailsBinding.layoutSliderIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) activityTvShowDetailsBinding.layoutSliderIndicators.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getApplicationContext(),
                        R.drawable.backround_slider_indicator_active
                ));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(
                        getApplicationContext(),
                        R.drawable.background_slider_indicator_inactive));
            }
        }
    }
}