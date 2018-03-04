package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.SelectDAO;
import ru.dmitriykotyshov.support.ServiceHelper;
import ru.dmitriykotyshov.trainticketobjects.Document;
import ru.dmitriykotyshov.trainticketobjects.TypeWagon;
import ru.dmitriykotyshov.trainticketobjects.WagonDB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Дмитрий on 25.01.2018.
 */
public class AdminWagon extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        SelectDAO selectDAO = ServiceHelper.getInstance("selectDAO");

        List<WagonDB> wagons = selectDAO.getWagonsJoinTypeWagonAndTrain();

        List<TypeWagon> typeWagons = selectDAO.getTypeWagons();

        Collections.sort(typeWagons, new Comparator<TypeWagon>() {
            public int compare(TypeWagon o1, TypeWagon o2) {
                return o1.getTypeName().compareTo(o2.getTypeName());
            }
        });

        req.setAttribute("typeWagons", typeWagons);
        req.setAttribute("wagons", wagons);
        req.getRequestDispatcher("admin/wagon.jsp").forward(req, resp);

    }
}
