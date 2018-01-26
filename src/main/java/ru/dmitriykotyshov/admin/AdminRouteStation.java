package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.ConnectionDAO;
import ru.dmitriykotyshov.trainticketobjects.City;
import ru.dmitriykotyshov.trainticketobjects.Route;
import ru.dmitriykotyshov.trainticketobjects.RouteStation;
import ru.dmitriykotyshov.trainticketobjects.Station;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 26.01.2018.
 */
public class AdminRouteStation extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect("select route_station.route_station_id, route.route_id, route.route, station.station_id, station.station, route_station.order_station, route_station.arrival_time, route_station.departure_time from route_station " +
                "join route on route_station.route_id = route.route_id " +
                "join station on route_station.station_id = station.station_id");

        List<RouteStation> routeStations = new ArrayList<RouteStation>();

        try {
            while (resultSet.next()){
                routeStations.add(new RouteStation(resultSet.getInt(1),
                        new Route(resultSet.getInt(2), resultSet.getString(3)),
                        new Station(resultSet.getInt(4), resultSet.getString(5)),
                        resultSet.getInt(6),
                        resultSet.getTimestamp(7), resultSet.getTimestamp(8)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionDAO.disconnect();

        req.setAttribute("routeStations", routeStations);
        req.getRequestDispatcher("admin/routestation.jsp").forward(req, resp);

    }
}
