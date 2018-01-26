package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.ConnectionDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Дмитрий on 16.01.2018.
 */
public class DeleteRoute extends HttpServlet {

    private final static String DELETE_ROUTE = "delete from route where route_id = %s";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ConnectionDAO connectionDAO = new ConnectionDAO();
        String  routeID = (req.getParameter("route"));
        String delete = String.format(DELETE_ROUTE, routeID);
        connectionDAO.operatorDML(delete);
        connectionDAO.disconnect();

        req.getRequestDispatcher("route").forward(req, resp);

    }
}
