package com.tvshowapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.tvshowapp.R;
import com.tvshowapp.databinding.ItemContainerTvShowBinding;
import com.tvshowapp.models.TvShow;

import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder> {

    private List<TvShow> tvShows;
    private LayoutInflater layoutInflater;

    public TvShowAdapter(List<TvShow> tvShows) {
        this.tvShows = tvShows;
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null){
            layoutInflater =  LayoutInflater.from(parent.getContext());
        }

        ItemContainerTvShowBinding tvShowBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container_tv_show, parent, false);
        return new TvShowViewHolder(tvShowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        holder.bindTvShow(tvShows.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    static class TvShowViewHolder extends RecyclerView.ViewHolder{

        private ItemContainerTvShowBinding itemContainerTvShowBinding;

        public TvShowViewHolder(ItemContainerTvShowBinding itemContainerTvShowBinding) {
            super(itemContainerTvShowBinding.getRoot());
            this.itemContainerTvShowBinding = itemContainerTvShowBinding;
        }



        public void bindTvShow(TvShow tvShow){
            itemContainerTvShowBinding.setTvShow(tvShow);
            itemContainerTvShowBinding.executePendingBindings();
        }
    }
}
