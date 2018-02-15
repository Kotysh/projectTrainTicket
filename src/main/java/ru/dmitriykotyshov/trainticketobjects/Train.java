package ru.dmitriykotyshov.trainticketobjects;

import java.sql.Timestamp;

/**
 * Created by Дмитрий on 09.01.2018.
 */
public class Train {

    private int id;
    private String numberTrain;
    private Route route;
    private boolean express;

    public Train(int id, String numberTrain, Route route, boolean express) {
        this.id = id;
        this.numberTrain = numberTrain;
        this.route = route;
        this.express = express;
    }

    public Train(int id, String numberTrain, Route route) {
        this.id = id;
        this.numberTrain = numberTrain;
        this.route = route;
    }

    public Train(int id, String numberTrain){
        this.id = id;
        this.numberTrain = numberTrain;
    }

    public Train() {
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", numberTrain='" + numberTrain + '\'' +
                ", route=" + route +
                ", express=" + express +
                '}';
    }

    public boolean isExpress() {
        return express;
    }

    public void setExpress(boolean express) {
        this.express = express;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberTrain() {
        return numberTrain;
    }

    public void setNumberTrain(String numberTrain) {
        this.numberTrain = numberTrain;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

}
