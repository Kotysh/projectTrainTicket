package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.ConnectionDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

/**
 * Created by Дмитрий on 26.01.2018.
 */
public class AddRouteStation extends HttpServlet {

    private final static String INSERT_ROUTE_STATION = "INSERT INTO \"DIMA\".\"ROUTE_STATION\" (ROUTE_ID, STATION_ID, ORDER_STATION, ARRIVAL_TIME, DEPARTURE_TIME) "+
            "VALUES ('%s', '%s', '%s', TO_TIMESTAMP('%s', 'YYYY-MM-DD HH24:MI:SS.FF'), TO_TIMESTAMP('%s', 'YYYY-MM-DD HH24:MI:SS.FF'))";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ConnectionDAO connectionDAO = new ConnectionDAO();
        String routeID = req.getParameter("routeID");
        String stationID = req.getParameter("stationID");
        String orderStation = req.getParameter("orderStation");

        Integer arrivalYear = Integer.valueOf(req.getParameter("arrivalYear"));
        Integer arrivalMonth = Integer.valueOf(req.getParameter("arrivalMonth"));
        Integer arrivalDay = Integer.valueOf(req.getParameter("arrivalDay"));
        Integer arrivalHour = Integer.valueOf(req.getParameter("arrivalHour"));
        Integer arrivalMinute = Integer.valueOf(req.getParameter("arrivalMinute"));

        Integer departureYear = Integer.valueOf(req.getParameter("departureYear"));
        Integer departureMonth = Integer.valueOf(req.getParameter("departureMonth"));
        Integer departureDay = Integer.valueOf(req.getParameter("departureDay"));
        Integer departureHour = Integer.valueOf(req.getParameter("departureHour"));
        Integer departureMinute = Integer.valueOf(req.getParameter("departureMinute"));

        String insert = String.format(INSERT_ROUTE_STATION, routeID, stationID, orderStation,
                new Timestamp(new GregorianCalendar(arrivalYear, arrivalMonth, arrivalDay, arrivalHour, arrivalMinute).getTimeInMillis()),
                new Timestamp(new GregorianCalendar(departureYear, departureMonth, departureDay, departureHour, departureMinute).getTimeInMillis()));

        connectionDAO.operatorDML(insert);
        connectionDAO.disconnect();

        req.getRequestDispatcher("routestation").forward(req, resp);

    }
}
