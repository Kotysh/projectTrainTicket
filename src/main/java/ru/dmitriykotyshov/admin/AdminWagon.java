package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.ConnectionDAO;
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
 * Created by Дмитрий on 25.01.2018.
 */
public class AdminWagon extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect("select wagon.wagon_id, train.train_id, train.number_train, type_wagon.type_wagon_id, type_wagon.type_name, wagon.order_wagon from wagon " +
                "join type_wagon on wagon.TYPE_WAGON_ID = type_wagon.type_wagon_id " +
                "join train on wagon.train_id = train.train_id");

        List<WagonDB> wagons = new ArrayList<WagonDB>();

        try {
            while (resultSet.next()){
                wagons.add(new WagonDB(resultSet.getInt(1), new Train(resultSet.getInt(2),resultSet.getString(3)),
                        new TypeWagon(resultSet.getInt(4), resultSet.getString(5)), resultSet.getInt(6)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionDAO.disconnect();

        req.setAttribute("wagons", wagons);
        req.getRequestDispatcher("admin/wagon.jsp").forward(req, resp);

    }
}
