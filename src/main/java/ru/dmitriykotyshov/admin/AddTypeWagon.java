package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.ConnectionDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Дмитрий on 25.01.2018.
 */
public class AddTypeWagon extends HttpServlet {

    private final static String INSERT_TYPE_WAGON = "INSERT INTO \"DIMA\".\"TYPE_WAGON\" (TYPE_NAME, BIO_TIOLET, AIR_CONDITION, COUNT_PLACE) " +
            "VALUES ('%s', %s, %s, '%s')";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ConnectionDAO connectionDAO = new ConnectionDAO();
        String typeWagon = req.getParameter("typeWagon");
        String bioTiolet = req.getParameter("bioTiolet");
        String airCondition = req.getParameter("airCondition");
        String countPlace = req.getParameter("countPlace");

        if (bioTiolet.length() == 0) bioTiolet = "null"; else bioTiolet = "'"+bioTiolet+"'";
        if (airCondition.length() == 0) airCondition = "null"; else airCondition = "'"+airCondition+"'";

        String insert = String.format(INSERT_TYPE_WAGON, typeWagon, bioTiolet, airCondition, countPlace);

        connectionDAO.operatorDML(insert);
        connectionDAO.disconnect();

        req.getRequestDispatcher("typewagon").forward(req, resp);

    }
}
