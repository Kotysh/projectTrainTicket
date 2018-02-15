package ru.dmitriykotyshov;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dmitriykotyshov.DAO.SelectDAO;
import ru.dmitriykotyshov.trainticketobjects.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Set;

import static ru.dmitriykotyshov.other.MyDate.get5Minute;


/**
 * Created by Дмитрий on 22.01.2018.
 */
public class GetPlace extends HttpServlet {

    private final static Logger logger = Logger.getLogger(GetPlace.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springContext.xml");

        SelectDAO selectDAO = applicationContext.getBean("SelectDAO", SelectDAO.class);



        logger.trace("get info about places...");
        Integer wagonId = Integer.valueOf(req.getParameter("idWagon"));
        String typeWagon = req.getParameter("typeWagon");
        Boolean bioTiolet = (req.getParameter("bioTiolet")).equals("true")?true:false;
        Boolean airCondition = (req.getParameter("airCondition")).equals("true")?true:false;
        Integer order = Integer.valueOf(req.getParameter("order"));
        Integer countPlace = Integer.valueOf(req.getParameter("countPlace"));
        logger.trace("idWagon - "+wagonId);
        logger.trace("typeWagon - "+typeWagon);
        logger.trace("bioTiolet - "+bioTiolet);
        logger.trace("airCondition - "+airCondition);
        logger.trace("order - "+order);
        logger.trace("countPlace - "+countPlace);

        Integer idTrain = Integer.valueOf(req.getParameter("idTrain"));
        String numberTrain = req.getParameter("numberTrain");
        logger.trace("idTrain - "+idTrain);
        logger.trace("numberTrain - "+numberTrain);

        Integer idRoute = Integer.valueOf(req.getParameter("idRoute"));
        String nameRoute = req.getParameter("nameRoute");
        logger.trace("idRoute - "+idRoute);
        logger.trace("nameRoute - "+nameRoute);

        Integer firstStationID = Integer.valueOf(req.getParameter("firstStationID"));
        String firstNameStation = req.getParameter("firstNameStation");
        Integer firstStationCityID = Integer.valueOf(req.getParameter("firstStationCityID"));
        String firstStationCityName = req.getParameter("firstStationCityName");
        logger.trace("firstStationId - "+firstStationID);
        logger.trace("firstNameStation - "+firstNameStation);
        logger.trace("firstStationCityId - "+firstStationCityID);
        logger.trace("firstStationCityName - "+firstStationCityName);

        Integer secondStationID = Integer.valueOf(req.getParameter("secondStationID"));
        String secondNameStation = req.getParameter("secondNameStation");
        Integer secondStationCityID = Integer.valueOf(req.getParameter("secondStationCityID"));
        String secondStationCityName = req.getParameter("secondStationCityName");
        logger.trace("secondStationId - "+secondStationID);
        logger.trace("secondNameStation - "+secondNameStation);
        logger.trace("secondStationCityId - "+secondStationCityID);
        logger.trace("secondStationCityName - "+secondStationCityName);

        Timestamp firstStationDate = new Timestamp(Long.valueOf(req.getParameter("firstStationDate")));
        Timestamp secondStationDate = new Timestamp(Long.valueOf(req.getParameter("secondStationDate")));
        logger.trace("firstStationDate - "+firstStationDate);
        logger.trace("secondStationDate - "+secondStationDate);

        Train train = new Train(idTrain, numberTrain, new Route(idRoute, nameRoute,
                new Station(firstStationID, firstNameStation, new City(firstStationCityID, firstStationCityName)),
                new Station(secondStationID, secondNameStation, new City(secondStationCityID, secondStationCityName)),
                firstStationDate, secondStationDate));
        logger.trace("train - "+train);

        int price = Integer.valueOf(req.getParameter("price"));

        Wagon wagon = new Wagon(wagonId, train, typeWagon, bioTiolet, airCondition, order, countPlace, price);
        logger.trace("wagon - "+wagon);

        logger.trace("search not free places...");
        Set<Integer> places = selectDAO.getPlaces(wagon);
        logger.trace("count place - "+places.size());
        for (Integer i: places){
            logger.trace("place #"+i);
        }

        logger.trace("проверка на оставшее время до завершения покупки билета");
        if (!get5Minute(firstStationDate)){
            req.setAttribute("message",
                    "Извините, но на данный поезд покупка билетов приостановлена");
            req.getRequestDispatcher("messagepage.jsp").forward(req, resp);
        }else {
            req.setAttribute("wagon", wagon);
            req.setAttribute("listPlaces", places);
            req.getRequestDispatcher("place.jsp").forward(req, resp);
        }

    }
}
