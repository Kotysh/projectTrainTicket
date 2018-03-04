package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.InsertDAO;
import ru.dmitriykotyshov.support.ServiceHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static ru.dmitriykotyshov.other.MyDate.dateFormat;

/**
 * Created by Дмитрий on 26.01.2018.
 */
public class AddTicket extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        InsertDAO insertDAO = ServiceHelper.getInstance("insertDAO");

        Integer customerId = Integer.valueOf(req.getParameter("customerId"));
        Integer wagonId = Integer.valueOf(req.getParameter("wagonId"));
        String place = req.getParameter("place");

        Date dateBuy = new Date();

        Integer fistrRouteStationId = Integer.valueOf(req.getParameter("firstRouteStationId"));
        Integer secondRouteStationId = Integer.valueOf(req.getParameter("secondRouteStationId"));

        int price = Integer.valueOf(req.getParameter("price"));

        insertDAO.insertTicket(customerId, wagonId, place, dateFormat(dateBuy), fistrRouteStationId, secondRouteStationId, price);

        req.getRequestDispatcher("ticket").forward(req, resp);

    }

}
