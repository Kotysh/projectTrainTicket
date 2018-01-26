package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.ConnectionDAO;
import ru.dmitriykotyshov.trainticketobjects.Route;
import ru.dmitriykotyshov.trainticketobjects.Train;
import ru.dmitriykotyshov.trainticketobjects.TypeWagon;
import ru.dmitriykotyshov.trainticketobjects.WagonDB;

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
 * Created by Дмитрий on 26.01.2018.
 */
public class AdminTrain extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect("select train.train_id, train.number_train, route.route_id, route.ROUTE from train " +
                "join route on train.route_id = route.route_id");

        List<Train> trains = new ArrayList<Train>();

        try {
            while (resultSet.next()){
                trains.add(new Train(resultSet.getInt(1), resultSet.getString(2),
                        new Route(resultSet.getInt(3), resultSet.getString(4))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionDAO.disconnect();

        req.setAttribute("trains", trains);
        req.getRequestDispatcher("admin/train.jsp").forward(req, resp);

    }
}
