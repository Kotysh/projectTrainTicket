package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.SelectDAO;
import ru.dmitriykotyshov.support.ServiceHelper;
import ru.dmitriykotyshov.trainticketobjects.Customer;
import ru.dmitriykotyshov.trainticketobjects.Document;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Дмитрий on 25.01.2018.
 */
public class AdminCustomer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SelectDAO selectDAO = ServiceHelper.getInstance("selectDAO");

        List<Customer> customers = selectDAO.getCustomersJoinDocument();

        List<Document> documents = selectDAO.getDocuments();

        req.setAttribute("documents", documents);
        req.setAttribute("customers", customers);
        req.getRequestDispatcher("admin/customer.jsp").forward(req, resp);

    }
}
