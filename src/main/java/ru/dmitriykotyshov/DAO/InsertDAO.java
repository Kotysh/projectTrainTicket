package ru.dmitriykotyshov.DAO;

import ru.dmitriykotyshov.other.MyDate;

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

    public void insertCustomer(String firstName, String middleName, String lastName, MyDate birthday,
                                      String gender, String documentId, String documentNumber, String email,
                                      String telephone){

        this.insert(getSqlInsertCustomer(firstName, middleName, lastName, birthday, gender, documentId, documentNumber,
                email, telephone));

    }

    public void insertTicket(int customerId, int wagonId, String place, String dateBuy,
                                    int fistrRouteStationId, int secondRouteStationId, int price){

        this.insert(getSqlInsertTicket(customerId, wagonId, place, dateBuy, fistrRouteStationId, secondRouteStationId, price));

    }

    public void insertDocument(String document){

        this.insert(getSqlInsertDocument(document));

    }

    public void insertCity(String city){

        this.insert(getSqlInsertCity(city));

    }

    public void insertRoute(String route){

        this.insert(getSqlInsertRoute(route));

    }

    public void insertRouteStation(String routeId, String  stationId, String orderStation,
                                   int arrivalYear, int arrivalMonth, int arrivalDay, int arrivalHour, int arrivalMinute,
                                   int departureYear, int departureMonth, int departureDay, int departureHour, int departureMinute, int distance){

        this.insert(getSqlInsertRouteStation(routeId, stationId, orderStation, arrivalYear, arrivalMonth, arrivalDay, arrivalHour, arrivalMinute,
                departureYear, departureMonth, departureDay, departureHour, departureMinute, distance));

    }

    public void insertStation(String station, String cityId){

        this.insert(getSqlInsertStation(station, cityId));

    }

    public void insertTrain(String numberTrain, String routeId, String express){

        this.insert(getSqlInsertTrain(numberTrain, routeId, express));

    }

    public void insertTypeWagon(String typeWagon, String bioTiolet, String airCondition, String countPlace){

        this.insert(getSqlInsertTypeWagon(typeWagon, bioTiolet, airCondition, countPlace));

    }

    public void insertWagon(String trainID, String typeWagonID, String orderWagon){

        this.insert(getSqlInsertWagon(trainID, typeWagonID, orderWagon));

    }


}