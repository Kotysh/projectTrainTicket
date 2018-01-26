package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.ConnectionDAO;
import ru.dmitriykotyshov.trainticketobjects.TypeWagon;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 25.01.2018.
 */
public class AdminTypeWagon extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect("select * from type_wagon");

        List<TypeWagon> typeWagons = new ArrayList<TypeWagon>();

        try {
            while (resultSet.next()){
                typeWagons.add(new TypeWagon(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionDAO.disconnect();

        req.setAttribute("typeWagons", typeWagons);
        req.getRequestDispatcher("admin/typewagon.jsp").forward(req, resp);

    }
}
