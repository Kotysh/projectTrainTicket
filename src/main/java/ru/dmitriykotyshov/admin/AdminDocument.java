package ru.dmitriykotyshov.admin;

import ru.dmitriykotyshov.DAO.ConnectionDAO;
import ru.dmitriykotyshov.trainticketobjects.City;
import ru.dmitriykotyshov.trainticketobjects.Document;

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
 * Created by Дмитрий on 13.01.2018.
 */
public class AdminDocument extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect("select * from document");

        List<Document> documents = new ArrayList<Document>();

        try {
            while (resultSet.next()){
                documents.add(new Document(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionDAO.disconnect();

        req.setAttribute("documents", documents);
        req.getRequestDispatcher("admin/document.jsp").forward(req, resp);

    }
}
