package ru.dmitriykotyshov.DAO;


import static ru.dmitriykotyshov.DAO.sql.DeleteSQL.*;

/**
 * Created by Дмитрий on 02.02.2018.
 */
public class DeleteDAO {

    private static DeleteDAO instanceDeleteDAO;

    public static synchronized DeleteDAO getInstanceDeleteDAO(){

        if (instanceDeleteDAO == null){
            instanceDeleteDAO = new DeleteDAO();
        }
        return instanceDeleteDAO;

    }

    private DeleteDAO (){}



    private void delete(String delete){

        ConnectionDAO connectionDAO = new ConnectionDAO();

        connectionDAO.operatorDML(delete);

        connectionDAO.disconnect();

    }


    public void deleteCustomer(String customerId){

        this.delete(sqlDeleteCustomer(customerId));

    }

    public void deleteCity(String cityId){

        this.delete(sqlDeleteCity(cityId));

    }

    public void deleteDocument(String documentId){

        this.delete(sqlDeleteDocument(documentId));

    }

    public void deleteRoute(String routeId){

        this.delete(sqlDeleteRoute(routeId));

    }

    public void deleteRouteStation(String routeStationId){

        this.delete(sqlDeleteRouteStation(routeStationId));

    }

    public void deleteStation(String stationId){

        this.delete(sqlDeleteStation(stationId));

    }

    public void deleteTicket(String ticketId){

        this.delete(sqlDeleteTicket(ticketId));

    }

    public void deleteTrain(String trainId){

        this.delete(sqlDeleteTrain(trainId));

    }

    public void deleteTypeWagon(String typeWagonId){

        this.delete(sqlDeleteTypeWagon(typeWagonId));

    }

    public void deleteWagon(String wagonId){

        this.delete(sqlDeleteWagon(wagonId));

    }



}
