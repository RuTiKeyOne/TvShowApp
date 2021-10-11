package com.tvshowapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.tvshowapp.dao.TvShowDao;
import com.tvshowapp.models.TvShow;

@Database(entities = TvShow.class, version = 1, exportSchema = false)
public abstract class TvShowDatabase extends RoomDatabase {

    private static TvShowDatabase tvShowDatabase;

    public static synchronized TvShowDatabase getTvShowDatabase(Context context){
        if(null == tvShowDatabase){
                tvShowDatabase = Room.databaseBuilder(
                        context,
                        TvShowDatabase.class,
                        "tv_shows_db"
                ).allowMainThreadQueries().build();
        }

        return tvShowDatabase;
    }

    public abstract TvShowDao tvShowDao();

}
