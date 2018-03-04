package ru.dmitriykotyshov;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import ru.dmitriykotyshov.DAO.InsertDAO;
import ru.dmitriykotyshov.DAO.SelectDAO;
import ru.dmitriykotyshov.mail.Message;
import ru.dmitriykotyshov.support.ServiceHelper;
import ru.dmitriykotyshov.trainticketobjects.Route;
import ru.dmitriykotyshov.trainticketobjects.Wagon;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import static ru.dmitriykotyshov.other.Message.no5Minute;
import static ru.dmitriykotyshov.other.Message.placeBought;
import static ru.dmitriykotyshov.other.MyDate.get5Minute;

/**
 * Created by Дмитрий on 26.01.2018.
 */
@EnableTransactionManagement
public class Buy extends HttpServlet {

    private final static Logger logger = Logger.getLogger(Buy.class);

    private final static String textMessage = "Здравствуйте, %s %s "+
            "Вы приобрели билет на нашем сайте, теперь вам его нужно оплатить по указанным реквезитам.";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        SelectDAO selectDAO = ServiceHelper.getInstance("selectDAO");
        InsertDAO insertDAO = ServiceHelper.getInstance("insertDAO");

        logger.trace("buy ticket...");
        String firstName = req.getParameter("firstName");
        String middleName = req.getParameter("middleName");
        String lastName = req.getParameter("lastName");
        logger.trace("firstName - "+firstName +
                    "\n middleName - "+middleName+
                    "\n lastName - "+lastName);

        String birthdayCustomer = req.getParameter("birthday");
        Date birthday = null;
        try {
            birthday = new SimpleDateFormat("yyyy.MM.dd").parse(birthdayCustomer);
        } catch (ParseException e) {
            logger.debug("error birthday - ", e);
        }
        logger.trace("birthday - "+birthday);

        String document = req.getParameter("document");
        String docNumber = req.getParameter("docNumber");
        String gender = req.getParameter("gender");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");
        logger.trace("document - "+document+
                    "\n docNumber - "+docNumber+
                    "\n gender - "+gender+
                    "\n email - "+email+
                    "\n telephone - "+telephone);
        Integer place = Integer.valueOf(req.getParameter("place"));
        logger.trace("select place - "+place);


        Wagon wagon = (Wagon) req.getSession().getAttribute("wagon");
        Route route = wagon.getTrain().getRoute();

        logger.trace("get5Minute...");
        if (!get5Minute(wagon.getTrain().getRoute().getTimeDateFirstStation())){

            no5Minute(req, resp);

        }else {

            logger.trace("get customerId...");
            int customerId = selectDAO.getCustomerId(docNumber, document);
            if (customerId == 0) {
                logger.trace("customerId = 0, (new)insertCustomer");
                insertDAO.insertCustomer(firstName, middleName, lastName, birthday, gender, document, docNumber, email, telephone);
                customerId = selectDAO.getCustomerId(docNumber, document);
            }
            logger.trace("customerId = " + customerId);


            String date = new SimpleDateFormat("YYYY/MM/dd").format(new Date());
            logger.trace("todays date - " + date);


            int firstRouteStationId = selectDAO.getRouteStationId(route.getNameRoute(), route.getTimeDateFirstStation().toString(), 1);
            logger.trace("firstRouteStationId - " + firstRouteStationId);
            int secondRouteStationId = selectDAO.getRouteStationId(route.getNameRoute(), route.getTimeDateSecondStation().toString(), 2);
            logger.trace("secondRouteStationId - " + secondRouteStationId);
            int wagonId = selectDAO.getWagonId(wagon.getTrain().getNumberTrain(), wagon.getOrder());
            logger.trace("wagonId - " + wagonId);


            logger.trace("insert ticket");
            logger.trace("place check...");

            method(place, insertDAO, selectDAO, route, customerId, wagonId, date,
                    firstRouteStationId, secondRouteStationId,
                    wagon, firstName, middleName,
                    email, req, resp);
        }
    }

    @Transactional
    public void method(int place,
                       InsertDAO insertDAO,
                       SelectDAO selectDAO,
                       Route route,
                       int customerId, int wagonId, String date,
                       int firstRouteStationId, int secondRouteStationId,
                       Wagon wagon, String firstName, String middleName,
                       String email,
                       HttpServletRequest req, HttpServletResponse resp){

        Set<Integer> places = selectDAO.getPlaces(new Wagon(wagonId, route.getTimeDateFirstStation(), route.getTimeDateSecondStation()));
        if (places.contains(place)) {

            logger.trace("Place "+place+" check");
            placeBought(req, resp);

        }else {

            insertDAO.insertTicket(customerId, wagonId, String.valueOf(place), date, firstRouteStationId, secondRouteStationId, wagon.getPrice());

            String text = String.format(textMessage, firstName, middleName);
            logger.trace("text message - " + text);

            Message message = new Message(email, "Train&Ticket", text);
            message.sendMessage();

            logger.trace("ALL OK!");
            req.setAttribute("firstName", firstName);
            req.setAttribute("middleName", middleName);
            try {
                req.getRequestDispatcher("buy.jsp").forward(req, resp);
            } catch (ServletException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}