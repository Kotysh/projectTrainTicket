package ru.dmitriykotyshov.DAO.sql;

import org.apache.log4j.Logger;
import ru.dmitriykotyshov.DAO.SelectDAO;
import ru.dmitriykotyshov.other.MyDate;
import ru.dmitriykotyshov.trainticketobjects.City;
import ru.dmitriykotyshov.trainticketobjects.Wagon;

/**
 * Created by Дмитрий on 01.02.2018.
 */
public class SelectSQL {


    private final static Logger logger = Logger.getLogger(SelectDAO.class);

    private SelectSQL() {}



    private static final String SQL_GET_CITIES = "select * from city";

    public static String getSqlGetCities(){

        return SQL_GET_CITIES;

    }



    private static final String SQL_GET_CITY_BY_NAME_CITY = "select * from city where city = '%s'";

    public static String getSqlGetCityByNameCity(String nameCity) {

        String sql = String.format(SQL_GET_CITY_BY_NAME_CITY, nameCity);
        logger.trace(sql);
        return sql;

    }



    private static final String SQL_GET_CITY_BY_CITY_ID = "select * from city where city_id = %d";

    public static String getSqlGetCityByCityId(int cityId) {

        String sql = String.format(SQL_GET_CITY_BY_CITY_ID, cityId);
        logger.trace(sql);
        return sql;

    }



    private static final String SQL_GET_STATIONS_BY_NAME_STATION_AND_CITY_ID = "select * from station where station = '%s' or city_id = %d";

    public static String getSqlGetStationsByNameStationAndCityId(String nameStation, City city) {

        String sql = String.format(SQL_GET_STATIONS_BY_NAME_STATION_AND_CITY_ID, nameStation, city.getId());
        logger.trace(sql);
        return sql;

    }



    private static final String SQL_GET_COUNT_ROUTES = "select route_station.route_id, route.route, count(route_station.station_id) " +
            "from route_station " +
            "join route on route.route_id = route_station.route_id " +
            "where route_station.station_id in (%d,%d) " +
            "group by route_station.route_id, route.route " +
            "having count(route_station.station_id)>=2";

    public static String getSqlGetCountRoutes(int firstStationId, int secondStationId) {

        String sql = String.format(SQL_GET_COUNT_ROUTES, firstStationId, secondStationId);
        logger.trace(sql);
        return sql;

    }



    private static final String SQL_GET_TRAIN_BY_ROUTE_ID = "select * from train where route_id = %d";

    public static String getSqlGetTrainByRouteId(int routeId) {

        String sql = String.format(SQL_GET_TRAIN_BY_ROUTE_ID, routeId);
        logger.trace(sql);
        return sql;

    }



    private static final String SQL_GET_WAGONS_BY_TRAIN_ID = "select wagon.wagon_id, wagon.order_wagon, type_wagon.type_name, type_wagon.bio_tiolet, type_wagon.air_condition, type_wagon.count_place " +
            "from wagon " +
            "join type_wagon on wagon.type_wagon_id = type_wagon.type_wagon_id " +
            "where wagon.train_id = %d " +
            "order by order_wagon";

    public static String getSqlGetWagonsByTrainId(int trainId) {

        String sql = String.format(SQL_GET_WAGONS_BY_TRAIN_ID, trainId);
        logger.trace(sql);
        return sql;

    }



