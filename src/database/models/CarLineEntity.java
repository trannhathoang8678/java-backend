package database.models;

import org.omg.PortableInterceptor.ServerRequestInfo;

public class CarLineEntity {
    private String carLine;
    private String carManufacter;
    private int numberOfSeats;

    public CarLineEntity(String carLine, String carManufacter, int numberOfSeats) {
        this.carLine = carLine;
        this.carManufacter = carManufacter;
        this.numberOfSeats = numberOfSeats;
    }

    public CarLineEntity() {
    }

    public String getCarLine() {
        return carLine;
    }

    public void setCarLine(String carLine) {
        this.carLine = carLine;
    }

    public String getCarManufacter() {
        return carManufacter;
    }

    public void setCarManufacter(String carManufacter) {
        this.carManufacter = carManufacter;
    }

    public int getNumberOfSeat() {
        return numberOfSeats;
    }

    public void setNumberOfSeat(int numberOfSeat) {
        this.numberOfSeats = numberOfSeat;
    }
    @Override
    public String toString()
    {
        return carLine + " by " + carManufacter + " with " + numberOfSeats + " seats";
    }
}
