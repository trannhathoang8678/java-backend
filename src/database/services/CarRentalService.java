package database.services;

import database.models.CarLineEntity;
import database.models.ProviderEntity;

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

    public List<ProviderEntity> getProvidersInfoInCondition() {
        List<ProviderEntity> providers = new ArrayList<>();
        ProviderEntity provider;
        try {
            String getProvidersInfo = "SELECT Provider.* FROM DANGKYCUNGCAP RP " +
                    "JOIN MUCPHI Price ON RP.MaMP = Price.MaMP " +
                    "JOIN NHACUNGCAP Provider ON RP.MaNhaCC = Provider.MaNhaCC " +
                    "JOIN DONGXE CL ON CL.DONGXE = RP.DongXe " +
                    "  WHERE ( CL.HangXe = 'Toyota' AND Price.DonGia > '15000') OR ( CL.HangXe = 'KIA' AND Price.DonGia > '20000');";
            Statement statement = connection.createStatement();
            ResultSet providerRS = statement.executeQuery(getProvidersInfo);
            while (providerRS.next()) {
                provider = new ProviderEntity(providerRS.getString(1), providerRS.getString(2)
                        , providerRS.getString(3), providerRS.getString(4), providerRS.getString(5));
               // System.out.println(provider.toString());
                providers.add(provider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return providers;
    }
    public List<ProviderEntity> getProvidersInfoInSort() {
        List<ProviderEntity> providers = new ArrayList<>();
        ProviderEntity provider;
        try {
            String getProvidersInfo = "SELECT * FROM NHACUNGCAP  ORDER BY TenNhaCC ASC , MaSoThue DESC;";
            Statement statement = connection.createStatement();
            ResultSet providerRS = statement.executeQuery(getProvidersInfo);
            while (providerRS.next()) {
                provider = new ProviderEntity(providerRS.getString(1), providerRS.getString(2)
                        , providerRS.getString(3), providerRS.getString(4), providerRS.getString(5));
                // System.out.println(provider.toString());
                providers.add(provider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return providers;
    }
}

