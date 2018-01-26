package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.ConnectionDAO;
import ru.dmitriykotyshov.other.MyDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.GregorianCalendar;

/**
 * Created by Дмитрий on 26.01.2018.
 */
public class AddTicket extends HttpServlet{

    private final static String INSERT_TICKET = "INSERT INTO \"DIMA\".\"TICKET\" (CUSTOMER_ID, WAGON_ID, PLACE, DATE_BUY, FIRST_ROUTE_STATION_ID, SECOND_ROUTE_STATION_ID)\n" +
            "VALUES ('%s', '%s', '%s', TO_DATE('%s', 'YYYY-MM-DD'), '%s', '%s')";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ConnectionDAO connectionDAO = new ConnectionDAO();
        String customerID = req.getParameter("customerID");
        String wagonID = req.getParameter("wagonID");
        String place = req.getParameter("place");

        Integer year = Integer.valueOf(req.getParameter("year"));
        Integer month = Integer.valueOf(req.getParameter("month"));
        Integer day = Integer.valueOf(req.getParameter("day"));

        MyDate datebuy = new MyDate(year, month, day);

        String fistrRouteStationID = req.getParameter("firstRouteStationID");
        String secondRouteStationID = req.getParameter("secondRouteStationID");

        String insert = String.format(INSERT_TICKET, customerID, wagonID, place, datebuy.toString(), fistrRouteStationID, secondRouteStationID);
        System.out.println(insert);
        connectionDAO.operatorDML(insert);
        connectionDAO.disconnect();

        req.getRequestDispatcher("ticket").forward(req, resp);

    }

}
