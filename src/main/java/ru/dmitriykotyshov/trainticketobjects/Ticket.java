package ru.dmitriykotyshov.trainticketobjects;

import java.sql.Date;

/**
 * Created by Дмитрий on 26.01.2018.
 */
public class Ticket {

    private int ticketId;
    private Customer customer;
    private WagonDB wagon;
    private int place;
    private Date dateBuy;
    private RouteStation firstStation;
    private RouteStation secondStation;
    private int price;

    public Ticket(int ticketId, Customer customer, WagonDB wagon, int place, Date dateBuy, RouteStation firstStation, RouteStation secondStation, int price) {
        this.ticketId = ticketId;
        this.customer = customer;
        this.wagon = wagon;
        this.place = place;
        this.dateBuy = dateBuy;
        this.firstStation = firstStation;
        this.secondStation = secondStation;
        this.price = price;
    }

    public Ticket(int ticketId, Customer customer, WagonDB wagon, int place, Date dateBuy, RouteStation firstStation, RouteStation secondStation) {
        this.ticketId = ticketId;
        this.customer = customer;
        this.wagon = wagon;
        this.place = place;
        this.dateBuy = dateBuy;
        this.firstStation = firstStation;
        this.secondStation = secondStation;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", customer=" + customer +
                ", wagon=" + wagon +
                ", place=" + place +
                ", dateBuy=" + dateBuy +
                ", firstStation=" + firstStation +
                ", secondStation=" + secondStation +
                ", price=" + price +
                '}';
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public WagonDB getWagon() {
        return wagon;
    }

    public void setWagon(WagonDB wagon) {
        this.wagon = wagon;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Date getDateBuy() {
        return dateBuy;
    }

    public void setDateBuy(Date dateBuy) {
        this.dateBuy = dateBuy;
    }

    public RouteStation getFirstStation() {
        return firstStation;
    }

    public void setFirstStation(RouteStation firstStation) {
        this.firstStation = firstStation;
    }

    public RouteStation getSecondStation() {
        return secondStation;
    }

    public void setSecondStation(RouteStation secondStation) {
        this.secondStation = secondStation;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
