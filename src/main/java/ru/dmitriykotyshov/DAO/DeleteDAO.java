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

        this.delete(getSqlDeleteCustomer(customerId));

    }

    public void deleteCity(String cityId){

        this.delete(getSqlDeleteCity(cityId));

    }

    public void deleteDocument(String documentId){

        this.delete(getSqlDeleteDocument(documentId));

    }

    public void deleteRoute(String routeId){

        this.delete(getSqlDeleteRoute(routeId));

    }

    public void deleteRouteStation(String routeStationId){

        this.delete(getSqlDeleteRouteStation(routeStationId));

    }

    public void deleteStation(String stationId){

        this.delete(getSqlDeleteStation(stationId));

    }

    public void deleteTicket(String ticketId){

        this.delete(getSqlDeleteTicket(ticketId));

    }

    public void deleteTrain(String trainId){

        this.delete(getSqlDeleteTrain(trainId));

    }

    public void deleteTypeWagon(String typeWagonId){

        this.delete(getSqlDeleteTypeWagon(typeWagonId));

    }

    public void deleteWagon(String wagonId){

        this.delete(getSqlDeleteWagon(wagonId));

    }



}
