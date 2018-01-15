package ru.dmitriykotyshov.trainticketobjects;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Дмитрий on 09.01.2018.
 */
public class Route {

    private int id;
    private String nameRoute;
    private Station firstStation;
    private Station secondStation;
    private Timestamp timeDateFirstStation;
    private Timestamp timeDateSecondStation;

    public Route(int id, String nameRoute, Station firstStation, Station secondSttaion, Timestamp timeDateFirstStation, Timestamp timeDateSecondStation) {
        this.id = id;
        this.nameRoute = nameRoute;
        this.firstStation = firstStation;
        this.secondStation = secondSttaion;
        this.timeDateFirstStation = timeDateFirstStation;
        this.timeDateSecondStation = timeDateSecondStation;
    }

    public Route() {
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", nameRoute='" + nameRoute + '\'' +
                ", firstStation=" + firstStation +
                ", secondSttaion=" + secondStation +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameRoute() {
        return nameRoute;
    }

    public void setNameRoute(String nameRoute) {
        this.nameRoute = nameRoute;
    }

    public Station getFirstStation() {
        return firstStation;
    }

    public void setFirstStation(Station firstStation) {
        this.firstStation = firstStation;
    }

    public Station getSecondStation() {
        return secondStation;
    }

    public void setSecondStation(Station secondStation) {
        this.secondStation = secondStation;
    }

    public Timestamp getTimeDateFirstStation() {
        return timeDateFirstStation;
    }

    public void setTimeDateFirstStation(Timestamp timeDateFirstStation) {
        this.timeDateFirstStation = timeDateFirstStation;
    }

    public Timestamp getTimeDateSecondStation() {
        return timeDateSecondStation;
    }

    public void setGetTimeDateSecondStation(Timestamp getTimeDateSecondStation) {
        this.timeDateSecondStation = getTimeDateSecondStation;
    }
}
