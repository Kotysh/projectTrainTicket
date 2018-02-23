package ru.dmitriykotyshov.DAO.sql;

import ru.dmitriykotyshov.other.MyDate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

import static ru.dmitriykotyshov.other.MyDate.dateFormat;

/**
 * Created by Дмитрий on 01.02.2018.
 */
public class InsertSQL {

    private final static String SQL_INSERT_CITY = "insert into city (city) values ('%s')";

    private final static String SQL_INSERT_CUSTOMER = "INSERT INTO CUSTOMER (FIRST_NAME, MIDDLE_NAME, LAST_NAME, BIRTHDAY, GENDER, DOCUMENT_ID, DOC_NUMBER, EMAIL, TELEPHONE) "+
            "VALUES ('%s', '%s', '%s', TO_DATE('%s', 'dd/MM/yyyy'), '%s', '%s', '%s', '%s', '%s')";

    private final static String SQL_INSERT_DOCUMENT = "insert into document (document) values ('%s')";

    private final static String SQL_INSERT_ROUTE = "insert into route (route) values ('%s')";

    private final static String SQL_INSERT_ROUTE_STATION = "INSERT INTO ROUTE_STATION (ROUTE_ID, STATION_ID, ORDER_STATION, ARRIVAL_TIME, DEPARTURE_TIME, DISTANCE) "+
            "VALUES ('%s', '%s', '%s', TO_TIMESTAMP('%s', 'YYYY-MM-DD HH24:MI:SS.FF'), TO_TIMESTAMP('%s', 'YYYY-MM-DD HH24:MI:SS.FF'), %d)";

    private final static String SQL_INSERT_TICKET = "INSERT INTO TICKET (CUSTOMER_ID, WAGON_ID, PLACE, DATE_BUY, FIRST_ROUTE_STATION_ID, SECOND_ROUTE_STATION_ID, PRICE)\n" +
            "VALUES ('%s', '%s', '%s', TO_DATE('%s', 'YYYY/MM/DD'), '%s', '%s', %d)";

    private final static String SQL_INSERT_STATION = "insert into station (station, city_id) values ('%s', %s)";

    private final static String SQL_INSERT_TRAIN = "INSERT INTO TRAIN (NUMBER_TRAIN, ROUTE_ID, EXPRESS) VALUES ('%s', '%s', %s)";

    private final static String SQL_INSERT_TYPE_WAGON = "INSERT INTO TYPE_WAGON (TYPE_NAME, BIO_TIOLET, AIR_CONDITION, COUNT_PLACE) " +
            "VALUES ('%s', %s, %s, '%s')";

    private final static String SQL_INSERT_WAGON = "INSERT INTO WAGON (TRAIN_ID, TYPE_WAGON_ID, ORDER_WAGON)" +
            "VALUES ('%s', '%s', '%s')";



    private InsertSQL(){}



    public static String InsertCity(String city){

        return String.format(SQL_INSERT_CITY, city);

    }


    public static String sqlInsertCustomer(String first_name, String middle_name, String last_name,
                                           Date birthday, String gender, String documentID, String documentNumber,
                                           String email, String telephone){
/*
        if (gender.length() == 0) gender = "null"; else gender = "'"+gender+"'";
        if (email.length() == 0) email = "null"; else email = "'"+email+"'";
        if (telephone.length() == 0) telephone = "null"; else telephone = "'"+telephone+"'";*/

        return String.format(SQL_INSERT_CUSTOMER, first_name, middle_name, last_name,
                dateFormat(birthday), gender, documentID, documentNumber, email, telephone);

    }



    public static String sqlInsertDocument(String document){

        return String.format(SQL_INSERT_DOCUMENT, document);

    }



    public static String sqlInsertRoute(String route){

        return String.format(SQL_INSERT_ROUTE, route);

    }



    public static String sqlInsertRouteStation(String routeId, String  stationId, String orderStation,
                                                  int arrivalYear, int arrivalMonth, int arrivalDay, int arrivalHour, int arrivalMinute,
                                                  int departureYear, int departureMonth, int departureDay, int departureHour, int departureMinute, int distance){

        return String.format(SQL_INSERT_ROUTE_STATION, routeId, stationId, orderStation,
                new Timestamp(new GregorianCalendar(arrivalYear, arrivalMonth, arrivalDay, arrivalHour, arrivalMinute).getTimeInMillis()),
                new Timestamp(new GregorianCalendar(departureYear, departureMonth, departureDay, departureHour, departureMinute).getTimeInMillis()),
                distance);

    }



    public static String sqlInsertStation(String station, String cityId){

        if (cityId.length() == 0) cityId = "null"; else cityId = "'"+cityId+"'";

        return String.format(SQL_INSERT_STATION, station, cityId);

    }



    public static String sqlInsertTicket(int customerId, int wagonId, String place,
                                            String datebuy, int fistrRouteStationID, int secondRouteStationID, int price){

        return String.format(SQL_INSERT_TICKET, customerId, wagonId, place, datebuy, fistrRouteStationID, secondRouteStationID, price);

    }



    public static String sqlInsertTrain (String numberTrain, String routeId, String express){

        if (express.length() == 0) express = "null"; else express = "'"+express+"'";

        return String.format(SQL_INSERT_TRAIN, numberTrain, routeId, express);

    }



    public static String sqlInsertTypeWagon(String typeWagon, String bioTiolet, String airCondition, String countPlace){

        if (bioTiolet.length() == 0) bioTiolet = "null"; else bioTiolet = "'"+bioTiolet+"'";
        if (airCondition.length() == 0) airCondition = "null"; else airCondition = "'"+airCondition+"'";

        return String.format(SQL_INSERT_TYPE_WAGON, typeWagon, bioTiolet, airCondition, countPlace);

    }



    public static String sqlInsertWagon(String trainID, String typeWagonID, String orderWagon){

        return String.format(SQL_INSERT_WAGON, trainID, typeWagonID, orderWagon);

    }

}
