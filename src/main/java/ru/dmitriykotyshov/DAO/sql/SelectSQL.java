package ru.dmitriykotyshov.DAO.sql;

import org.apache.log4j.Logger;
import ru.dmitriykotyshov.DAO.SelectDAO;
import ru.dmitriykotyshov.other.MyDate;
import ru.dmitriykotyshov.trainticketobjects.City;
import ru.dmitriykotyshov.trainticketobjects.Wagon;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static ru.dmitriykotyshov.other.MyDate.dateFormat;
import static ru.dmitriykotyshov.other.MyDate.dateFormatAddDay;

/**
 * Created by Дмитрий on 01.02.2018.
 */
public class SelectSQL {

    private static final String SQL_GET_CITIES = "select * from city";

    private static final String SQL_GET_CITY_BY_NAME_CITY = "select * from city where city = '%s'";

    private static final String SQL_GET_CITY_BY_CITY_ID = "select * from city where city_id = %d";

    private static final String SQL_GET_COUNT_ROUTES = "select route_station.route_id, route.route, count(route_station.station_id) " +
            "from route_station " +
            "join route on route.route_id = route_station.route_id " +
            "where route_station.station_id in (%d,%d) " +
            "group by route_station.route_id, route.route " +
            "having count(route_station.station_id)>=2";

    private static final String SQL_GET_STATIONS_BY_NAME_STATION_AND_CITY_ID = "select * from station where station = '%s' or city_id = %d";

    private static final String SQL_GET_TRAIN_BY_ROUTE_ID = "select * from train where route_id = %d";

    private static final String SQL_GET_WAGONS_BY_TRAIN_ID = "select wagon.wagon_id, wagon.order_wagon, type_wagon.type_name, type_wagon.bio_tiolet, type_wagon.air_condition, type_wagon.count_place " +
            "from wagon " +
            "join type_wagon on wagon.type_wagon_id = type_wagon.type_wagon_id " +
            "where wagon.train_id = %d " +
            "order by order_wagon";

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

    private static final String SQL_GET_NUMBER_ROUTE_BY_FIRST_STATION_ID_AND_SECOND_STATION_ID = "select route_id, count(station_id) " +
            "from route_station " +
            "where station_id in (%d,%d) " +
            "group by route_id " +
            "having count(station_id)=2";

    private static final String SQL_GET_ORDER_STATION_ON_ROUTE_OUT = "select order_station " +
            "from route_station " +
            "where route_id = %d and station_id = %d " +
            "and DEPARTURE_TIME>TO_TIMESTAMP('%s', 'DD/MM/YYYY') " +
            "and DEPARTURE_TIME<TO_TIMESTAMP('%s', 'DD/MM/YYYY')";

    private static final String SQL_GET_ORDER_STATION_ON_ROUTE_IN = "select order_station " +
            "from route_station " +
            "where route_id = %d and station_id = %d " +
            "and ARRIVAL_TIME>TO_TIMESTAMP('%s', 'DD/MM/YYYY') " +
            "and ARRIVAL_TIME<TO_TIMESTAMP('%s', 'DD/MM/YYYY')";

    private static final String SQL_GET_TIMESTAMP_STATION_OUT = "select DEPARTURE_TIME, DISTANCE " +
            "from route_station " +
            "where route_id = %d and station_id = %d " +
            "and DEPARTURE_TIME>TO_TIMESTAMP('%s', 'DD/MM/YYYY') " +
            "and DEPARTURE_TIME<TO_TIMESTAMP('%s', 'DD/MM/YYYY')";

    private static final String SQL_GET_TIMESTAMP_STATION_IN = "select ARRIVAL_TIME, DISTANCE " +
            "from route_station " +
            "where route_id = %d and station_id = %d " +
            "and ARRIVAL_TIME>TO_TIMESTAMP('%s', 'DD/MM/YYYY') " +
            "and ARRIVAL_TIME<TO_TIMESTAMP('%s', 'DD/MM/YYYY')";

    private static final String SQL_GET_NAME_ROUTE = "select route from route where route_id = %d";

    private final static String SQL_GET_CUSTOMER_ID = "select customer_id from customer " +
            "where doc_number = '%s' and document_id = %s";

    private final static String SQL_GET_ROUTE_STATION_ID_OUT_STATION_BY_ROUTE_NAME_AND_DEPARTURE_TIME = "select route_station_id from route_station " +
            "join route on route_station.route_id = route.route_id " +
            "where route = '%s' and departure_time = to_timestamp('%s', 'YYYY-MM-DD HH24:MI:SS.FF')";

