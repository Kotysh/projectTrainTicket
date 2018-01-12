package ru.dmitriykotyshov;

import ru.dmitriykotyshov.DAO.ConnectionDAO;
import ru.dmitriykotyshov.other.MyDate;
import ru.dmitriykotyshov.trainticketobjects.City;
import ru.dmitriykotyshov.trainticketobjects.Route;
import ru.dmitriykotyshov.trainticketobjects.Station;
import ru.dmitriykotyshov.trainticketobjects.Train;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import static ru.dmitriykotyshov.DAO.SelectDAO.*;

/**
 * Created by Дмитрий on 08.01.2018.
 */
public class GetRoute extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        String fieldOne = new String(req.getParameter("stationOne")).toLowerCase();
        String fieldTwo = new String(req.getParameter("stationTwo")).toLowerCase();

        Integer year = Integer.valueOf(req.getParameter("year"));
        Integer month = Integer.valueOf(req.getParameter("month"));
        Integer day = Integer.valueOf(req.getParameter("day"));

        MyDate myDate = new MyDate(year, month, day);
/*
        GregorianCalendar gregorianCalendar = new GregorianCalendar(year, month-1, day);
        Timestamp date = new Timestamp(gregorianCalendar.getTimeInMillis());*/


        System.out.println(fieldOne + " " + fieldTwo);

        System.out.println("Поиск городов");

        City cityOne = getCity(fieldOne);
        City cityTwo = getCity(fieldTwo);


        System.out.println("Поиск станций");

        List<Station> stationsCityOne = getStations(fieldOne, cityOne);
        List<Station> stationsCityTwo = getStations(fieldTwo, cityTwo);

        System.out.println("Поиск маршрутов");

        List<Route> routes = getRoutes(stationsCityOne, stationsCityTwo, myDate);

        System.out.println("Поиск поездов");

        List<Train> trains = new ArrayList<Train>();

        for (int i=0; i<routes.size(); i++){

            trains.addAll(getTrain(routes.get(i)));

        }

        req.setAttribute("listTrain", trains);
        req.getRequestDispatcher("routes.jsp").forward(req, resp);

    }

}
