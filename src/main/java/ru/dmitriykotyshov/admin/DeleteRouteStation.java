package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.ConnectionDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Дмитрий on 26.01.2018.
 */
public class DeleteRouteStation extends HttpServlet {

    private final static String DELETE_ROUTE_STATION = "delete from route_station where route_station_id = %s";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ConnectionDAO connectionDAO = new ConnectionDAO();
        String routeStationID = (req.getParameter("routeStation"));
        String delete = String.format(DELETE_ROUTE_STATION, routeStationID);
        connectionDAO.operatorDML(delete);
        connectionDAO.disconnect();

        req.getRequestDispatcher("routestation").forward(req, resp);

    }
}
