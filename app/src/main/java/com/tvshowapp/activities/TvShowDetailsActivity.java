package com.tvshowapp.activities;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.tvshowapp.R;
import com.tvshowapp.adapters.ImageSliderAdapter;
import com.tvshowapp.databinding.ActivityTvShowDetailsBinding;
import com.tvshowapp.viewmodels.TvShowDetailsViewModel;

import java.util.Locale;

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
        activityTvShowDetailsBinding.imageBack.setOnClickListener(view -> onBackPressed());
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

                    activityTvShowDetailsBinding.setTvShowImageUrl(
                            tvShowDetailsResponse.getTvShowDetails().getImagePath()
                    );
                    activityTvShowDetailsBinding.imageTVShow.setVisibility(View.VISIBLE);
                    activityTvShowDetailsBinding.setDescription(String.valueOf(
                            HtmlCompat.fromHtml(
                                    tvShowDetailsResponse.getTvShowDetails().getDescription(),
                                    HtmlCompat.FROM_HTML_MODE_LEGACY
                            )
                    ));
                    activityTvShowDetailsBinding.textDescription.setVisibility(View.VISIBLE);
                    activityTvShowDetailsBinding.textReadMore.setVisibility(View.VISIBLE);
                    activityTvShowDetailsBinding.textReadMore.setOnClickListener(view -> {
                        if (activityTvShowDetailsBinding.textReadMore.getText().toString().equals("Read More")) {
                            activityTvShowDetailsBinding.textDescription.setMaxLines(Integer.MAX_VALUE);
                            activityTvShowDetailsBinding.textDescription.setEllipsize(null);
                            activityTvShowDetailsBinding.textReadMore.setText(R.string.read_less);
                        } else {
                            activityTvShowDetailsBinding.textDescription.setMaxLines(4);
                            activityTvShowDetailsBinding.textDescription.setEllipsize(TextUtils.TruncateAt.END);
                            activityTvShowDetailsBinding.textReadMore.setText(R.string.read_more);
                        }
                    });
                    activityTvShowDetailsBinding.setRating(
                            String.format(
                                    Locale.getDefault(),
                                    "%.2f",
                                    Double.parseDouble(tvShowDetailsResponse.getTvShowDetails().getRating())
                            )
                    );

                    if(tvShowDetailsResponse.getTvShowDetails().getGenres() != null){
                        activityTvShowDetailsBinding.setGenre(tvShowDetailsResponse.getTvShowDetails().getGenres()[0]);
                    }else{
                        activityTvShowDetailsBinding.setGenre("N/A");
                    }
                    activityTvShowDetailsBinding.setRuntime(tvShowDetailsResponse.getTvShowDetails().getRuntime() + " Min");
                    activityTvShowDetailsBinding.viewDivider1.setVisibility(View.VISIBLE);
                    activityTvShowDetailsBinding.layoutMisc.setVisibility(View.VISIBLE);
                    activityTvShowDetailsBinding.viewDivider2.setVisibility(View.VISIBLE);
                    loadBasicTvShowDetails();
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

    private void loadBasicTvShowDetails() {
        activityTvShowDetailsBinding.setTvShowName(getIntent().getStringExtra("name"));
        activityTvShowDetailsBinding.setNetworkCountry(getIntent().getStringExtra(
                "network") + "(" + getIntent().getStringExtra("country") + ")");
        activityTvShowDetailsBinding.setStatus(getIntent().getStringExtra("status"));
        activityTvShowDetailsBinding.setStartedDate(getIntent().getStringExtra("status"));

        activityTvShowDetailsBinding.textName.setVisibility(View.VISIBLE);
        activityTvShowDetailsBinding.textNetworkCountry.setVisibility(View.VISIBLE);
        activityTvShowDetailsBinding.textStatus.setVisibility(View.VISIBLE);
        activityTvShowDetailsBinding.textStarted.setVisibility(View.VISIBLE);
    }
}