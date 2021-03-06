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
                System.out.println(carLineEntity.toString());
                carLines.add(carLineEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carLines;
    }

    public List<ProviderEntity> getProvidersInfoInCondition() {
        //Toyota over 15000 or KIA over 20000
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
                System.out.println(provider.toString());
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
                 System.out.println(provider.toString());
                providers.add(provider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return providers;
    }
    public void countProviderStartAtTime(String date)
    {
        String sql = "SELECT MaNhaCC,COUNT(*) FROM DANGKYCUNGCAP  WHERE NgayBatDauCungCap = '"
                + date + "' GROUP BY MaNhaCC;";
        System.out.println(sql);
        try {

            Statement statement = connection.createStatement();
            ResultSet countRS = statement.executeQuery(sql);
            while (countRS.next()) {
                System.out.println(countRS.getString(1) + " " + countRS.getInt(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void printCaRManufacters()
    {
        String getCaRManufacters = "SELECT HangXe FROM DONGXE GROUP BY HangXe;" ;
        try {
            Statement statement = connection.createStatement();
            ResultSet carMRS = statement.executeQuery(getCaRManufacters);
            while(carMRS.next())
                System.out.println(carMRS.getString(1));
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void printAllProviders()
    {
        String getProvider = "SELECT P.MaNhaCC,P.TenNhaCC,P.DiaChi,P.MaSoThue,RP.MaDKCC,S.TenLoaiDV,Price.DonGia,\n" +
                "S.TenLoaiDV,Price.DonGia,CL.HangXe,RP.NgayBatDauCungCap, RP.NgayKetThucCungCap FROM NHACUNGCAP P\n" +
                "LEFT JOIN DANGKYCUNGCAP RP ON P.MaNhaCC = RP.MaNhaCC \n" +
                "LEFT JOIN MUCPHI Price ON RP.MaMP = Price.MaMP\n" +
                "LEFT JOIN DONGXE CL ON CL.DongXe = RP.DongXe \n" +
                "LEFT JOIN LOAIDICHVU S ON S.MaLoaiDV = RP.MaLoaiDV ;" ;
        try
        {
            Statement statement = connection.createStatement();
            ResultSet providerRS = statement.executeQuery(getProvider);
            while(providerRS.next()) {
                System.out.print(providerRS.getString(1) );
                for(int i = 2 ; i <= 12 ; i++)
                    System.out.print("," + providerRS.getString(i));
                System.out.println();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public List<ProviderEntity> getProvidersHiaceOrCerato() {
        List<ProviderEntity> providers = new ArrayList<>();
        ProviderEntity provider;
        try {
            String getProvidersInfo = "SELECT Provider.* FROM DANGKYCUNGCAP RP " +
                    "JOIN NHACUNGCAP Provider ON RP.MaNhaCC = Provider.MaNhaCC " +
                    "JOIN DONGXE CL ON CL.DONGXE = RP.DongXe " +
                    "  WHERE  CL.DongXe = 'Hiace' OR  CL.DongXe = 'Cerato' ";
            Statement statement = connection.createStatement();
            ResultSet providerRS = statement.executeQuery(getProvidersInfo);
            while (providerRS.next()) {
                provider = new ProviderEntity(providerRS.getString(1), providerRS.getString(2)
                        , providerRS.getString(3), providerRS.getString(4), providerRS.getString(5));
                System.out.println(provider.toString());
                providers.add(provider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return providers;
    }
    public List<ProviderEntity> getProvidersHaveNotProvided() {
        List<ProviderEntity> providers = new ArrayList<>();
        ProviderEntity provider;
        try {
            String getProvidersInfo = "SELECT P.* FROM NHACUNGCAP P\n" +
                    "LEFT JOIN DANGKYCUNGCAP RP ON P.MaNhaCC = RP.MaNhaCC \n" +
                    "WHERE RP.MaNhaCC IS NULL;" ;
            Statement statement = connection.createStatement();
            ResultSet providerRS = statement.executeQuery(getProvidersInfo);
            while (providerRS.next()) {
                provider = new ProviderEntity(providerRS.getString(1), providerRS.getString(2)
                        , providerRS.getString(3), providerRS.getString(4), providerRS.getString(5));
                System.out.println(provider.toString());
                providers.add(provider);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return providers;
    }
}

