package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.SelectDAO;
import ru.dmitriykotyshov.support.ServiceHelper;
import ru.dmitriykotyshov.trainticketobjects.TypeWagon;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Дмитрий on 25.01.2018.
 */
public class AdminTypeWagon extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SelectDAO selectDAO = ServiceHelper.getInstance("selectDAO");

        List<TypeWagon> typeWagons = selectDAO.getTypeWagons();

        req.setAttribute("typeWagons", typeWagons);
        req.getRequestDispatcher("admin/typewagon.jsp").forward(req, resp);

    }
}
