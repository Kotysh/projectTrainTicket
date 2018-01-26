package ru.dmitriykotyshov.trainticketobjects;

/**
 * Created by Дмитрий on 25.01.2018.
 */
public class WagonDB {

    private int wagonId;
    private Train train;
    private TypeWagon typeWagon;
    private int orderWagon;

    public WagonDB(int wagonId, Train train, TypeWagon typeWagon, int orderWagon) {
        this.wagonId = wagonId;
        this.train = train;
        this.typeWagon = typeWagon;
        this.orderWagon = orderWagon;
    }

    public WagonDB(int wagonId){
        this.wagonId = wagonId;
    }

    public int getWagonId() {
        return wagonId;
    }

    public void setWagonId(int wagonId) {
        this.wagonId = wagonId;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public TypeWagon getTypeWagon() {
        return typeWagon;
    }

    public void setTypeWagon(TypeWagon typeWagon) {
        this.typeWagon = typeWagon;
    }

    public int getOrderWagon() {
        return orderWagon;
    }

    public void setOrderWagon(int orderWagon) {
        this.orderWagon = orderWagon;
    }
}
