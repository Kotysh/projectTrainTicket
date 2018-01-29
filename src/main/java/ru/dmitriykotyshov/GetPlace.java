package ru.dmitriykotyshov;

import ru.dmitriykotyshov.trainticketobjects.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import static ru.dmitriykotyshov.DAO.SelectDAO.getPlaces;

/**
 * Created by Дмитрий on 22.01.2018.
 */
public class GetPlace extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        Integer WagonId = Integer.valueOf(req.getParameter("idWagon"));
        String typeWagon = (String) req.getParameter("typeWagon");
        Boolean bioTiolet = ((String)req.getParameter("bioTiolet")).equals("true")?true:false;
        Boolean airCondition = ((String)req.getParameter("airCondition")).equals("true")?true:false;
        Integer order = Integer.valueOf(req.getParameter("order"));
        Integer countPlace = Integer.valueOf(req.getParameter("countPlace"));


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

        Wagon wagon = new Wagon(WagonId, train, typeWagon, bioTiolet, airCondition, order, countPlace);
        System.out.println("Конечный результат!");
        System.out.println(wagon);
        System.out.println("Ведем поиск...");
        Set<Integer> places = getPlaces(wagon);
        System.out.println("Количество--> "+places.size());
        for (Integer i: places){
            System.out.println(i);
        }

        req.setAttribute("wagon", wagon);
        req.setAttribute("listPlaces", places);
        req.getRequestDispatcher("place.jsp").forward(req, resp);

    }
}