    private final static String SQL_GET_ROUTE_STATION_ID_IN_STATION_BY_ROUTE_NAME_AND_DEPARTURE_TIME = "select route_station_id from route_station " +
            "join route on route_station.route_id = route.route_id " +
            "where route = '%s' and arrival_time = to_timestamp('%s', 'YYYY-MM-DD HH24:MI:SS.FF')";

    private final static String SQL_GET_WAGON_ID_BY_NUMBER_TRAIN_AND_ORDER_WAGON = "select wagon.wagon_id from wagon "+
            "join train on train.train_id = wagon.train_id "+
            "where train.number_train = '%s' and wagon.order_wagon = %d";

    private final static String SQL_GET_CUSTOMERS_JOIN_DOCUMENT = "select customer_id, first_name, middle_name, last_name, birthday, gender, document.document_id, document.document, doc_number, " +
            "email, telephone " +
            "from customer " +
            "join document on customer.document_id = document.document_id";

    private final static String SQL_GET_DOCUMENTS = "select * from document";

    private final static String SQL_GET_ROUTES = "select * from route";

    private final static String SQL_GET_ROUTE_STATIONS_JOIN_ROUTE_AND_STATION = "select route_station.route_station_id, route.route_id, route.route, station.station_id, station.station, route_station.order_station, route_station.arrival_time, route_station.departure_time, route_station.distance from route_station " +
            "join route on route_station.route_id = route.route_id " +
            "join station on route_station.station_id = station.station_id";

    private final static String SQL_GET_STATIONS_JOIN_CITY = "select station.station_id, station.station, city.city_id, city.city from city " +
            "right join station on city.city_id = station.city_id " +
            "order by station.station_id";

    private final static String SQL_GET_TICKETS_JOIN_CUSTOMER = "select ticket.ticket_id, customer.customer_id, customer.first_name, customer.middle_name, customer.last_name, ticket.wagon_id, " +
            "ticket.place, ticket.date_buy, ticket.first_route_station_id, ticket.second_route_station_id, ticket.price from ticket " +
            "join customer on ticket.customer_id = customer.customer_id";

    private final static String SQL_GET_TRAINS_JOIN_ROUTE = "select train.train_id, train.number_train, route.route_id, route.ROUTE, train.express from train " +
            "join route on train.route_id = route.route_id";

    private final static String SQL_GET_TYPE_WAGONS = "select * from type_wagon";

    private final static String SQL_GET_WAGONS_JOIN_TYPE_WAGON_AND_TRAIN = "select wagon.wagon_id, train.train_id, train.number_train, type_wagon.type_wagon_id, type_wagon.type_name, wagon.order_wagon from wagon " +
            "join type_wagon on wagon.TYPE_WAGON_ID = type_wagon.type_wagon_id " +
            "join train on wagon.train_id = train.train_id";



    private final static Logger logger = Logger.getLogger(SelectDAO.class);

    private SelectSQL() {}


    public static String sqlGetCities(){

        return SQL_GET_CITIES;

    }



    public static String sqlGetCityByNameCity(String nameCity) {

        String sql = String.format(SQL_GET_CITY_BY_NAME_CITY, nameCity);
        logger.trace(sql);
        return sql;

    }



    public static String sqlGetCityByCityId(int cityId) {

        String sql = String.format(SQL_GET_CITY_BY_CITY_ID, cityId);
        logger.trace(sql);
        return sql;

    }



    public static String sqlGetStationsByNameStationAndCityId(String nameStation, City city) {

        String sql = String.format(SQL_GET_STATIONS_BY_NAME_STATION_AND_CITY_ID, nameStation, city.getId());
        logger.trace(sql);
        return sql;

    }



    public static String sqlGetCountRoutes(int firstStationId, int secondStationId) {

        String sql = String.format(SQL_GET_COUNT_ROUTES, firstStationId, secondStationId);
        logger.trace(sql);
        return sql;

    }



    public static String sqlGetTrainByRouteId(int routeId) {

        String sql = String.format(SQL_GET_TRAIN_BY_ROUTE_ID, routeId);
        logger.trace(sql);
        return sql;

    }



    public static String sqlGetWagonsByTrainId(int trainId) {

        String sql = String.format(SQL_GET_WAGONS_BY_TRAIN_ID, trainId);
        logger.trace(sql);
        return sql;

    }



