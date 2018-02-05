package ru.dmitriykotyshov.trainticketobjects;

/**
 * Created by Дмитрий on 02.02.2018.
 */
public class RouteDB {

    private int routeId;
    private String nameRoute;

    public RouteDB(int routeId, String nameRoute) {
        this.routeId = routeId;
        this.nameRoute = nameRoute;
    }

    @Override
    public String toString() {
        return "RouteDB{" +
                "routeId=" + routeId +
                ", nameRoute='" + nameRoute + '\'' +
                '}';
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getNameRoute() {
        return nameRoute;
    }

    public void setNameRoute(String nameRoute) {
        this.nameRoute = nameRoute;
    }
}
