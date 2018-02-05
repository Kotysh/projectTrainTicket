package ru.dmitriykotyshov.DAO.sql;

/**
 * Created by Дмитрий on 02.02.2018.
 */
public class DeleteSQL {

    private DeleteSQL(){}



    private final static String SQL_DELETE_CITY = "delete from city where city_id = %s";

    public static String getSqlDeleteCity(String cityId){

        return String.format(SQL_DELETE_CITY, cityId);

    }



    private final static String SQL_DELETE_CUSTOMER = "delete from customer where customer_id = %s";

    public static String getSqlDeleteCustomer(String customerId){

        return String.format(SQL_DELETE_CUSTOMER, customerId);

    }



    private final static String SQL_DELETE_DOCUMENT = "delete from document where document_id = %s";

    public static String getSqlDeleteDocument(String documentId){

        return String.format(SQL_DELETE_DOCUMENT, documentId);

    }



    private final static String SQL_DELETE_ROUTE = "delete from route where route_id = %s";

    public static String getSqlDeleteRoute(String routeId){

        return String.format(SQL_DELETE_ROUTE, routeId);

    }



    private final static String SQL_DELETE_ROUTE_STATION = "delete from route_station where route_station_id = %s";

    public static String getSqlDeleteRouteStation(String routeStationId){

        return String.format(SQL_DELETE_ROUTE_STATION, routeStationId);

    }



    private final static String SQL_DELETE_STATION = "delete from station where station_id = %s";

    public static String getSqlDeleteStation(String stationId){

        return String.format(SQL_DELETE_STATION, stationId);

    }



    private final static String SQL_DELETE_TICKET = "delete from ticket where ticket_id = %s";

    public static String getSqlDeleteTicket(String ticketId){

        return String.format(SQL_DELETE_TICKET, ticketId);

    }



    private final static String SQL_DELETE_TRAIN = "delete from train where train_id = %s";

    public static String getSqlDeleteTrain(String trainId){

        return String.format(SQL_DELETE_TRAIN, trainId);

    }



    private final static String SQL_DELETE_TYPE_WAGON = "delete from type_wagon where type_wagon_id = %s";

    public static String getSqlDeleteTypeWagon(String typeWagonId){

        return String.format(SQL_DELETE_TYPE_WAGON, typeWagonId);

    }



    private final static String SQL_DELETE_WAGON = "delete from wagon where wagon_id = %s";

    public static String getSqlDeleteWagon(String wagonId){

        return String.format(SQL_DELETE_WAGON, wagonId);

    }

}
