package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.ConnectionDAO;
import sun.security.jgss.HttpCaller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Дмитрий on 13.01.2018.
 */
public class DeleteStation extends HttpServlet{

    private final static String DELETE_STATION = "delete from station where station_id = %s";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ConnectionDAO connectionDAO = new ConnectionDAO();
        String stationID = (req.getParameter("station"));
        String delete = String.format(DELETE_STATION, stationID);
        connectionDAO.operatorDML(delete);
        connectionDAO.disconnect();

        req.getRequestDispatcher("station").forward(req, resp);

    }
}
