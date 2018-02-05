package ru.dmitriykotyshov.admin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dmitriykotyshov.DAO.ConnectionDAO;
import ru.dmitriykotyshov.DAO.DeleteDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.dmitriykotyshov.DAO.sql.DeleteSQL.getSqlDeleteRouteStation;

/**
 * Created by Дмитрий on 26.01.2018.
 */
public class DeleteRouteStation extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springContext.xml");

        DeleteDAO deleteDAO = applicationContext.getBean("DeleteDAO", DeleteDAO.class);

        String routeStationId = (req.getParameter("routeStation"));

        deleteDAO.deleteRouteStation(routeStationId);

        req.getRequestDispatcher("routestation").forward(req, resp);

    }
}
