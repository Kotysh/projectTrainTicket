package ru.dmitriykotyshov.DAO.sql;

/**
 * Created by Дмитрий on 02.02.2018.
 */
public class DeleteSQL {

    private final static String SQL_DELETE_CITY = "delete from city where city_id = %s";

    private final static String SQL_DELETE_CUSTOMER = "delete from customer where customer_id = %s";

    private final static String SQL_DELETE_DOCUMENT = "delete from document where document_id = %s";

    private final static String SQL_DELETE_ROUTE = "delete from route where route_id = %s";

    private final static String SQL_DELETE_ROUTE_STATION = "delete from route_station where route_station_id = %s";

    private final static String SQL_DELETE_STATION = "delete from station where station_id = %s";

    private final static String SQL_DELETE_TICKET = "delete from ticket where ticket_id = %s";

    private final static String SQL_DELETE_TRAIN = "delete from train where train_id = %s";

    private final static String SQL_DELETE_TYPE_WAGON = "delete from type_wagon where type_wagon_id = %s";

    private final static String SQL_DELETE_WAGON = "delete from wagon where wagon_id = %s";



    private DeleteSQL(){}



    public static String sqlDeleteCity(String cityId){

        return String.format(SQL_DELETE_CITY, cityId);

    }


    public static String sqlDeleteCustomer(String customerId){

        return String.format(SQL_DELETE_CUSTOMER, customerId);

    }


    public static String sqlDeleteDocument(String documentId){

        return String.format(SQL_DELETE_DOCUMENT, documentId);

    }


    public static String sqlDeleteRoute(String routeId){

        return String.format(SQL_DELETE_ROUTE, routeId);

    }


    public static String sqlDeleteRouteStation(String routeStationId){

        return String.format(SQL_DELETE_ROUTE_STATION, routeStationId);

    }


    public static String sqlDeleteStation(String stationId){

        return String.format(SQL_DELETE_STATION, stationId);

    }


    public static String sqlDeleteTicket(String ticketId){

        return String.format(SQL_DELETE_TICKET, ticketId);

    }


    public static String sqlDeleteTrain(String trainId){

        return String.format(SQL_DELETE_TRAIN, trainId);

    }


    public static String sqlDeleteTypeWagon(String typeWagonId){

        return String.format(SQL_DELETE_TYPE_WAGON, typeWagonId);

    }


    public static String sqlDeleteWagon(String wagonId){

        return String.format(SQL_DELETE_WAGON, wagonId);

    }

}
