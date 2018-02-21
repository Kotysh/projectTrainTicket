package ru.dmitriykotyshov;

import org.apache.log4j.Logger;
import ru.dmitriykotyshov.DAO.SelectDAO;
import ru.dmitriykotyshov.support.ServiceHelper;
import ru.dmitriykotyshov.trainticketobjects.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import static ru.dmitriykotyshov.other.Message.no5Minute;
import static ru.dmitriykotyshov.other.MyDate.get5Minute;


/**
 * Created by Дмитрий on 22.01.2018.
 */
public class GetPlace extends HttpServlet {

    private final static Logger logger = Logger.getLogger(GetPlace.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        SelectDAO selectDAO = ServiceHelper.getInstance("selectDAO");


        List<Wagon> wagons = (List<Wagon>) req.getSession().getAttribute("wagons");

        List<Document> documents = selectDAO.getDocuments();

        Wagon wagon = wagons.get(Integer.valueOf(req.getParameter("selectWagon")));
        logger.trace("wagon - "+wagon);

        logger.trace("search not free places...");
        Set<Integer> places = selectDAO.getPlaces(wagon);

        logger.trace("count place - "+places.size());
        for (Integer i: places){
            logger.trace("place #"+i);
        }

        logger.trace("get5Minute");
        if (!get5Minute(wagon.getTrain().getRoute().getTimeDateSecondStation())){

            no5Minute(req, resp);

        }else {

            logger.trace("ALL OK!");
            req.getSession().setAttribute("wagon", wagon);
            req.getSession().setAttribute("places", places);
            req.getSession().setAttribute("documents", documents);
            req.getRequestDispatcher("place.jsp").forward(req, resp);

        }

    }
}