    private static final String SQL_GET_BUY_PLACES_FOR_WAGON = "select ticket.place from ticket " +
            "join route_station routeone on ticket.FIRST_ROUTE_STATION_ID = routeone.ROUTE_STATION_ID " +
            "join route_station routetwo on ticket.SECOND_ROUTE_STATION_ID = routetwo.ROUTE_STATION_ID " +
            "where ticket.wagon_id = %d " +
            "and " +
            "((routeone.departure_time<=to_timestamp('%s', 'YYYY-MM-DD HH24:MI:SS.FF') and to_timestamp('%s', 'YYYY-MM-DD HH24:MI:SS.FF')<=routetwo.arrival_time) " +
            "or " +
            "(routeone.departure_time<=to_timestamp('%s', 'YYYY-MM-DD HH24:MI:SS.FF') and to_timestamp('%s', 'YYYY-MM-DD HH24:MI:SS.FF')<=routetwo.arrival_time) " +
            "or " +
            "(to_timestamp('%s', 'YYYY-MM-DD HH24:MI:SS.FF')<=routeone.departure_time and routeone.departure_time<=to_timestamp('%s', 'YYYY-MM-DD HH24:MI:SS.FF')) " +
            "or " +
            "(to_timestamp('%s', 'YYYY-MM-DD HH24:MI:SS.FF')<=routetwo.arrival_time and routetwo.arrival_time<=to_timestamp('%s', 'YYYY-MM-DD HH24:MI:SS.FF')))";

    public static String getSqlGetBuyPlacesForWagon(Wagon wagon) {

        String sql = String.format(SQL_GET_BUY_PLACES_FOR_WAGON, wagon.getWagonId(),
                wagon.getTrain().getRoute().getTimeDateFirstStation(), wagon.getTrain().getRoute().getTimeDateFirstStation(),
                wagon.getTrain().getRoute().getTimeDateSecondStation(), wagon.getTrain().getRoute().getTimeDateSecondStation(),
                wagon.getTrain().getRoute().getTimeDateFirstStation(), wagon.getTrain().getRoute().getTimeDateSecondStation(),
                wagon.getTrain().getRoute().getTimeDateFirstStation(), wagon.getTrain().getRoute().getTimeDateSecondStation());
        logger.trace(sql);
        return sql;

    }



    private static final String SQL_GET_NUMBER_ROUTE_BY_FIRST_STATION_ID_AND_SECOND_STATION_ID = "select route_id, count(station_id) " +
            "from route_station " +
            "where station_id in (%d,%d) " +
            "group by route_id " +
            "having count(station_id)=2";

    public static String getSqlGetNumberRouteByFirstStationIdAndSecondStationId(int firstStationdId, int secondStationId) {

        String sql = String.format(SQL_GET_NUMBER_ROUTE_BY_FIRST_STATION_ID_AND_SECOND_STATION_ID, firstStationdId, secondStationId);
        logger.trace(sql);
        return sql;

    }



    private static final String SQL_GET_ORDER_STATION_ON_ROUTE_OUT = "select order_station " +
            "from route_station " +
            "where route_id = %d and station_id = %d " +
            "and DEPARTURE_TIME>TO_TIMESTAMP('%d/%d/%d', 'YYYY/MM/DD') " +
            "and DEPARTURE_TIME<TO_TIMESTAMP('%d/%d/%d', 'YYYY/MM/DD')";

    public static String getSqlGetOrderStationOnRouteOut(int route, int i, MyDate date) {

        String sql = String.format(SQL_GET_ORDER_STATION_ON_ROUTE_OUT, route, i, date.getYear(), date.getMonth(), date.getDay(),
                date.getYear(), date.getMonth(), date.getDay() + 1);
        logger.trace(sql);
        return sql;

    }



    private static final String SQL_GET_ORDER_STATION_ON_ROUTE_IN = "select order_station " +
            "from route_station " +
            "where route_id = %d and station_id = %d " +
            "and ARRIVAL_TIME>TO_TIMESTAMP('%d/%d/%d', 'YYYY/MM/DD') " +
            "and ARRIVAL_TIME<TO_TIMESTAMP('%d/%d/%d', 'YYYY/MM/DD')";

    public static String getSqlGetOrderStationOnRouteIn(int route, int i, MyDate date) {

        String sql = String.format(SQL_GET_ORDER_STATION_ON_ROUTE_IN, route, i, date.getYear(), date.getMonth(), date.getDay(),
                date.getYear(), date.getMonth(), date.getDay() + 1);
        logger.trace(sql);
        return sql;

    }



