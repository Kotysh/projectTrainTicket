package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.ConnectionDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Дмитрий on 26.01.2018.
 */
public class DeleteTrain extends HttpServlet {

    private final static String DELETE_TRAIN = "delete from train where train_id = %s";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ConnectionDAO connectionDAO = new ConnectionDAO();
        String trainID = (req.getParameter("train"));
        String delete = String.format(DELETE_TRAIN, trainID);
        connectionDAO.operatorDML(delete);
        connectionDAO.disconnect();

        req.getRequestDispatcher("train").forward(req, resp);

    }
}
