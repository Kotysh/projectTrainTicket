package ru.dmitriykotyshov;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.dmitriykotyshov.DAO.SelectDAO;
import ru.dmitriykotyshov.other.Price;
import ru.dmitriykotyshov.trainticketobjects.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Дмитрий on 15.01.2018.
 */
public class GetTrain extends HttpServlet {

    private final static Logger logger = Logger.getLogger(GetTrain.class);

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("springContext.xml");

        SelectDAO selectDAO = applicationContext.getBean("SelectDAO", SelectDAO.class);



        logger.trace("get info about train...");
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

        int distance = Integer.valueOf(req.getParameter("distance"));
        int price = Integer.valueOf(req.getParameter("price"));
        logger.trace("distance - "+distance);
        logger.trace("price - "+price);


        Train train = new Train(idTrain, numberTrain, new Route(idRoute, nameRoute,
                new Station(firstStationID, firstNameStation, new City(firstStationCityID, firstStationCityName)),
                new Station(secondStationID, secondNameStation, new City(secondStationCityID, secondStationCityName)),
                firstStationDate, secondStationDate));
        train.getRoute().setDistance(distance);
        train.getRoute().setPrice(price);
        logger.trace("train - "+train);

        logger.trace("search wagons this train...");
        List<Wagon> wagons = selectDAO.getWagons(train);

        for (Wagon w: wagons){


            int priceWagon = (int)(w.getTrain().getRoute().getPrice()*
                    (w.isAirCondition()? Price.AIR_CONDITION:1)*
                    (w.isBioTiolet()?Price.BIO_TIOLET:1));
            w.setPrice(priceWagon);
            logger.trace(w);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(wagons);
        logger.trace("send json - "+str);

        req.setAttribute("json", str);
        req.getRequestDispatcher("train.jsp").forward(req, resp);

    }
}
