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
public class AddWagon extends HttpServlet {

    private final static String INSERT_WAGON = "INSERT INTO \"DIMA\".\"WAGON\" (TRAIN_ID, TYPE_WAGON_ID, ORDER_WAGON)" +
            "VALUES ('%s', '%s', '%s')";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ConnectionDAO connectionDAO = new ConnectionDAO();
        String trainID = req.getParameter("trainID");
        String typeWagonID = req.getParameter("typeWagonID");
        String orderWagon = req.getParameter("orderWagon");

        String insert = String.format(INSERT_WAGON, trainID, typeWagonID, orderWagon);

        connectionDAO.operatorDML(insert);
        connectionDAO.disconnect();

        req.getRequestDispatcher("wagon").forward(req, resp);

    }
}
