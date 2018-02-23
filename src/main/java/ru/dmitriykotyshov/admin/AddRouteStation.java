package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.InsertDAO;
import ru.dmitriykotyshov.support.ServiceHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Дмитрий on 26.01.2018.
 */
public class AddRouteStation extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        InsertDAO insertDAO = ServiceHelper.getInstance("insertDAO");

        String routeId = req.getParameter("routeId");
        String stationId = req.getParameter("stationId");
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

        Integer distance = Integer.valueOf(req.getParameter("distance"));

        insertDAO.insertRouteStation(routeId, stationId, orderStation, arrivalYear, arrivalMonth, arrivalDay, arrivalHour, arrivalMinute,
                departureYear, departureMonth, departureDay, departureHour, departureMinute, distance);

        req.getRequestDispatcher("routestation").forward(req, resp);

    }
}
