package ru.dmitriykotyshov;

import ru.dmitriykotyshov.mail.Message;
import ru.dmitriykotyshov.other.MyDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
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

        String document = req.getParameter("document");
        String gender = req.getParameter("gender");
        String docNumber = req.getParameter("docNumber");
        String email = req.getParameter("email");
        String telephone = req.getParameter("telephone");

        String route = req.getParameter("route");
        String numberTrain = req.getParameter("numberTrain");
        String orderWagon = req.getParameter("orderWagon");
        String firstRouteStation = req.getParameter("firstRouteStation");
        String secondRouteStation = req.getParameter("secondRouteStation");
        String place = req.getParameter("place");


        MyDate birthday = new MyDate(year, month, day);


        int customerId = getCustomerId(docNumber, document);
        if (customerId == 0) {
            insertCustomer(firstName, middleName, lastName, birthday, gender, document, docNumber, email, telephone);
            customerId = getCustomerId(docNumber, document);
        }

        String date = new SimpleDateFormat("YYYY-MM-DD").format(new Date());

        int firstRouteStationId = getRouteStationId(route, firstRouteStation, 1);
        int secondRouteStationId = getRouteStationId(route, secondRouteStation, 2);
        int wagonId = getWagonId(numberTrain, orderWagon);

        insertTicket(customerId, wagonId, place, date, firstRouteStationId, secondRouteStationId);

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
        System.out.println("------------------------------------------------------------------------");
        System.out.println(customerId);
        System.out.println(firstRouteStationId);
        System.out.println(secondRouteStationId);
        System.out.println(wagonId);
        //-------------------------------------------------------------------------------------------------------------



        String text = "Здравствуйте, "+firstName+" "+middleName+", "+
                "Вы приобрели билет на нашем сайте, теперь вам его нужно оплатить по указанным реквезитам.";

        Message message = new Message(email, "Train&Ticket", text);
        message.sendMessage();

        req.setAttribute("firstName", firstName);
        req.setAttribute("middleName", middleName);
        req.getRequestDispatcher("buy.jsp").forward(req, resp);

    }
}
