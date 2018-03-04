package ru.dmitriykotyshov.admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Дмитрий on 12.02.2018.
 */
public class ExitAdmin extends HttpServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();
        httpSession.removeAttribute("login");
        httpSession.removeAttribute("password");
        httpSession.removeAttribute("typeAdmin");

        req.getRequestDispatcher("admin/inputAdmin.jsp").forward(req, resp);

    }
}
