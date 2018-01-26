package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.ConnectionDAO;
import ru.dmitriykotyshov.other.MyDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * Created by Дмитрий on 25.01.2018.
 */
public class AddCustomer extends HttpServlet {

    private final static String INSERT_CUSTOMER = "INSERT INTO \"DIMA\".\"CUSTOMER\" (FIRST_NAME, MIDDLE_NAME, LAST_NAME, BIRTHDAY, GENDER, DOCUMENT_ID, DOC_NUMBER, EMAIL, TELEPHONE) "+
            "VALUES ('%s', '%s', '%s', TO_DATE('%s', 'YYYY-MM-DD'), %s, '%s', '%s', %s, %s)";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ConnectionDAO connectionDAO = new ConnectionDAO();
        String first_name = req.getParameter("firstName");
        String middle_name = req.getParameter("middleName");
        String last_name = req.getParameter("lastName");
        MyDate birthday = new MyDate(Integer.valueOf(req.getParameter("year")), Integer.valueOf(req.getParameter("month")), Integer.valueOf(req.getParameter("day")));
        String gender = req.getParameter("gender");
        String documentID = req.getParameter("documentID");
        String documentNumber = req.getParameter("documentNumber");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");

        if (gender.length() == 0) gender = "null"; else gender = "'"+gender+"'";
        if (email.length() == 0) email = "null"; else email = "'"+email+"'";
        if (telephone.length() == 0) telephone = "null"; else telephone = "'"+telephone+"'";


        String insert = String.format(INSERT_CUSTOMER, first_name, middle_name, last_name,
                birthday.toString(), gender, documentID, documentNumber, email, telephone);
        System.out.println(insert);

        connectionDAO.operatorDML(insert);
        connectionDAO.disconnect();

        req.getRequestDispatcher("customer").forward(req, resp);

    }
}
