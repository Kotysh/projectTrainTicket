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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ConnectionDAO connectionDAO = new ConnectionDAO();
        String station = req.getParameter("station");
        String city_id = req.getParameter("city_id");
        if (city_id.length() == 0){
            connectionDAO.operatorDML("insert into station (station) values ('"+station+"')");
        }else{
            connectionDAO.operatorDML("insert into station (station, city_id) values ('"+station+"', "+city_id+")");
        }
        connectionDAO.disconnect();

        req.getRequestDispatcher("station").forward(req, resp);

    }
}
