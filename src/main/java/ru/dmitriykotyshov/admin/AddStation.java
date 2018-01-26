package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.ConnectionDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Дмитрий on 13.01.2018.
 */
public class AddStation extends HttpServlet {

    private final static String INSERT_STATION_SQL = "insert into station (station) values ('%s')";
    private final static String INSERT_STATION_CITYID_SQL = "insert into station (station, city_id) values ('%s', '%s')";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ConnectionDAO connectionDAO = new ConnectionDAO();
        String station = req.getParameter("station");
        String cityID = req.getParameter("cityID");
        String insert;
        if (cityID.length() == 0){
            insert = String.format(INSERT_STATION_SQL, station);
            connectionDAO.operatorDML(insert);
        }else{
            insert = String.format(INSERT_STATION_CITYID_SQL, station, cityID);
            connectionDAO.operatorDML(insert);
        }
        connectionDAO.disconnect();

        req.getRequestDispatcher("station").forward(req, resp);

    }
}
