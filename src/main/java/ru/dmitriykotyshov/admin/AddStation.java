package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.InsertDAO;
import ru.dmitriykotyshov.support.ServiceHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Дмитрий on 13.01.2018.
 */
public class AddStation extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        InsertDAO insertDAO = ServiceHelper.getInstance("insertDAO");

        String station = req.getParameter("station");
        String cityId = req.getParameter("cityId");

        insertDAO.insertStation(station, cityId);

        req.getRequestDispatcher("station").forward(req, resp);

    }
}
