package ru.dmitriykotyshov.DAO;

import ru.dmitriykotyshov.other.MyDate;
import ru.dmitriykotyshov.trainticketobjects.Customer;

/**
 * Created by Дмитрий on 27.01.2018.
 */
public class InsertDAO {

    private InsertDAO(){}

    private final static String INSERT_CUSTOMER = "INSERT INTO \"DIMA\".\"CUSTOMER\" (FIRST_NAME, MIDDLE_NAME, LAST_NAME, BIRTHDAY, GENDER, DOCUMENT_ID, DOC_NUMBER, EMAIL, TELEPHONE) "+
            "VALUES ('%s', '%s', '%s', TO_DATE('%s', 'YYYY-MM-DD'), %s, '%s', '%s', %s, %s)";

    public static void insertCustomer(String firstName, String middleName, String lastName, MyDate birthday,
                                      String gender, String documentID, String documentNumber, String email,
                                      String telephone){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        if (gender.length() == 0) gender = "null"; else gender = "'"+gender+"'";
        if (email.length() == 0) email = "null"; else email = "'"+email+"'";
        if (telephone.length() == 0) telephone = "null"; else telephone = "'"+telephone+"'";

        String insert = String.format(INSERT_CUSTOMER, firstName, middleName, lastName,
                birthday.toString(), gender, documentID, documentNumber, email, telephone);

        System.out.println(insert);

        connectionDAO.operatorDML(insert);
        connectionDAO.disconnect();

    }

    private final static String INSERT_TICKET = "INSERT INTO \"DIMA\".\"TICKET\" (CUSTOMER_ID, WAGON_ID, PLACE, DATE_BUY, FIRST_ROUTE_STATION_ID, SECOND_ROUTE_STATION_ID)\n" +
            "VALUES ('%s', '%s', '%s', TO_DATE('%s', 'YYYY-MM-DD'), '%s', '%s')";

    public static void insertTicket(int customerID, int wagonID, String place, String dateBuy,
                                    int fistrRouteStationID, int secondRouteStationID){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        String insert = String.format(INSERT_TICKET, customerID, wagonID, place, dateBuy, fistrRouteStationID, secondRouteStationID);
        System.out.println(insert);

        connectionDAO.operatorDML(insert);
        connectionDAO.disconnect();

    }

}