    private static final String SQL_GET_TIMESTAMP_STATION_OUT = "select DEPARTURE_TIME, DISTANCE " +
            "from route_station " +
            "where route_id = %d and station_id = %d " +
            "and DEPARTURE_TIME>TO_TIMESTAMP('%d/%d/%d', 'YYYY/MM/DD') " +
            "and DEPARTURE_TIME<TO_TIMESTAMP('%d/%d/%d', 'YYYY/MM/DD')";

    public static String getSqlGetTimestampStationOut(int routeId, int stationId, MyDate date) {

        String sql = String.format(SQL_GET_TIMESTAMP_STATION_OUT, routeId, stationId, date.getYear(), date.getMonth(), date.getDay(),
                date.getYear(), date.getMonth(), date.getDay() + 1);
        logger.trace(sql);
        return sql;

    }



    private static final String SQL_GET_TIMESTAMP_STATION_IN = "select ARRIVAL_TIME, DISTANCE " +
            "from route_station " +
            "where route_id = %d and station_id = %d " +
            "and ARRIVAL_TIME>TO_TIMESTAMP('%d/%d/%d', 'YYYY/MM/DD') " +
            "and ARRIVAL_TIME<TO_TIMESTAMP('%d/%d/%d', 'YYYY/MM/DD')";

    public static String getSqlGetTimestampStationIn(int routeId, int stationId, MyDate date) {

        String sql = String.format(SQL_GET_TIMESTAMP_STATION_IN, routeId, stationId, date.getYear(), date.getMonth(), date.getDay(),
                date.getYear(), date.getMonth(), date.getDay() + 1);
        logger.trace(sql);
        return sql;

    }



    private static final String SQL_GET_NAME_ROUTE = "select route from route where route_id = %d";

    public static String getSqlGetNameRoute(int routeId){

        String sql = String.format(SQL_GET_NAME_ROUTE, routeId);
        logger.trace(sql);
        return sql;

    }



    private final static String SQL_GET_CUSTOMER_ID = "select customer_id from customer " +
            "where doc_number = '%s' and document_id = %s";

    public static String getSqlGetCustomerId(String documentNumber, String documentId){

        String sql = String.format(SQL_GET_CUSTOMER_ID, documentNumber, documentId);
        logger.trace(sql);
        return sql;

    }



    private final static String GET_ROUTE_STATION_ID_OUT_STATION_BY_ROUTE_NAME_AND_DEPARTURE_TIME = "select route_station_id from route_station " +
            "join route on route_station.route_id = route.route_id " +
            "where route = '%s' and departure_time = to_timestamp('%s', 'YYYY-MM-DD HH24:MI:SS.FF')";

    public static String getGetRouteStationIdOutStationByRouteNameAndDepartureTime(String nameRoute, String timestamp){

        String sql = String.format(GET_ROUTE_STATION_ID_OUT_STATION_BY_ROUTE_NAME_AND_DEPARTURE_TIME, nameRoute, timestamp);
        logger.trace(sql);
        return sql;

    }



    private final static String GET_ROUTE_STATION_ID_IN_STATION_BY_ROUTE_NAME_AND_DEPARTURE_TIME = "select route_station_id from route_station " +
            "join route on route_station.route_id = route.route_id " +
            "where route = '%s' and arrival_time = to_timestamp('%s', 'YYYY-MM-DD HH24:MI:SS.FF')";

    public static String getGetRouteStationIdInStationByRouteNameAndDepartureTime(String nameRoute, String timestamp){

        String sql = String.format(GET_ROUTE_STATION_ID_IN_STATION_BY_ROUTE_NAME_AND_DEPARTURE_TIME, nameRoute, timestamp);
        logger.trace(sql);
        return sql;

    }



