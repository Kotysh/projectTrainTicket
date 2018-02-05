package ru.dmitriykotyshov.admin;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dmitriykotyshov.DAO.DeleteDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.dmitriykotyshov.DAO.sql.DeleteSQL.getSqlDeleteStation;

/**
 * Created by Дмитрий on 13.01.2018.
 */
public class DeleteStation extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springContext.xml");

        DeleteDAO deleteDAO = applicationContext.getBean("DeleteDAO", DeleteDAO.class);

        String stationId = req.getParameter("station");

        deleteDAO.deleteStation(stationId);

        req.getRequestDispatcher("station").forward(req, resp);

    }
}
