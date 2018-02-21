package ru.dmitriykotyshov.DAO;

import ru.dmitriykotyshov.other.MyDate;

import java.util.Date;

import static ru.dmitriykotyshov.DAO.sql.InsertSQL.*;

/**
 * Created by Дмитрий on 27.01.2018.
 */
public class InsertDAO {

    private static InsertDAO instanceInsertDAO;

    public static synchronized InsertDAO getInstanceInsertDAO(){

        if (instanceInsertDAO == null){
            instanceInsertDAO = new InsertDAO();
        }
        return instanceInsertDAO;

    }

    private InsertDAO(){}

    private void insert(String sqlInsert){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        connectionDAO.operatorDML(sqlInsert);

        connectionDAO.disconnect();

    }

    public void insertCustomer(String firstName, String middleName, String lastName, Date birthday,
                                      String gender, String documentId, String documentNumber, String email,
                                      String telephone){

        this.insert(sqlInsertCustomer(firstName, middleName, lastName, birthday, gender, documentId, documentNumber,
                email, telephone));

    }

    public void insertTicket(int customerId, int wagonId, String place, String dateBuy,
                                    int fistrRouteStationId, int secondRouteStationId, int price){

        this.insert(sqlInsertTicket(customerId, wagonId, place, dateBuy, fistrRouteStationId, secondRouteStationId, price));

    }

    public void insertDocument(String document){

        this.insert(sqlInsertDocument(document));

    }

    public void insertCity(String city){

        this.insert(InsertCity(city));

    }

    public void insertRoute(String route){

        this.insert(sqlInsertRoute(route));

    }

    public void insertRouteStation(String routeId, String  stationId, String orderStation,
                                   int arrivalYear, int arrivalMonth, int arrivalDay, int arrivalHour, int arrivalMinute,
                                   int departureYear, int departureMonth, int departureDay, int departureHour, int departureMinute, int distance){

        this.insert(sqlInsertRouteStation(routeId, stationId, orderStation, arrivalYear, arrivalMonth, arrivalDay, arrivalHour, arrivalMinute,
                departureYear, departureMonth, departureDay, departureHour, departureMinute, distance));

    }

    public void insertStation(String station, String cityId){

        this.insert(sqlInsertStation(station, cityId));

    }

    public void insertTrain(String numberTrain, String routeId, String express){

        this.insert(sqlInsertTrain(numberTrain, routeId, express));

    }

    public void insertTypeWagon(String typeWagon, String bioTiolet, String airCondition, String countPlace){

        this.insert(sqlInsertTypeWagon(typeWagon, bioTiolet, airCondition, countPlace));

    }

    public void insertWagon(String trainID, String typeWagonID, String orderWagon){

        this.insert(sqlInsertWagon(trainID, typeWagonID, orderWagon));

    }


}