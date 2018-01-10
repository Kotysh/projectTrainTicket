package ru.dmitriykotyshov.trainticketobjects;

import java.util.List;

/**
 * Created by Дмитрий on 09.01.2018.
 */
public class Route {

    private int id;
    private String nameRoute;
    private Station firstStation;
    private Station secondSttaion;

    public Route(int id, String nameRoute, Station firstStation, Station secondSttaion) {
        this.id = id;
        this.nameRoute = nameRoute;
        this.firstStation = firstStation;
        this.secondSttaion = secondSttaion;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", nameRoute='" + nameRoute + '\'' +
                ", firstStation=" + firstStation +
                ", secondSttaion=" + secondSttaion +
                '}';
    }

    public Station getFirstStation() {
        return firstStation;
    }

    public void setFirstStation(Station firstStation) {
        this.firstStation = firstStation;
    }

    public Station getSecondStation() {
        return secondSttaion;
    }

    public void setSecondSttaion(Station secondSttaion) {
        this.secondSttaion = secondSttaion;
    }

    public int getId() {
        return id;
    }

    public void setId(int numberRoute) {
        this.id = numberRoute;
    }

    public String getNameRoute() {
        return nameRoute;
    }

    public void setNameRoute(String nameRoute) {
        this.nameRoute = nameRoute;
    }

}
