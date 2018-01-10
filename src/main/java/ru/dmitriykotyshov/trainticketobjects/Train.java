package ru.dmitriykotyshov.trainticketobjects;

import java.sql.Date;

/**
 * Created by Дмитрий on 09.01.2018.
 */
public class Train {

    private int id;
    private String numberTrain;
    private Route route;
    private Date dateOut;

    public Train(int id, String numberTrain, Route route, Date dateOut) {
        this.id = id;
        this.numberTrain = numberTrain;
        this.route = route;
        this.dateOut = dateOut;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", numberTrain='" + numberTrain + '\'' +
                ", route='" + route + '\'' +
                ", dateOut=" + dateOut +
                '}';
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

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }
}
