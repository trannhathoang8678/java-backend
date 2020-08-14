package database.services;

import database.models.CarLineEntity;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CarRentalService {
    private Connection connection;

    public CarRentalService(Connection connection) {
        this.connection = connection;
    }

    public CarRentalService() {
    }

    public List<CarLineEntity> carLineOver5seats() {
        List<CarLineEntity> carLines = new ArrayList<>();
        CarLineEntity carLineEntity;
        try {
            String getcarLine = "SELECT * FROM DONGXE WHERE SoChoNgoi > 5;";
            Statement statement = connection.createStatement();
            ResultSet carLineRS = statement.executeQuery(getcarLine);
            while (carLineRS.next()) {
                carLineEntity = new CarLineEntity(carLineRS.getString(1), carLineRS.getString(2), carLineRS.getInt(3));
//                System.out.println(carLineEntity.toString());
                carLines.add(carLineEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carLines;

    }
}