    public static String sqlGetBuyPlacesForWagon(Wagon wagon) {

        String sql = String.format(SQL_GET_BUY_PLACES_FOR_WAGON, wagon.getWagonId(),
                wagon.getTrain().getRoute().getTimeDateFirstStation(), wagon.getTrain().getRoute().getTimeDateFirstStation(),
                wagon.getTrain().getRoute().getTimeDateSecondStation(), wagon.getTrain().getRoute().getTimeDateSecondStation(),
                wagon.getTrain().getRoute().getTimeDateFirstStation(), wagon.getTrain().getRoute().getTimeDateSecondStation(),
                wagon.getTrain().getRoute().getTimeDateFirstStation(), wagon.getTrain().getRoute().getTimeDateSecondStation());
        logger.trace(sql);
        return sql;

    }



    public static String sqlGetNumberRouteByFirstStationIdAndSecondStationId(int firstStationdId, int secondStationId) {

        String sql = String.format(SQL_GET_NUMBER_ROUTE_BY_FIRST_STATION_ID_AND_SECOND_STATION_ID, firstStationdId, secondStationId);
        logger.trace(sql);
        return sql;

    }



    public static String sqlGetOrderStationOnRouteOut(int route, int i, Date date) {

        String sql = String.format(SQL_GET_ORDER_STATION_ON_ROUTE_OUT, route, i, dateFormat(date),
                dateFormatAddDay(date));
        logger.trace(sql);
        return sql;

    }



    public static String sqlGetOrderStationOnRouteIn(int route, int i, Date date) {

        String sql = String.format(SQL_GET_ORDER_STATION_ON_ROUTE_IN, route, i, dateFormat(date),
                dateFormatAddDay(date));
        logger.trace(sql);
        return sql;

    }



    public static String sqlGetTimestampStationOut(int routeId, int stationId, Date date) {

        String sql = String.format(SQL_GET_TIMESTAMP_STATION_OUT, routeId, stationId, dateFormat(date),
                dateFormatAddDay(date));
        logger.trace(sql);
        return sql;

    }



    public static String sqlGetTimestampStationIn(int routeId, int stationId, Date date) {

        String sql = String.format(SQL_GET_TIMESTAMP_STATION_IN, routeId, stationId, dateFormat(date),
                dateFormatAddDay(date));
        logger.trace(sql);
        return sql;

    }



    public static String sqlGetNameRoute(int routeId){

        String sql = String.format(SQL_GET_NAME_ROUTE, routeId);
        logger.trace(sql);
        return sql;

    }



    public static String sqlGetCustomerId(String documentNumber, String documentId){

        String sql = String.format(SQL_GET_CUSTOMER_ID, documentNumber, documentId);
        logger.trace(sql);
        return sql;

    }



    public static String sqlGetRouteStationIdOutStationByRouteNameAndDepartureTime(String nameRoute, String timestamp){

        String sql = String.format(SQL_GET_ROUTE_STATION_ID_OUT_STATION_BY_ROUTE_NAME_AND_DEPARTURE_TIME, nameRoute, timestamp);
        logger.trace(sql);
        return sql;

    }



    public static String sqlGetRouteStationIdInStationByRouteNameAndDepartureTime(String nameRoute, String timestamp){

        String sql = String.format(SQL_GET_ROUTE_STATION_ID_IN_STATION_BY_ROUTE_NAME_AND_DEPARTURE_TIME, nameRoute, timestamp);
        logger.trace(sql);
        return sql;

    }



    public static String sqlGetWagonIdByNumberTrainAndOrderWagon(String numberTrain, int orderWagon){

        String sql = String.format(SQL_GET_WAGON_ID_BY_NUMBER_TRAIN_AND_ORDER_WAGON, numberTrain, orderWagon);
        logger.trace(sql);
        return sql;

    }



    public static String sqlGetCustomersJoinDocument(){

        return SQL_GET_CUSTOMERS_JOIN_DOCUMENT;

    }



    public static String sqlGetDocuments(){

        return SQL_GET_DOCUMENTS;

    }



    public static String sqlGetRoutes(){

        return SQL_GET_ROUTES;

    }



    public static String sqlGetRouteStationsJoinRouteAndStation(){

        return SQL_GET_ROUTE_STATIONS_JOIN_ROUTE_AND_STATION;

    }



    public static String sqlGetStationsJoinCity(){

        return SQL_GET_STATIONS_JOIN_CITY;

    }



    public static String sqlGetTicketsJoinCustomer(){

        return SQL_GET_TICKETS_JOIN_CUSTOMER;

    }



    public static String sqlGetTrainsJoinRoute(){

       return SQL_GET_TRAINS_JOIN_ROUTE;

    }



    public static String sqlGetTypeWagons(){

        return SQL_GET_TYPE_WAGONS;

    }



    public static String sqlGetWagonIdByNumberTrainAndOrderWagon(){

        return SQL_GET_WAGONS_JOIN_TYPE_WAGON_AND_TRAIN;

    }

}
