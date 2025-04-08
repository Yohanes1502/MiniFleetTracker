package com.example.minifleettracker.data.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "trip_table")
public class TripEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public double latitude;
    public double longitude;
    public int speed;
    public boolean engineOn;
    public boolean doorOpen;
    public long timestamp;

    public TripEntity(double latitude, double longitude, int speed, boolean engineOn, boolean doorOpen, long timestamp) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.speed = speed;
        this.engineOn = engineOn;
        this.doorOpen = doorOpen;
        this.timestamp = timestamp;
    }
}
