package ru.dmitriykotyshov.trainticketobjects;

/**
 * Created by Дмитрий on 09.01.2018.
 */
public class City {

    private int id;
    private String nameCity;

    public City(int id, String nameCity) {
        this.id = id;
        this.nameCity = nameCity;
    }

    public City() {
        this.id = 0;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", nameCity='" + nameCity + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }
}
