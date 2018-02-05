package ru.dmitriykotyshov.admin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dmitriykotyshov.DAO.InsertDAO;
import ru.dmitriykotyshov.other.MyDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Дмитрий on 26.01.2018.
 */
public class AddTicket extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springContext.xml");

        InsertDAO insertDAO = applicationContext.getBean("InsertDAO", InsertDAO.class);

        Integer customerId = Integer.valueOf(req.getParameter("customerId"));
        Integer wagonId = Integer.valueOf(req.getParameter("wagonId"));
        String place = req.getParameter("place");

        Integer year = Integer.valueOf(req.getParameter("year"));
        Integer month = Integer.valueOf(req.getParameter("month"));
        Integer day = Integer.valueOf(req.getParameter("day"));
        MyDate datebuy = new MyDate(year, month, day);

        Integer fistrRouteStationId = Integer.valueOf(req.getParameter("firstRouteStationId"));
        Integer secondRouteStationId = Integer.valueOf(req.getParameter("secondRouteStationId"));

        int price = Integer.valueOf(req.getParameter("price"));

        insertDAO.insertTicket(customerId, wagonId, place, datebuy.toString(), fistrRouteStationId, secondRouteStationId, price);

        req.getRequestDispatcher("ticket").forward(req, resp);

    }

}
