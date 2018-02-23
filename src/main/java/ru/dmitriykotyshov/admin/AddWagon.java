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
public class AddWagon extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        InsertDAO insertDAO = ServiceHelper.getInstance("insertDAO");

        String trainId = req.getParameter("trainId");
        String typeWagonId = req.getParameter("typeWagonId");
        String orderWagon = req.getParameter("orderWagon");

        insertDAO.insertWagon(trainId, typeWagonId, orderWagon);

        req.getRequestDispatcher("wagon").forward(req, resp);

    }
}
