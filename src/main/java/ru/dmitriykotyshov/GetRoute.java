package ru.dmitriykotyshov;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dmitriykotyshov.DAO.SelectDAO;
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
import java.util.*;


/**
 * Created by Дмитрий on 08.01.2018.
 */
public class GetRoute extends HttpServlet {

    private final static Logger logger = Logger.getLogger(GetRoute.class);

    @Override
    protected void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springContext.xml");

        SelectDAO selectDAO = applicationContext.getBean("SelectDAO", SelectDAO.class);



        logger.trace("fields...");
        String fieldOne = new String(req.getParameter("stationOne")).toLowerCase();
        String fieldTwo = new String(req.getParameter("stationTwo")).toLowerCase();
        logger.trace("first field= "+fieldOne + " second field= " + fieldTwo);

        String date = req.getParameter("date");
        logger.trace("date = "+date);
        MyDate myDate = new MyDate(date);
        logger.trace("myDate = "+myDate);


        logger.trace("search cities...");
        City cityOne = selectDAO.getCity(fieldOne);
        City cityTwo = selectDAO.getCity(fieldTwo);
        logger.trace(cityOne);
        logger.trace(cityTwo);

        logger.trace("search stations...");
        List<Station> stationsCityOne = selectDAO.getStations(fieldOne, cityOne);
        List<Station> stationsCityTwo = selectDAO.getStations(fieldTwo, cityTwo);
        logger.trace(stationsCityOne);
        logger.trace(stationsCityTwo);

        logger.trace("search routes...");
        List<Route> routes = selectDAO.getRoutes(stationsCityOne, stationsCityTwo, myDate);

        for (Route r : routes) {
            logger.trace(r);
        }

        logger.trace("search trains...");
        List<Train> trains = new ArrayList<Train>();

        for (int i=0; i<routes.size(); i++){

            trains.addAll(selectDAO.getTrain(routes.get(i)));

        }

        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(trains);
        logger.trace("send json - "+str);

        req.setAttribute("json", str);
        req.getRequestDispatcher("routes.jsp").forward(req, resp);

    }

}
