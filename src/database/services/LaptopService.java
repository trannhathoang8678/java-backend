package database.services;

import database.models.Counter;
import database.models.LaptopEntity;
import database.models.Statistic;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LaptopService {
    private Connection connection;

    public LaptopService(Connection connection) {
        this.connection = connection;
    }

    public LaptopService() {
    }

    public List<LaptopEntity> excuvateQuerySQLForLaptop(String sql) {
        LaptopEntity laptopEntity;
        List<LaptopEntity> laptopEntities = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                laptopEntity = new LaptopEntity(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getBigDecimal(10), rs.getString(11),
                        rs.getString(12), rs.getString(13), rs.getInt(14), rs.getTimestamp(15), rs.getTimestamp(16));
                laptopEntities.add(laptopEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return laptopEntities;
    }

    public List<LaptopEntity> sortBySoldLaptop(String order) {
        String sql = "SELECT * FROM laptop ORDER BY sold " + order + ";";
        return excuvateQuerySQLForLaptop(sql);
    }

    public List<LaptopEntity> searchLaptop(BigDecimal fromPrice, BigDecimal toPrice, String maker, String screen_size, String ram, String cpu, String type, String card) {
        String sql = "SELECT * FROM laptop WHERE TRUE";
        if (fromPrice != null) {
            sql += " AND price >= " + fromPrice;
        }
        if (toPrice != null) {
            sql += " AND price <= " + toPrice;
        }
        if (maker != null) {
            sql += " AND maker ='" + maker + "'";
        }
        if (screen_size != null) {
            sql += " AND maker ='" + screen_size + "'";
        }
        if (ram != null) {
            sql += " AND maker ='" + ram + "'";
        }
        if (cpu != null) {
            sql += " AND maker ='" + cpu + "'";
        }
        if (type != null) {
            sql += " AND maker ='" + type + "'";
        }
        if (card != null) {
            sql += " AND maker ='" + card + "'";
        }
        sql += ";";

        //System.out.println(sql);//for test
        return excuvateQuerySQLForLaptop(sql);
    }

    public List<Counter> getCounterByMaker() {
        List<Counter> counters = new ArrayList<>();
        String sql = "SELECT `maker`,COUNT(*) AS quantity FROM laptop WHERE `maker` IS NOT NULL GROUP BY maker ORDER BY quantity DESC;";
        try {
            Counter counter;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                counter = new Counter(rs.getString(1), rs.getInt(2));
                counters.add(counter);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
//        for(Counter counter : counters)
//            System.out.println(counter.toString());
//        for test
        return counters;
    }
    public List<Statistic> getStatisticByMaker() {
        List<Statistic> statistics = new ArrayList<>();
        String sql = "SELECT `maker`,SUM(sold),SUM(price) FROM laptop WHERE `maker` IS NOT NULL GROUP BY maker ;";
        try {
            Statistic statistic;
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                statistic = new Statistic(rs.getString(1),rs.getInt(2), rs.getBigDecimal(3));
                statistics.add(statistic);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
//        for(Statistic statistic : statistics)
//            System.out.println(statistic.toString());
     //   for test
        return statistics;
    }

    public void printLaptopList(List<LaptopEntity> laptopEntities) {
        if (laptopEntities == null) {
            System.out.println("There is no laptop suitable");
            return;
        }
        for (LaptopEntity laptopEntity : laptopEntities) {
            System.out.println(laptopEntity.toString());
        }
    }
}
