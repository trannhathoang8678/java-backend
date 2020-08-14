package database.models;

public class CarLineEntity {
    private String carLine;
    private String carManufacter;
    private int numberOfSeat;

    public CarLineEntity(String carLine, String carManufacter, int numberOfSeat) {
        this.carLine = carLine;
        this.carManufacter = carManufacter;
        this.numberOfSeat = numberOfSeat;
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
        return numberOfSeat;
    }

    public void setNumberOfSeat(int numberOfSeat) {
        this.numberOfSeat = numberOfSeat;
    }
}
