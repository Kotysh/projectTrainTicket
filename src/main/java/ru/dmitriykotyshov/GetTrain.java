package ru.dmitriykotyshov;

import org.apache.log4j.Logger;
import ru.dmitriykotyshov.DAO.SelectDAO;
import ru.dmitriykotyshov.other.Price;
import ru.dmitriykotyshov.support.ServiceHelper;
import ru.dmitriykotyshov.trainticketobjects.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static ru.dmitriykotyshov.other.Message.no5Minute;
import static ru.dmitriykotyshov.other.MyDate.get5Minute;
import static ru.dmitriykotyshov.other.Price.getPrice;

/**
 * Created by Дмитрий on 15.01.2018.
 */
public class GetTrain extends HttpServlet {

    private final static Logger logger = Logger.getLogger(GetTrain.class);

    @Override
    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        SelectDAO selectDAO = ServiceHelper.getInstance("selectDAO");

        List<Train> trains = (List<Train>) req.getSession().getAttribute("trains");
        Train train = trains.get(Integer.valueOf(req.getParameter("selectRoute")));
        logger.trace("train - "+train);

        logger.trace("search wagons this train...");
        List<Wagon> wagons = selectDAO.getWagons(train);

        for (Wagon w: wagons){

            int priceWagon = getPrice(w.getTrain().getRoute().getPrice(),
                                        w.isAirCondition(), w.isBioTiolet(),
                                        w.getTypeWagon());
            w.setPrice(priceWagon);
            logger.trace(w);

        }

        logger.trace("get5Minute...");
        if (!get5Minute(wagons.get(0).getTrain().getRoute().getTimeDateFirstStation())){

            no5Minute(req, resp);

        }else{

            logger.trace("ALL OK!");
            req.getSession().setAttribute("wagons", wagons);
            req.getRequestDispatcher("train.jsp").forward(req, resp);

        }

    }
}