    private final static String SQL_GET_WAGON_ID_BY_NUMBER_TRAIN_AND_ORDER_WAGON = "select wagon.wagon_id from wagon "+
            "join train on train.train_id = wagon.train_id "+
            "where train.number_train = '%s' and wagon.order_wagon = %s";

    public static String getSqlGetWagonIdByNumberTrainAndOrderWagon(String numberTrain, String orderWagon){

        String sql = String.format(SQL_GET_WAGON_ID_BY_NUMBER_TRAIN_AND_ORDER_WAGON, numberTrain, orderWagon);
        logger.trace(sql);
        return sql;

    }



    private final static String SQL_GET_CUSTOMERS_JOIN_DOCUMENT = "select customer_id, first_name, middle_name, last_name, birthday, gender, document.document_id, document.document, doc_number, " +
            "email, telephone " +
            "from customer " +
            "join document on customer.document_id = document.document_id";

    public static String getSqlGetCustomersJoinDocument(){

        return SQL_GET_CUSTOMERS_JOIN_DOCUMENT;

    }



    private final static String SQL_GET_DOCUMENTS = "select * from document";

    public static String getSqlGetDocuments(){

        return SQL_GET_DOCUMENTS;

    }



    private final static String SQL_GET_ROUTES = "select * from route";

    public static String getSqlGetRoutes(){

        return SQL_GET_ROUTES;

    }



    private final static String SQL_GET_ROUTE_STATIONS_JOIN_ROUTE_AND_STATION = "select route_station.route_station_id, route.route_id, route.route, station.station_id, station.station, route_station.order_station, route_station.arrival_time, route_station.departure_time, route_station.distance from route_station " +
            "join route on route_station.route_id = route.route_id " +
            "join station on route_station.station_id = station.station_id";

    public static String getSqlGetRouteStationsJoinRouteAndStation(){

        return SQL_GET_ROUTE_STATIONS_JOIN_ROUTE_AND_STATION;

    }



    private final static String SQL_GET_STATIONS_JOIN_CITY = "select station.station_id, station.station, city.city_id, city.city from city " +
            "right join station on city.city_id = station.city_id " +
            "order by station.station_id";

    public static String getSqlGetStationsJoinCity(){

        return SQL_GET_STATIONS_JOIN_CITY;

    }



    private final static String SQL_GET_TICKETS_JOIN_CUSTOMER = "select ticket.ticket_id, customer.customer_id, customer.first_name, customer.middle_name, customer.last_name, ticket.wagon_id, " +
            "ticket.place, ticket.date_buy, ticket.first_route_station_id, ticket.second_route_station_id, ticket.price from ticket " +
            "join customer on ticket.customer_id = customer.customer_id";

    public static String sqlGetTicketsJoinCustomer(){

        return SQL_GET_TICKETS_JOIN_CUSTOMER;

    }



    private final static String SQL_GET_TRAINS_JOIN_ROUTE = "select train.train_id, train.number_train, route.route_id, route.ROUTE, train.express from train " +
            "join route on train.route_id = route.route_id";

    public static String getSqlGetTrainsJoinRoute(){

       return SQL_GET_TRAINS_JOIN_ROUTE;

    }



    private final static String SQL_GET_TYPE_WAGONS = "select * from type_wagon";

    public static String getSqlGetTypeWagons(){

        return SQL_GET_TYPE_WAGONS;

    }



    private final static String SQL_GET_WAGONS_JOIN_TYPE_WAGON_AND_TRAIN = "select wagon.wagon_id, train.train_id, train.number_train, type_wagon.type_wagon_id, type_wagon.type_name, wagon.order_wagon from wagon " +
            "join type_wagon on wagon.TYPE_WAGON_ID = type_wagon.type_wagon_id " +
            "join train on wagon.train_id = train.train_id";

    public static String getSqlGetWagonIdByNumberTrainAndOrderWagon(){

        return SQL_GET_WAGONS_JOIN_TYPE_WAGON_AND_TRAIN;

    }

}
