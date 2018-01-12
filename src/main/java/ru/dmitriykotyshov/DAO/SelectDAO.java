package ru.dmitriykotyshov.DAO;

import ru.dmitriykotyshov.other.MyDate;
import ru.dmitriykotyshov.trainticketobjects.City;
import ru.dmitriykotyshov.trainticketobjects.Route;
import ru.dmitriykotyshov.trainticketobjects.Station;
import ru.dmitriykotyshov.trainticketobjects.Train;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 10.01.2018.
 */
public class SelectDAO {

    private SelectDAO(){};

    /**
     *
     * @param nameCity - Название города (name_city)
     * @return - Возвращает город по переменной city
     */
    public static City getCity(String nameCity){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect("select * from city where city = '"+nameCity+"'");

        City city = new City();

        try {

            if (resultSet.next()){
                city.setId(resultSet.getInt(1));
                city.setNameCity(resultSet.getString(2));
                System.out.println(city);
            }else{
                System.out.println("Город "+city+" отсутствует");
            }

        } catch (SQLException e) {
            System.out.println("getCity() - " + e.getMessage());
        }

        connectionDAO.disconnect();

        return city;

    }

    /**
     *
     * @param i - Идентификатор станции (id_city)
     * @return - Возвращает станцию по переменной i
     */
    public static City getCity(int i){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect("select * from city where city_id = "+i);

        City city = new City();

        try {

            if (resultSet.next()){
                city = new City(resultSet.getInt(1), resultSet.getString(2));
                System.out.println(city);
            }else{
                System.out.println("Город c идентификатором "+i+" отсутствует");
            }

        } catch (SQLException e) {
            System.out.println("getCity() - " + e.getMessage());
        }

        connectionDAO.disconnect();

        return city;

    }

    /**
     *
     * @param station - Название станции
     * @param city - Город в котором находятся станции
     * @return - Возвращает все станции расположенные в указанном городе и станции
     * у которых имя совпадает с полем station
     */
    public static List<Station> getStations(String station, City city){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect("select * from station where station = '" + station + "' or city_id = " + city.getId());

        List<Station> stations = new ArrayList<Station>();

        try{

            if (resultSet.next()) {
                do {
                    if (city.getId() == 0) {
                        city = getCity(resultSet.getInt(3));
                    }
                    Station newStation = new Station(resultSet.getInt(1), resultSet.getString(2), city);
                    System.out.println(newStation);
                    stations.add(newStation);

                } while (resultSet.next());
            }else{
                System.out.println("Станции с именем "+station+" отсутствуют");
            }


        }catch (SQLException e){
            System.out.println("getStation() - " + e.getMessage());
        }

        connectionDAO.disconnect();

        return stations;

    }

    /**
     *
     * @param firstStationList - Список станций из пункта А
     * @param secondStationList - Список станций в пункт Б
     * @return  Возвращает количество маршрутов из пунка А в пункт Б
     */
    public static List<Route> getRoutes (List<Station> firstStationList, List<Station> secondStationList, MyDate date){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        List<Route> routes = new ArrayList<Route>();
        int orderFirstStation = 0;
        int orderSecondStation = 0;

        for (int i=0; i<firstStationList.size(); i++){
            for (int j=0; j<secondStationList.size(); j++){

                String sql = "select route_station.route_id, route.route, count(route_station.station_id) " +
                        "from route_station " +
                        "join route on route.route_id = route_station.route_id " +
                        "where route_station.station_id in ("+firstStationList.get(i).getId()+","+secondStationList.get(j).getId()+") " +
                        "group by route_station.route_id, route.route " +
                        "having count(route_station.station_id)=2";

                ResultSet resultSet = connectionDAO.getSelect(sql);

                try {
                    if (resultSet.next()){
                        do{

                            orderFirstStation = getOrderOnRoute(resultSet.getInt(1), firstStationList.get(i).getId(), date, 1);
                            orderSecondStation = getOrderOnRoute(resultSet.getInt(1), secondStationList.get(j).getId(), date, 2);

                            if (orderFirstStation<orderSecondStation){

                                Timestamp timestampFirstStation = getTimestamp(resultSet.getInt(1), firstStationList.get(i).getId(), date, 1);
                                Timestamp timestampSecondStation = getTimestamp(resultSet.getInt(1), secondStationList.get(j).getId(), date, 2);

                                routes.add(new Route(resultSet.getInt(1), resultSet.getString(2),
                                        firstStationList.get(i), secondStationList.get(j), timestampFirstStation, timestampSecondStation));

                            }

                        }while(resultSet.next());
                    }
                } catch (SQLException e) {
                    System.out.println("getRoute() - "+e.getMessage());
                }

            }
        }

        connectionDAO.disconnect();

        return routes;
    }

