package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.DeleteDAO;
import ru.dmitriykotyshov.support.ServiceHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.dmitriykotyshov.other.Message.noRight;
import static ru.dmitriykotyshov.other.ValidAdmin.validationSuperAdmin;

/**
 * Created by Дмитрий on 14.01.2018.
 */
public class DeleteDocument extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String typeAdmin = (String) req.getSession().getAttribute("typeAdmin");

        if(typeAdmin != null && validationSuperAdmin(Integer.valueOf(typeAdmin))) {

            req.setCharacterEncoding("UTF-8");

            DeleteDAO deleteDAO = ServiceHelper.getInstance("deleteDAO");

            String  documentId = (req.getParameter("document"));

            deleteDAO.deleteDocument(documentId);

            req.getRequestDispatcher("document").forward(req, resp);

        }else{

            if (typeAdmin!= null) noRight(req, resp);
            else req.getRequestDispatcher("admin/inputAdmin.jsp").forward(req, resp);

        }


    }
}
