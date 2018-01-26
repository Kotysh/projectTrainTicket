package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.ConnectionDAO;
import ru.dmitriykotyshov.trainticketobjects.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 26.01.2018.
 */
public class AdminTicket extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect("select ticket.ticket_id, customer.customer_id, customer.first_name, customer.middle_name, customer.last_name, ticket.wagon_id, " +
                "ticket.place, ticket.date_buy, ticket.first_route_station_id, ticket.second_route_station_id from ticket " +
                "join customer on ticket.customer_id = customer.customer_id");

        List<Ticket> tickets = new ArrayList<Ticket>();

        try {
            while (resultSet.next()){
                tickets.add(new Ticket(resultSet.getInt(1),
                        new Customer(resultSet.getInt(2), resultSet.getString(3),
                                resultSet.getString(4), resultSet.getString(5)),
                        new WagonDB(resultSet.getInt(6)), resultSet.getInt(7),resultSet.getDate(8),
                        new RouteStation(resultSet.getInt(9)),
                        new RouteStation(resultSet.getInt(10))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionDAO.disconnect();

        req.setAttribute("tickets", tickets);
        req.getRequestDispatcher("admin/ticket.jsp").forward(req, resp);

    }
}
