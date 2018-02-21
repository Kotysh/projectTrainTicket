package ru.dmitriykotyshov;

import org.apache.log4j.Logger;
import ru.dmitriykotyshov.DAO.SelectDAO;
import ru.dmitriykotyshov.other.Price;
import ru.dmitriykotyshov.support.ServiceHelper;
import ru.dmitriykotyshov.trainticketobjects.City;
import ru.dmitriykotyshov.trainticketobjects.Route;
import ru.dmitriykotyshov.trainticketobjects.Station;
import ru.dmitriykotyshov.trainticketobjects.Train;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by Дмитрий on 08.01.2018.
 */
public class GetRoute extends HttpServlet {

    private final static Logger logger = Logger.getLogger(GetRoute.class);

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        SelectDAO selectDAO = ServiceHelper.getInstance("selectDAO");

        String fieldOne = new String(req.getParameter("stationOne")).toLowerCase().trim();
        String fieldTwo = new String(req.getParameter("stationTwo")).toLowerCase().trim();
        logger.trace("first field = "+fieldOne + " second field = " + fieldTwo);


        String date = req.getParameter("date");
        logger.trace("date = "+date);
        Date dateDeparture = null;
        try {
            dateDeparture = new SimpleDateFormat("dd.MM.yyyy").parse(date);
        } catch (ParseException e) {
            logger.debug("error dateDeparture - ", e);
        }
        logger.trace("dateDeparture = "+dateDeparture);


        logger.trace("search cities...");
        City cityOne = selectDAO.getCity(fieldOne);
        City cityTwo = selectDAO.getCity(fieldTwo);
        logger.trace("cityOne - "+cityOne+"\n"+
                    "cityTwo - "+cityTwo);


        logger.trace("search stations...");
        List<Station> stationsCityOne = selectDAO.getStations(fieldOne, cityOne);
        List<Station> stationsCityTwo = selectDAO.getStations(fieldTwo, cityTwo);
        logger.trace("stations of departure - "+stationsCityOne+"\n"+
                    "stations of arrival - "+stationsCityTwo);


        logger.trace("search routes...");
        List<Route> routes = selectDAO.getRoutes(stationsCityOne, stationsCityTwo, dateDeparture);
        for (Route r : routes) {
            logger.trace(r);
        }


        logger.trace("search trains...");
        List<Train> trains = new ArrayList<Train>();

        for (int i=0; i<routes.size(); i++){

            trains.addAll(selectDAO.getTrain(routes.get(i)));

        }


        logger.trace("price for express");
        for (int i=0 ; i<trains.size(); i++){
            if (trains.get(i).isExpress())
                trains.get(i).getRoute().setPrice((int)(trains.get(i).getRoute().getPrice() * Price.EXPRESS));
        }


        logger.trace("sort train");
        Collections.sort(trains, new Comparator<Train>() {
            public int compare(Train o1, Train o2) {
                return o1.getRoute().getTimeDateFirstStation().compareTo(o2.getRoute().getTimeDateFirstStation());
            }
        });

        logger.trace("all train in route");
        for (Train t : trains){
            logger.trace(t);
        }

        logger.trace("ALL OK!");
        req.getSession().setAttribute("trains", trains);
        req.getRequestDispatcher("routes.jsp").forward(req, resp);

    }

}
