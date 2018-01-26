package ru.dmitriykotyshov.trainticketobjects;

/**
 * Created by Дмитрий on 25.01.2018.
 */
public class TypeWagon {

    private int typeWagonId;
    private String typeName;
    private String bioTiolet;
    private String airCondition;
    private int countPlace;

    public TypeWagon(int typeWagonId, String typeName, String bioTiolet, String airCondition, int countPlace) {
        this.typeWagonId = typeWagonId;
        this.typeName = typeName;
        this.bioTiolet = bioTiolet;
        this.airCondition = airCondition;
        this.countPlace = countPlace;
    }

    public TypeWagon (int typeWagonId, String typeName){
        this.typeWagonId = typeWagonId;
        this.typeName = typeName;
    }

    public int getTypeWagonId() {
        return typeWagonId;
    }

    public void setTypeWagonId(int typeWagonId) {
        this.typeWagonId = typeWagonId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBioTiolet() {
        return bioTiolet;
    }

    public void setBioTiolet(String bioTiolet) {
        this.bioTiolet = bioTiolet;
    }

    public String getAirCondition() {
        return airCondition;
    }

    public void setAirCondition(String airCondition) {
        this.airCondition = airCondition;
    }

    public int getCountPlace() {
        return countPlace;
    }

    public void setCountPlace(int countPlace) {
        this.countPlace = countPlace;
    }
}
