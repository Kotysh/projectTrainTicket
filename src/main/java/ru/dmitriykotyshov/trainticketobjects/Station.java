package ru.dmitriykotyshov.trainticketobjects;

/**
 * Created by Дмитрий on 09.01.2018.
 */
public class Station {

    private int id;
    private String nameStation;
    private City city;

    @Override
    public String toString() {
        return "Station{" +
                "numberStation=" + id +
                ", nameStation='" + nameStation + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public Station() {
    }

    public Station(int numberStation, String nameStation, City city) {
        this.id = numberStation;
        this.nameStation = nameStation;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int numberStation) {
        this.id = numberStation;
    }

    public String getNameStation() {
        return nameStation;
    }

    public void setNameStation(String nameStation) {
        this.nameStation = nameStation;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
