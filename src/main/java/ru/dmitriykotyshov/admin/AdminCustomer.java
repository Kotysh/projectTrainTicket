package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.ConnectionDAO;
import ru.dmitriykotyshov.trainticketobjects.Customer;
import ru.dmitriykotyshov.trainticketobjects.Document;

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
 * Created by Дмитрий on 25.01.2018.
 */
public class AdminCustomer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect("select customer_id, first_name, middle_name, last_name, birthday, gender, document.document_id, document.document, doc_number, " +
                "email, telephone " +
                "from customer " +
                "join document on customer.document_id = document.document_id");

        List<Customer> customers = new ArrayList<Customer>();

        try {
            while (resultSet.next()){
                customers.add(new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getDate(5), resultSet.getString(6), new Document(resultSet.getInt(7), resultSet.getString(8)),
                        resultSet.getString(9), resultSet.getString(10), resultSet.getString(11)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionDAO.disconnect();

        req.setAttribute("customers", customers);
        req.getRequestDispatcher("admin/customer.jsp").forward(req, resp);

    }
}
