package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.ConnectionDAO;
import ru.dmitriykotyshov.trainticketobjects.City;
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
 * Created by Дмитрий on 13.01.2018.
 */
public class AdminStation extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect("select station.station_id, station.station, city.city_id, city.city from city " +
                "right join station on city.city_id = station.city_id " +
                "order by station.station_id");

        List<Station> stations = new ArrayList<Station>();

        try {
            while (resultSet.next()){

                stations.add(new Station(resultSet.getInt(1), resultSet.getString(2),
                        new City(resultSet.getInt(3) == 0 ? 0 : resultSet.getInt(3),
                        resultSet.getString(4) == null ? "null" : resultSet.getString(4))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionDAO.disconnect();

        req.setAttribute("stations", stations);
        req.getRequestDispatcher("admin/station.jsp").forward(req, resp);

    }
}
