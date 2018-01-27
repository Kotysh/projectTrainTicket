package ru.dmitriykotyshov;

import ru.dmitriykotyshov.other.MyDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import static ru.dmitriykotyshov.DAO.InsertDAO.insertCustomer;
import static ru.dmitriykotyshov.DAO.InsertDAO.insertTicket;
import static ru.dmitriykotyshov.DAO.SelectDAO.getCustomerId;
import static ru.dmitriykotyshov.DAO.SelectDAO.getRouteStationId;
import static ru.dmitriykotyshov.DAO.SelectDAO.getWagonId;

/**
 * Created by Дмитрий on 26.01.2018.
 */
public class Buy extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String firstName = req.getParameter("firstName");
        String middleName = req.getParameter("middleName");
        String lastName = req.getParameter("lastName");

        Integer year = Integer.valueOf(req.getParameter("year"));
        Integer month = Integer.valueOf(req.getParameter("month"));
        Integer day = Integer.valueOf(req.getParameter("day"));

        MyDate birthday = new MyDate(year, month, day);

        String document = req.getParameter("document");
        String gender = req.getParameter("gender");
        String docNumber = req.getParameter("docNumber");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");

        insertCustomer(firstName, middleName, lastName, birthday, gender, document, docNumber, email, telephone);
        int customerId = getCustomerId(docNumber, document);


        String route = req.getParameter("route");
        String numberTrain = req.getParameter("numberTrain");
        String orderWagon = req.getParameter("orderWagon");
        String firstRouteStation = req.getParameter("firstRouteStation");
        String secondRouteStation = req.getParameter("secondRouteStation");
        String place = req.getParameter("place");

        //-------------------------------------------------------------------------------------------------------------
        System.out.println(firstName);
        System.out.println(middleName);
        System.out.println(lastName);
        System.out.println(birthday);
        System.out.println(document);
        System.out.println(docNumber);
        System.out.println(gender);
        System.out.println(email);
        System.out.println(telephone);
        System.out.println("------------------------------------------------------------------------");
        System.out.println(route);
        System.out.println(numberTrain);
        System.out.println(orderWagon);
        System.out.println(firstRouteStation);
        System.out.println(secondRouteStation);
        System.out.println(place);

        int firstRouteStationId = getRouteStationId(route, firstRouteStation, 1);
        int secondRouteStationId = getRouteStationId(route, secondRouteStation, 2);
        int wagonId = getWagonId(numberTrain, orderWagon);

        System.out.println("------------------------------------------------------------------------");
        System.out.println(customerId);
        System.out.println(firstRouteStationId);
        System.out.println(secondRouteStationId);
        System.out.println(wagonId);
        String date = new SimpleDateFormat("YYYY-MM-DD").format(new Date());

        insertTicket(customerId, wagonId, place, date, firstRouteStationId, secondRouteStationId);



    }
}
