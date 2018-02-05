package ru.dmitriykotyshov.trainticketobjects;

import ru.dmitriykotyshov.other.Price;

import java.sql.Timestamp;

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
    private int distance;
    private int price;

    public Route(int id, String nameRoute, Station firstStation, Station secondSttaion,
                 Timestamp timeDateFirstStation, Timestamp timeDateSecondStation,int distanceFirstStation, int distanceSecondStation) {
        this.id = id;
        this.nameRoute = nameRoute;
        this.firstStation = firstStation;
        this.secondStation = secondSttaion;
        this.timeDateFirstStation = timeDateFirstStation;
        this.timeDateSecondStation = timeDateSecondStation;
        this.distance = distanceSecondStation - distanceFirstStation;
        this.price = (int)((distanceSecondStation - distanceFirstStation)*Price.COEFFICIENT_PRICE);
    }

    public Route(int id, String nameRoute, Station firstStation, Station secondStation, Timestamp timeDateFirstStation, Timestamp timeDateSecondStation) {
        this.id = id;
        this.nameRoute = nameRoute;
        this.firstStation = firstStation;
        this.secondStation = secondStation;
        this.timeDateFirstStation = timeDateFirstStation;
        this.timeDateSecondStation = timeDateSecondStation;
    }

    public Route(int id, String nameRoute){
        this.id = id;
        this.nameRoute = nameRoute;
    }

    public Route() {
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", nameRoute='" + nameRoute + '\'' +
                ", firstStation=" + firstStation +
                ", secondStation=" + secondStation +
                ", timeDateFirstStation=" + timeDateFirstStation +
                ", timeDateSecondStation=" + timeDateSecondStation +
                ", distanec=" + distance+
                ", price=" + price +
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

    public void setTimeDateSecondStation(Timestamp timeDateSecondStation) {
        this.timeDateSecondStation = timeDateSecondStation;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
