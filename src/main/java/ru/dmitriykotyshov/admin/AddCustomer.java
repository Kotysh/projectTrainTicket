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
 * Created by Дмитрий on 25.01.2018.
 */
public class AddCustomer extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springContext.xml");

        InsertDAO insertDAO = applicationContext.getBean("InsertDAO", InsertDAO.class);

        String first_name = req.getParameter("firstName");
        String middle_name = req.getParameter("middleName");
        String last_name = req.getParameter("lastName");
        MyDate birthday = new MyDate(Integer.valueOf(req.getParameter("year")), Integer.valueOf(req.getParameter("month")), Integer.valueOf(req.getParameter("day")));
        String gender = req.getParameter("gender");
        String documentID = req.getParameter("documentID");
        String documentNumber = req.getParameter("documentNumber");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");

        insertDAO.insertCustomer(first_name, middle_name, last_name, birthday, gender, documentID, documentNumber, email, telephone);

        req.getRequestDispatcher("customer").forward(req, resp);

    }
}
