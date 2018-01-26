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
public class DeleteCustomer extends HttpServlet {

    private final static String DELETE_CUSTOMER = "delete from customer where customer_id = %s";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ConnectionDAO connectionDAO = new ConnectionDAO();
        String  customerID = (req.getParameter("customer"));
        String delete = String.format(DELETE_CUSTOMER, customerID);
        System.out.println(delete);
        connectionDAO.operatorDML(delete);
        connectionDAO.disconnect();

        req.getRequestDispatcher("customer").forward(req, resp);

    }
}
