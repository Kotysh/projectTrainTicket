package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.InsertDAO;
import ru.dmitriykotyshov.support.ServiceHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static ru.dmitriykotyshov.other.Message.noRight;
import static ru.dmitriykotyshov.other.ValidAdmin.validationSuperAdmin;

/**
 * Created by Дмитрий on 25.01.2018.
 */
public class AddCustomer extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String typeAdmin = (String) req.getSession().getAttribute("typeAdmin");

        if(typeAdmin != null && validationSuperAdmin(Integer.valueOf(typeAdmin))){

            req.setCharacterEncoding("UTF-8");

            InsertDAO insertDAO = ServiceHelper.getInstance("insertDAO");

            String first_name = req.getParameter("firstName");
            String middle_name = req.getParameter("middleName");
            String last_name = req.getParameter("lastName");
            Date birthday = new Date();
            String gender = req.getParameter("gender");
            String documentID = req.getParameter("document");
            String documentNumber = req.getParameter("documentNumber");
            String email = req.getParameter("email");
            String telephone = req.getParameter("telephone");

            insertDAO.insertCustomer(first_name, middle_name, last_name, birthday, gender, documentID, documentNumber, email, telephone);

            req.getRequestDispatcher("customer").forward(req, resp);

        }else{

            if (typeAdmin!= null) noRight(req, resp);
            else req.getRequestDispatcher("admin/inputAdmin.jsp").forward(req, resp);

        }

    }
}
