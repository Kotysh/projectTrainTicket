package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.InsertDAO;
import ru.dmitriykotyshov.support.ServiceHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Дмитрий on 25.01.2018.
 */
public class AddTypeWagon extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        InsertDAO insertDAO = ServiceHelper.getInstance("insertDAO");

        String typeWagon = req.getParameter("typeWagon");
        String bioTiolet = req.getParameter("bioTiolet");
        String airCondition = req.getParameter("airCondition");
        String countPlace = req.getParameter("countPlace");

        insertDAO.insertTypeWagon(typeWagon, bioTiolet, airCondition, countPlace);

        req.getRequestDispatcher("typewagon").forward(req, resp);

    }
}
