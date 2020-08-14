package database.services;

import java.sql.Connection;

public class CarRentalService {
    private Connection connection;

    public CarRentalService(Connection connection) {
        this.connection = connection;
    }

    public CarRentalService() {
    }
}
