package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.SelectDAO;
import ru.dmitriykotyshov.support.ServiceHelper;
import ru.dmitriykotyshov.trainticketobjects.RouteStation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Дмитрий on 26.01.2018.
 */
public class AdminRouteStation extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SelectDAO selectDAO = ServiceHelper.getInstance("selectDAO");

        List<RouteStation> routeStations = selectDAO.getRouteStationsJoinRouteAndStation();

        req.setAttribute("routeStations", routeStations);
        req.getRequestDispatcher("admin/routestation.jsp").forward(req, resp);

    }
}
