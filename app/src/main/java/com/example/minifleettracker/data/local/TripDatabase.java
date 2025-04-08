package com.example.minifleettracker.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.minifleettracker.data.model.TripEntity;

@Database(entities = {TripEntity.class}, version = 1)
public abstract class TripDatabase extends RoomDatabase {
    public abstract TripDao tripDao();

    private static volatile TripDatabase INSTANCE;

    public static TripDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (TripDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TripDatabase.class, "trip_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
