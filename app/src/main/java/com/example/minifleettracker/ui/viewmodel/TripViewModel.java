package com.example.minifleettracker.ui.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.minifleettracker.data.model.TripEntity;
import com.example.minifleettracker.repository.TripRepository;

import java.util.List;

public class TripViewModel extends AndroidViewModel {
    private TripRepository repository;
    private LiveData<List<TripEntity>> allTrips;

    public TripViewModel(@NonNull Application application) {
        super(application);
        repository = new TripRepository(application);
        allTrips = repository.getAllTrips();
    }

    public void insert(TripEntity trip) {
        repository.insert(trip);
    }

    public LiveData<List<TripEntity>> getAllTrips() {
        return allTrips;
    }
}
