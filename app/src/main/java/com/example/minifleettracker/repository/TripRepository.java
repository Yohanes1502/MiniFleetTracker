package com.example.minifleettracker.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.minifleettracker.data.local.TripDao;
import com.example.minifleettracker.data.local.TripDatabase;
import com.example.minifleettracker.data.model.TripEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TripRepository {
    private TripDao tripDao;
    private LiveData<List<TripEntity>> allTrips;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public TripRepository(Application application) {
        TripDatabase db = TripDatabase.getInstance(application);
        tripDao = db.tripDao();
        allTrips = tripDao.getAllTrips();
    }

    public void insert(TripEntity trip) {
        executor.execute(() -> tripDao.insert(trip));
    }

    public LiveData<List<TripEntity>> getAllTrips() {
        return allTrips;
    }
}
