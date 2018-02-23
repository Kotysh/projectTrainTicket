package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.SelectDAO;
import ru.dmitriykotyshov.support.ServiceHelper;
import ru.dmitriykotyshov.trainticketobjects.Station;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Дмитрий on 13.01.2018.
 */
public class AdminStation extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SelectDAO selectDAO = ServiceHelper.getInstance("selectDAO");

        List<Station> stations = selectDAO.getStationsJoinCity();

        req.setAttribute("stations", stations);
        req.getRequestDispatcher("admin/station.jsp").forward(req, resp);

    }
}
