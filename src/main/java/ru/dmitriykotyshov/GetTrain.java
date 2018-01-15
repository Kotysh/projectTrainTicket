package ru.dmitriykotyshov;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.dmitriykotyshov.trainticketobjects.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static ru.dmitriykotyshov.DAO.SelectDAO.getWagons;

/**
 * Created by Дмитрий on 15.01.2018.
 */
public class GetTrain extends HttpServlet {

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Integer idTrain = Integer.valueOf(req.getParameter("idTrain"));
        String numberTrain = (String) req.getParameter("numberTrain");

        Integer idRoute = Integer.valueOf(req.getParameter("idRoute"));
        String nameRoute = (String) req.getParameter("nameRoute");

        Integer firstStationID = Integer.valueOf(req.getParameter("firstStationID"));
        String firstNameStation = (String) req.getParameter("firstNameStation");
        Integer firstStationCityID = Integer.valueOf(req.getParameter("firstStationCityID"));
        String firstStationCityName = (String) req.getParameter("firstStationCityName");

        Integer secondStationID = Integer.valueOf(req.getParameter("secondStationID"));
        String secondNameStation = (String) req.getParameter("secondNameStation");
        Integer secondStationCityID = Integer.valueOf(req.getParameter("secondStationCityID"));
        String secondStationCityName = (String) req.getParameter("secondStationCityName");

        Timestamp firstStationDate = new Timestamp(Long.valueOf(req.getParameter("firstStationDate")));
        Timestamp secondStationDate = new Timestamp(Long.valueOf(req.getParameter("secondStationDate")));

        Train train = new Train(idTrain, numberTrain, new Route(idRoute, nameRoute,
                new Station(firstStationID, firstNameStation, new City(firstStationCityID, firstStationCityName)),
                new Station(secondStationID, secondNameStation, new City(secondStationCityID, secondStationCityName)),
                firstStationDate, secondStationDate));

        System.out.println(train);

        List<Wagon> wagons = getWagons(train);

        req.setAttribute("wagons", wagons);
        req.getRequestDispatcher("train.jsp").forward(req, resp);

    }
}
