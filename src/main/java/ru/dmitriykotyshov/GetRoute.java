package ru.dmitriykotyshov;

import ru.dmitriykotyshov.trainticketobjects.City;
import ru.dmitriykotyshov.trainticketobjects.Route;
import ru.dmitriykotyshov.trainticketobjects.Station;
import ru.dmitriykotyshov.trainticketobjects.Train;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

        System.out.println(fieldOne + " " + fieldTwo);

        System.out.println("Поиск городов");

        City cityOne = getCity(fieldOne);
        City cityTwo = getCity(fieldTwo);


        System.out.println("Поиск станций");

        List<Station> stationsCityOne = getStation(fieldOne, cityOne);
        List<Station> stationsCityTwo = getStation(fieldTwo, cityTwo);

        System.out.println("Поиск маршрутов");

        List<Route> routes = null;

        if (stationsCityOne.size() == 0 || stationsCityTwo.size() == 0){
            System.out.println("Между указаннами станциями маршрутов не найдено");
        }else{

            routes = getRoutes(stationsCityOne, stationsCityTwo);
            System.out.println(routes);

            System.out.println("Поиск поездов");

            List<Train> trains = new ArrayList<Train>();

            for (int i=0; i<routes.size(); i++){

                trains.addAll(getTrain(routes.get(i)));

            }

            req.setAttribute("listTrain", trains);
            req.getRequestDispatcher("routes.jsp").forward(req, resp);

        }


    }

}
