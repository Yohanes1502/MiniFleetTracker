package com.example.minifleettracker.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.minifleettracker.data.model.TripEntity;

import java.util.List;

@Dao
public interface TripDao {
    @Insert
    void insert(TripEntity trip);

    @Query("SELECT * FROM trip_table ORDER BY timestamp DESC")
    LiveData<List<TripEntity>> getAllTrips();
}
