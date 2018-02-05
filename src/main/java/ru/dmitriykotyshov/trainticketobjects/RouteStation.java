package ru.dmitriykotyshov.trainticketobjects;

import java.sql.Timestamp;

/**
 * Created by Дмитрий on 26.01.2018.
 */
public class RouteStation {

    private int routeStationId;
    private Route routeId;
    private Station stationId;
    private int orderStation;
    private Timestamp arrivalTime;
    private Timestamp departureTime;
    private int distance;

    public RouteStation(int routeStationId, Route routeId, Station stationId, int orderStation, Timestamp arrivalTime, Timestamp departureTime, int distance) {
        this.routeStationId = routeStationId;
        this.routeId = routeId;
        this.stationId = stationId;
        this.orderStation = orderStation;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.distance = distance;
    }

    public RouteStation(int routeStationId) {
        this.routeStationId = routeStationId;
    }

    public int getRouteStationId() {
        return routeStationId;
    }

    public void setRouteStationId(int routeStationId) {
        this.routeStationId = routeStationId;
    }

    public Route getRouteId() {
        return routeId;
    }

    public void setRouteId(Route routeId) {
        this.routeId = routeId;
    }

    public Station getStationId() {
        return stationId;
    }

    public void setStationId(Station stationId) {
        this.stationId = stationId;
    }

    public int getOrderStation() {
        return orderStation;
    }

    public void setOrderStation(int orderStation) {
        this.orderStation = orderStation;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Timestamp arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
