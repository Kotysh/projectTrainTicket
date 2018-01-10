package ru.dmitriykotyshov.DAO;

import ru.dmitriykotyshov.trainticketobjects.City;
import ru.dmitriykotyshov.trainticketobjects.Route;
import ru.dmitriykotyshov.trainticketobjects.Station;
import ru.dmitriykotyshov.trainticketobjects.Train;

import java.sql.ResultSet;
import java.sql.SQLException;
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

        ResultSet resultSet = connectionDAO.getSelect("select * from city where name_city = '"+nameCity+"'");

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

        ResultSet resultSet = connectionDAO.getSelect("select * from city where id_city = "+i);

        City city = null;

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
    public static List<Station> getStation(String station, City city){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        ResultSet resultSet = connectionDAO.getSelect("select * from station where name_station = '" + station + "' or city = " + city.getId());

        List<Station> stations = new ArrayList<Station>();

        try{

            if (resultSet.next()) {
                do {
                    if (city == null) {
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
     * @param i - id первой станции
     * @param j - id второй станции
     * @return - Возвращает идентификатор маршрута на котором находятся обе станции
     */
    public static int getNumberRoute (int i, int j){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        String sql = "select route, count(station) "+
                "from route_station "+
                "where station in ("+i+","+j+") "+
                "group by route "+
                "having count(station)=2";

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
    public static int getOrderRoute (int route, int i){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        String sql = "select order_station " +
                "from route_station " +
                "where route = "+route+" and station = "+i;

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

    /**
     *
     * @param stationsCityOne - List станций отбытия
     * @param stationsCityTwo - List станций прибытия
     * @return - Возвращает List<Route> маршрутов которые удовлетворяют условию
     * (порядковый номер первой станции меньше порядкового номера второй станции)
     */
    public static List<Route> getRoutes(List<Station> stationsCityOne, List<Station> stationsCityTwo){

        List<Route> routes = new ArrayList<Route>();

        for (int i=0; i<stationsCityOne.size(); i++){

            for (int j=0; j<stationsCityTwo.size(); j++){

                int numberRoute = getNumberRoute(stationsCityOne.get(i).getId(), stationsCityTwo.get(j).getId());
                int orderOneStation = getOrderRoute(numberRoute, stationsCityOne.get(i).getId());
                int orderTwoStation = getOrderRoute(numberRoute, stationsCityTwo.get(j).getId());
                System.out.println(orderOneStation+" "+orderTwoStation);
                if (orderOneStation < orderTwoStation){
                    Route newRoute = new Route(numberRoute, getNameRoute(numberRoute), stationsCityOne.get(i),
                            stationsCityTwo.get(j));
                    System.out.println("Маршрут есть! - "+numberRoute);
                    routes.add(newRoute);
                }else{
                    System.out.println("Маршрута нет!");
                }

            }

        }

        return routes;

    }

    /**
     *
     * @param numberRoute - Номер маршрута
     * @return - По указанному id_route(идентификатору) возвращает name_route(имя маршрута)
     */
    public static String getNameRoute(int numberRoute) {

        ConnectionDAO connectionDAO = new ConnectionDAO();

        String sql = "select name_route from route where id_route = "+numberRoute;

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

    /**
     *
     * @param route - Маршрут на котором находятся какое то количество поездов
     * @return - Возвращает List<Train> поездов, которые находятся на данном маршруте
     */
    public static List<Train> getTrain(Route route){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        String sql = "select * from train where route = "+route.getId();

        List<Train> trains = new ArrayList<Train>();

        ResultSet resultSet = connectionDAO.getSelect(sql);

        try {
            if (resultSet.next()) {
                do {

                    Train newTrain = new Train(resultSet.getInt(1), resultSet.getString(2),
                            route, resultSet.getDate(4));
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

}