    /**
     *
     * @param route - Маршрут на котором находятся какое то количество поездов
     * @return - Возвращает List<Train> поездов, которые находятся на данном маршруте
     */
    public static List<Train> getTrain(Route route){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        String sql = "select * from train where route_id = "+route.getId();

        List<Train> trains = new ArrayList<Train>();

        ResultSet resultSet = connectionDAO.getSelect(sql);

        try {
            if (resultSet.next()) {
                do {

                    Train newTrain = new Train(resultSet.getInt(1), resultSet.getString(2),
                            route);
                    trains.add(newTrain);
                    System.out.println(newTrain);

                } while (resultSet.next());
            }else{
                System.out.println("Поезда на маршруте "+route.getNameRoute()+" отсутствуют");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connectionDAO.disconnect();

        return trains;

    }


    /**
     *
     * @param i - id первой станции
     * @param j - id второй станции
     * @return - Возвращает идентификатор маршрута route_id на котором находятся обе станции
     */
    public static int getNumberRoute (int i, int j){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        String sql = "select route_id, count(station_id) "+
                "from route_station "+
                "where station_id in ("+i+","+j+") "+
                "group by route_id "+
                "having count(station_id)=2";

        ResultSet resultSet = connectionDAO.getSelect(sql);

        int route = 0;

        try {
            if (resultSet.next()){
                route = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("getNumberRoute() - " + e.getMessage());
        }

        connectionDAO.disconnect();

        return route;

    }

    /**
     *
     * @param route - Номер маршрута
     * @param i - Номер станции
     * @return - Возвращает порядковый номер станции на маршруте(order_station)
     */
    public static int getOrderOnRoute (int route, int i, MyDate date, int index){

        ConnectionDAO connectionDAO = new ConnectionDAO();
        String sql;
        if (index == 1) {//откуда = 1
            sql = "select order_station " +
                    "from route_station " +
                    "where route_id = " + route + " and station_id = " + i + " " +
                    "and DEPARTURE_TIME>TO_TIMESTAMP('"+date.getYear()+"/"+date.getMonth()+"/"+date.getDay()+"', 'YYYY/MM/DD') " +
                    "and DEPARTURE_TIME<TO_TIMESTAMP('"+date.getYear()+"/"+date.getMonth()+"/"+(date.getDay()+1)+"', 'YYYY/MM/DD')";
        }else{//куда = 2
            sql = "select order_station "+
                    "from route_station "+
                    "where route_id = "+route+" and station_id = "+i+" "+
                    "and ARRIVAL_TIME>TO_TIMESTAMP('"+date.getYear()+"/"+date.getMonth()+"/"+date.getDay()+"', 'YYYY/MM/DD') "+
                    "and ARRIVAL_TIME<TO_TIMESTAMP('"+date.getYear()+"/"+date.getMonth()+"/"+(date.getDay()+1)+"', 'YYYY/MM/DD')";
        }

        int order = 0;

        ResultSet resultSet = connectionDAO.getSelect(sql);

        try {
            if (resultSet.next()){
                order = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("getOrderRoute - " + e.getMessage());
        }

        connectionDAO.disconnect();

        return order;

    }

    public static Timestamp getTimestamp(int route, int station, MyDate date, int index){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        String sql;

        if (index == 1) {//откуда = 1
            sql = "select DEPARTURE_TIME " +
                    "from route_station " +
                    "where route_id = " + route + " and station_id = " + station + " " +
                    "and DEPARTURE_TIME>TO_TIMESTAMP('"+date.getYear()+"/"+date.getMonth()+"/"+date.getDay()+"', 'YYYY/MM/DD') " +
                    "and DEPARTURE_TIME<TO_TIMESTAMP('"+date.getYear()+"/"+date.getMonth()+"/"+(date.getDay()+1)+"', 'YYYY/MM/DD')";
        }else{//куда = 2
            sql = "select ARRIVAL_TIME "+
                    "from route_station "+
                    "where route_id = "+route+" and station_id = "+station+" "+
                    "and ARRIVAL_TIME>TO_TIMESTAMP('"+date.getYear()+"/"+date.getMonth()+"/"+date.getDay()+"', 'YYYY/MM/DD') "+
                    "and ARRIVAL_TIME<TO_TIMESTAMP('"+date.getYear()+"/"+date.getMonth()+"/"+(date.getDay()+1)+"', 'YYYY/MM/DD')";
        }

        Timestamp timestamp = null;

        ResultSet resultSet = connectionDAO.getSelect(sql);

        try {
            if (resultSet.next()){
                timestamp = resultSet.getTimestamp(1);
            }
        } catch (SQLException e) {
            System.out.println("getOrderRoute - " + e.getMessage());
        }

        connectionDAO.disconnect();


        return timestamp;
    }

    /**
     *
     * @param numberRoute - Номер маршрута
     * @return - По указанному id_route(идентификатору) возвращает name_route(имя маршрута)
     */
    public static String getNameRoute(int numberRoute) {

        ConnectionDAO connectionDAO = new ConnectionDAO();

        String sql = "select route from route where route_id = "+numberRoute;

        String name = "";

        ResultSet resultSet = connectionDAO.getSelect(sql);

        try {
            if (resultSet.next()){
                name = resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.out.println("getOrderRoute - " + e.getMessage());
        }

        connectionDAO.disconnect();

        return name;

    }

}
