package ru.dmitriykotyshov.other;

import java.sql.Timestamp;

/**
 * Created by Дмитрий on 03.02.2018.
 */
public class PairTimestampDistance {

    Timestamp timestamp;
    int distance;

    public PairTimestampDistance(Timestamp timestamp, int distance) {
        this.timestamp = timestamp;
        this.distance = distance;
    }

    public PairTimestampDistance() {
    }

    @Override
    public String toString() {
        return "PairTimestampDistance{" +
                "timestamp=" + timestamp +
                ", distance=" + distance +
                '}';
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
