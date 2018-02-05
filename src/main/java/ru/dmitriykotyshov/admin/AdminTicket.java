package ru.dmitriykotyshov.admin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dmitriykotyshov.DAO.SelectDAO;
import ru.dmitriykotyshov.trainticketobjects.Ticket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Дмитрий on 26.01.2018.
 */
public class AdminTicket extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springContext.xml");

        SelectDAO selectDAO = applicationContext.getBean("SelectDAO", SelectDAO.class);

        List<Ticket> tickets = selectDAO.getTicketsJoinCustomer();

        req.setAttribute("tickets", tickets);
        req.getRequestDispatcher("admin/ticket.jsp").forward(req, resp);

    }
}
