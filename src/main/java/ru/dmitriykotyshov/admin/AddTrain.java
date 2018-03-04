package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.InsertDAO;
import ru.dmitriykotyshov.support.ServiceHelper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.dmitriykotyshov.other.Message.noRight;
import static ru.dmitriykotyshov.other.ValidAdmin.validationTrainAdmin;

/**
 * Created by Дмитрий on 26.01.2018.
 */
public class AddTrain extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String typeAdmin = (String) req.getSession().getAttribute("typeAdmin");

        if(typeAdmin != null && validationTrainAdmin(Integer.valueOf(typeAdmin))) {

            req.setCharacterEncoding("UTF-8");

            InsertDAO insertDAO = ServiceHelper.getInstance("insertDAO");

            String numberTrain = req.getParameter("numberTrain");
            String routeId = req.getParameter("routeId");
            String express = req.getParameter("express");

            insertDAO.insertTrain(numberTrain, routeId, express);

            req.getRequestDispatcher("train").forward(req, resp);

        }else{

            if (typeAdmin!= null) noRight(req, resp);
            else req.getRequestDispatcher("admin/inputAdmin.jsp").forward(req, resp);

        }

    }
}
