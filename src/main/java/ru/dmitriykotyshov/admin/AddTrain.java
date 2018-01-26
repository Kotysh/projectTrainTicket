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
public class AddTrain extends HttpServlet {

    private final static String INSERT_WAGON = "INSERT INTO \"DIMA\".\"TRAIN\" (NUMBER_TRAIN, ROUTE_ID) VALUES ('%s', '%s')";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ConnectionDAO connectionDAO = new ConnectionDAO();
        String numberTrain = req.getParameter("numberTrain");
        String routeID = req.getParameter("routeID");


        String insert = String.format(INSERT_WAGON, numberTrain, routeID);

        connectionDAO.operatorDML(insert);
        connectionDAO.disconnect();

        req.getRequestDispatcher("train").forward(req, resp);

    }
}
