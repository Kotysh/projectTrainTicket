package ru.dmitriykotyshov.DAO;

import org.apache.log4j.Logger;
import ru.dmitriykotyshov.other.MyDate;
import ru.dmitriykotyshov.other.PairTimestampDistance;
import ru.dmitriykotyshov.trainticketobjects.*;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.dmitriykotyshov.DAO.sql.SelectSQL.*;

/**
 * Created by Дмитрий on 10.01.2018.
 */
public class SelectDAO {

    private final static Logger logger = Logger.getLogger(SelectDAO.class);

    private static SelectDAO instanceSelectDAO;

    public static synchronized SelectDAO getInstanceSelectDAO(){

        if (instanceSelectDAO == null){
            instanceSelectDAO = new SelectDAO();
        }

        return instanceSelectDAO;

    }

    private SelectDAO(){}



    public List<City> getCities(){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetCities());

        List<City> cities = new ArrayList<City>();

        try {
            while (resultSet.next()){
                cities.add(new City(resultSet.getInt(1), resultSet.getString(2)));
                logger.trace("найден город - "+cities.get(cities.size()-1));
            }
        } catch (SQLException e) {
            logger.debug("getCities() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return cities;

    }

    public City getCity(String nameCity){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetCityByNameCity(nameCity));

        City city = new City();

        try {

            if (resultSet.next()){
                city.setId(resultSet.getInt(1));
                city.setNameCity(resultSet.getString(2));
                logger.trace("найден город - "+city);
            }else{
                logger.trace("город c именем "+nameCity+" не найден");
            }

        } catch (SQLException e) {
            logger.debug("getCity() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return city;

    }

    public City getCity(int cityId){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetCityByCityId(cityId));

        City city = new City();

        try {

            if (resultSet.next()){
                city.setId(resultSet.getInt(1));
                city.setNameCity(resultSet.getString(2));
                logger.trace("найден город - "+city);
            }else{
                logger.trace("город по идентификатору "+cityId+" не найден");
            }

        } catch (SQLException e) {
            logger.debug("getCity() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return city;

    }

    public List<Station> getStations(String nameStation, City city){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetStationsByNameStationAndCityId(nameStation, city));

        List<Station> stations = new ArrayList<Station>();

        try{

            if (resultSet.next()) {
                do {
                    if (city.getId() == 0) {
                        city = getCity(resultSet.getInt(3));
                    }
                    Station newStation = new Station(resultSet.getInt(1), resultSet.getString(2), city);
                    logger.trace("Найдена станция "+newStation);
                    stations.add(newStation);

                } while (resultSet.next());
            }else{
                logger.trace("Станции с названием "+nameStation+" отсутствуют");
            }

        }catch (SQLException e){
            logger.debug("getStations() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return stations;

    }

    public List<Route> getRoutes (List<Station> firstStationList, List<Station> secondStationList, Date date){


        List<Route> routes = new ArrayList<Route>();
        int orderFirstStation = 0;
        int orderSecondStation = 0;

        for (int i=0; i<firstStationList.size(); i++){
            for (int j=0; j<secondStationList.size(); j++){

                ConnectionDAO connectionDAO = new ConnectionDAO();

                ResultSet resultSet = connectionDAO.getSelect(sqlGetCountRoutes(firstStationList.get(i).getId(), secondStationList.get(j).getId()));

                try {
                    if (resultSet.next()){
                        do{

                            orderFirstStation = getOrderStationOnRoute(resultSet.getInt(1), firstStationList.get(i).getId(), date, 1);
                            orderSecondStation = getOrderStationOnRoute(resultSet.getInt(1), secondStationList.get(j).getId(), date, 2);

                            if (orderFirstStation<orderSecondStation){

                                PairTimestampDistance timestampFirstStation = getTimestampStation(resultSet.getInt(1), firstStationList.get(i).getId(), date, 1);
                                PairTimestampDistance timestampSecondStation = getTimestampStation(resultSet.getInt(1), secondStationList.get(j).getId(), date, 2);

                                Route newRoute = new Route(resultSet.getInt(1), resultSet.getString(2),
                                        firstStationList.get(i), secondStationList.get(j),
                                        timestampFirstStation.getTimestamp(), timestampSecondStation.getTimestamp(),
                                        timestampFirstStation.getDistance(), timestampSecondStation.getDistance());
                                logger.trace("найден маршрут - "+newRoute);
                                routes.add(newRoute);

                            }

                        }while(resultSet.next());
                    }
                } catch (SQLException e) {
                    logger.debug("getRoutes() - SQLException: ", e);
                }


                connectionDAO.disconnect();

            }
        }

        return routes;
    }

    public List<Train> getTrain(Route route){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        List<Train> trains = new ArrayList<Train>();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetTrainByRouteId(route.getId()));

        try {
            if (resultSet.next()) {
                do {

                    Train newTrain = new Train(resultSet.getInt(1), resultSet.getString(2), route,
                            (resultSet.getString(4) == null ? false : true));
                    logger.trace("найен поезд - "+newTrain);
                    trains.add(newTrain);

                } while (resultSet.next());
            }else{
                logger.trace("Поезда на маршруте "+route.getNameRoute()+" отсутствуют");
            }
        } catch (SQLException e) {
            logger.debug("getTrain() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return trains;

    }

    public List<Wagon> getWagons(Train train){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        List<Wagon> wagons = new ArrayList<Wagon>();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetWagonsByTrainId(train.getId()));

        try {
            if (resultSet.next()){
                do{
                    Wagon newWagon = new Wagon(resultSet.getInt(1), train,
                            resultSet.getString(3), resultSet.getString(4) != null,
                            resultSet.getString(5) != null, resultSet.getInt(2),
                            resultSet.getInt(6));
                    logger.trace("наден вагон - "+newWagon);
                    wagons.add(newWagon);
                }while (resultSet.next());
            }else{
                logger.trace("У поезда "+train.getNumberTrain()+" нет вагонов");
            }
        } catch (SQLException e) {
            logger.debug("getWagons() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return wagons;
    }

    public Set<Integer> getPlaces(Wagon wagon){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        Set<Integer> places = new HashSet<Integer>();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetBuyPlacesForWagon(wagon));

        try {
            if (resultSet.next()){
                do{

                    places.add(resultSet.getInt(1));

                }while (resultSet.next());
            }else{
                logger.trace("Все места в вагоне №"+wagon.getOrder()+" свободны");
            }
        } catch (SQLException e) {
            logger.debug("getPlaces() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return places;

    }

    public int getNumberRoute (int firstStationdId, int secondStationId){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetNumberRouteByFirstStationIdAndSecondStationId(firstStationdId, secondStationId));

        int route = 0;

        try {
            if (resultSet.next()){
                route = resultSet.getInt(1);
                logger.trace("номер маршрута между двумя станциями - "+route);
            }
        } catch (SQLException e) {
            logger.debug("getNumberRoute() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return route;

    }

    public int getOrderStationOnRoute (int route, int i, Date date, int index){

        ConnectionDAO connectionDAO = new ConnectionDAO();
        String sql;

        if (index == 1) {//OUT = 1
            sql = sqlGetOrderStationOnRouteOut(route, i, date);
        }else{          //IN = 2
            sql = sqlGetOrderStationOnRouteIn(route, i, date);
        }

        int order = 0;
        ResultSet resultSet = connectionDAO.getSelect(sql);

        try {
            if (resultSet.next()){
                order = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.debug("getOrderStationOnRoute() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return order;

    }

    public PairTimestampDistance getTimestampStation(int routeId, int stationId, Date date, int index){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        String sql;

        if (index == 1) {//OUT = 1
            sql = sqlGetTimestampStationOut(routeId, stationId, date);
        }else{          //IN = 2
            sql = sqlGetTimestampStationIn(routeId, stationId, date);
        }

        PairTimestampDistance pairTimestampDistance = new PairTimestampDistance();

        ResultSet resultSet = connectionDAO.getSelect(sql);

        try {
            if (resultSet.next()){
                pairTimestampDistance.setTimestamp(resultSet.getTimestamp(1));
                pairTimestampDistance.setDistance(resultSet.getInt(2));
            }
        } catch (SQLException e) {
            logger.debug("getTimestampStation() - SQLException: ", e);
        }

        connectionDAO.disconnect();


        return pairTimestampDistance;
    }

    public String getNameRoute(int routeId) {

        ConnectionDAO connectionDAO = new ConnectionDAO();

        String nameRoute = "";

        ResultSet resultSet = connectionDAO.getSelect(sqlGetNameRoute(routeId));

        try {
            if (resultSet.next()){
                nameRoute = resultSet.getString(1);
            }
        } catch (SQLException e) {
            logger.debug("getNameRoute() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return nameRoute;

    }

    public int getCustomerId(String documentNumber, String documentId){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetCustomerId(documentNumber, documentId));

        int customerId = 0;

        try {
            if (resultSet.next()){
                customerId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.debug("getCustomerId() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return customerId;
    }

    public int getRouteStationId(String nameRoute, String timestamp, int index){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        int routeStationId = 0;

        String sql = "";

        if (index == 1){//OUT = 1
            sql = sqlGetRouteStationIdOutStationByRouteNameAndDepartureTime(nameRoute, timestamp);
        }else{          //IN = 2
            sql = sqlGetRouteStationIdInStationByRouteNameAndDepartureTime(nameRoute, timestamp);
        }

        ResultSet resultSet = connectionDAO.getSelect(sql);

        try {
            if (resultSet.next()) {
                routeStationId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.debug("getRouteStationId() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return routeStationId;

    }

    public int getWagonId(String numberTrain, int orderWagon){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetWagonIdByNumberTrainAndOrderWagon(numberTrain, orderWagon));

        int wagonId = 0;

        try {
            if (resultSet.next()){
                wagonId = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            logger.debug("getWagonId() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return wagonId;
    }

    public List<Customer> getCustomersJoinDocument(){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetCustomersJoinDocument());

        List<Customer> customers = new ArrayList<Customer>();

        try {
            while (resultSet.next()){
                customers.add(new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),
                        resultSet.getDate(5), resultSet.getString(6), new Document(resultSet.getInt(7), resultSet.getString(8)),
                        resultSet.getString(9), resultSet.getString(10), resultSet.getString(11)));
            }
        } catch (SQLException e) {
            logger.debug("getCustomersJoinDocument() - SQLException: ", e);
        }

        return customers;

    }

    public List<Document> getDocuments(){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetDocuments());

        List<Document> documents = new ArrayList<Document>();

        try {
            while (resultSet.next()){
                documents.add(new Document(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            logger.debug("getDocuments() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return documents;

    }

    public List<RouteDB> getRoutes(){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetRoutes());

        List<RouteDB> routes = new ArrayList<RouteDB>();

        try {
            while (resultSet.next()){
                routes.add(new RouteDB(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            logger.debug("getRoutes() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return routes;

    }

    public List<RouteStation> getRouteStationsJoinRouteAndStation(){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetRouteStationsJoinRouteAndStation());

        List<RouteStation> routeStations = new ArrayList<RouteStation>();

        try {
            while (resultSet.next()){
                routeStations.add(new RouteStation(resultSet.getInt(1),
                        new Route(resultSet.getInt(2), resultSet.getString(3)),
                        new Station(resultSet.getInt(4), resultSet.getString(5)),
                        resultSet.getInt(6),
                        resultSet.getTimestamp(7), resultSet.getTimestamp(8), resultSet.getInt(9)));
            }
        } catch (SQLException e) {
            logger.debug("getRouteStationsJoinRouteAndStation() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return routeStations;

    }

    public List<Station> getStationsJoinCity(){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetStationsJoinCity());

        List<Station> stations = new ArrayList<Station>();

        try {
            while (resultSet.next()){

                stations.add(new Station(resultSet.getInt(1), resultSet.getString(2),
                        new City(resultSet.getInt(3) == 0 ? 0 : resultSet.getInt(3),
                                resultSet.getString(4) == null ? "null" : resultSet.getString(4))));
            }
        } catch (SQLException e) {
            logger.debug("getStationsJoinCity() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return stations;

    }


    public List<Ticket> getTicketsJoinCustomer(){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetTicketsJoinCustomer());

        List<Ticket> tickets = new ArrayList<Ticket>();

        try {
            while (resultSet.next()){
                tickets.add(new Ticket(resultSet.getInt(1),
                        new Customer(resultSet.getInt(2), resultSet.getString(3),
                                resultSet.getString(4), resultSet.getString(5)),
                        new WagonDB(resultSet.getInt(6)), resultSet.getInt(7),resultSet.getDate(8),
                        new RouteStation(resultSet.getInt(9)),
                        new RouteStation(resultSet.getInt(10)), resultSet.getInt(11)));
            }
        } catch (SQLException e) {
            logger.debug("getTicketsJoinCustomer() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return tickets;

    }


    public List<Train> getTrainsJoinRoute(){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetTrainsJoinRoute());

        List<Train> trains = new ArrayList<Train>();

        try {
            while (resultSet.next()){
                trains.add(new Train(resultSet.getInt(1), resultSet.getString(2),
                        new Route(resultSet.getInt(3), resultSet.getString(4)),
                        resultSet.getString(5) == null? false : true));
            }
        } catch (SQLException e) {
            logger.debug("getTrainsJoinRoute() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return trains;

    }

    public List<TypeWagon> getTypeWagons(){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetTypeWagons());

        List<TypeWagon> typeWagons = new ArrayList<TypeWagon>();

        try {
            while (resultSet.next()){
                typeWagons.add(new TypeWagon(resultSet.getInt(1), resultSet.getString(2),
                        resultSet.getString(3), resultSet.getString(4),
                        resultSet.getInt(5)));
            }
        } catch (SQLException e) {
            logger.debug("getTypeWagons() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return typeWagons;

    }


    public List<WagonDB> getWagonsJoinTypeWagonAndTrain(){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect(sqlGetWagonIdByNumberTrainAndOrderWagon());

        List<WagonDB> wagons = new ArrayList<WagonDB>();

        try {
            while (resultSet.next()){
                wagons.add(new WagonDB(resultSet.getInt(1), new Train(resultSet.getInt(2),resultSet.getString(3)),
                        new TypeWagon(resultSet.getInt(4), resultSet.getString(5)), resultSet.getInt(6)));
            }
        } catch (SQLException e) {
            logger.debug("getWagonsJoinTypeWagonAndTrain() - SQLException: ", e);
        }

        connectionDAO.disconnect();

        return wagons;

    }

}
