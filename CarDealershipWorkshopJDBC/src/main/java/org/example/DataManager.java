package org.example;
//a

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private final BasicDataSource basicDataSource;

    public DataManager(String url, String userName, String password) {
        basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(userName);
        basicDataSource.setPassword(password);
    }

    public void addVehicle(String v_vin, int v_year, String v_make, String v_model, String v_type, String v_color, double v_odometer, double v_price, int d_id) {
        String query = "{CALL AddVehicle(?,?,?,?,?,?,?,?,?)}";

        try (Connection connection = basicDataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setString(1, v_vin);
            callableStatement.setInt(2, v_year);
            callableStatement.setString(3, v_make);
            callableStatement.setString(4, v_model);
            callableStatement.setString(5, v_type);
            callableStatement.setString(6, v_color);
            callableStatement.setDouble(7, v_odometer);
            callableStatement.setDouble(8, v_price);
            callableStatement.setInt(9, d_id);

            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addLease(String v_vin, String startDate, String endDate, String customerName, String customerEmail, String customerPhone, double monthlyPayment, double totalAmount, int d_id) {
        String query = "{CALL AddLeaseContract(?,?,?,?,?,?,?,?,?)}";

        try (Connection connection = basicDataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setString(1, v_vin);
            callableStatement.setString(2, startDate);
            callableStatement.setString(3, endDate);
            callableStatement.setString(4, customerName);
            callableStatement.setString(5, customerEmail);
            callableStatement.setString(6, customerPhone);
            callableStatement.setDouble(7, monthlyPayment);
            callableStatement.setDouble(8, totalAmount);
            callableStatement.setInt(9, d_id);

            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSale(String v_vin, String contractDate, String customerName, String customerEmail, String customerPhone, boolean financeOption, double totalAmount, int d_id) {
        String query = "{CALL AddSalesContract(?,?,?,?,?,?,?,?)}";

        try (Connection connection = basicDataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setString(1, v_vin);
            callableStatement.setString(2, contractDate);
            callableStatement.setString(3, customerName);
            callableStatement.setString(4, customerEmail);
            callableStatement.setString(5, customerPhone);
            callableStatement.setBoolean(6, financeOption);
            callableStatement.setDouble(7, totalAmount);
            callableStatement.setInt(8, d_id);

            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Dealership> getAllDealerships() {
        String query = "{CALL GetAllDealerships()}";
        List<Dealership> dealerships = new ArrayList<>();

        try (Connection connection = basicDataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)) {

            ResultSet resultSet = callableStatement.executeQuery();
            while (resultSet.next()) {
                int dealership_id = resultSet.getInt("dealership_id");
                String name = resultSet.getString("name");
                String address = resultSet.getString("address");
                String phone = resultSet.getString("phone");
                dealerships.add(new Dealership(name,address,phone,dealership_id));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealerships;
    }
    public List<Vehicle> getByPriceRange(double v_min, double v_max, int d_id){
        String query = "{CALL GetByPriceRange(?,?,?)}";
        List<Vehicle> priceRange = new ArrayList<>();

        try(Connection connection = basicDataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(query)){

            callableStatement.setDouble(1,v_min);
            callableStatement.setDouble(2,v_max);
            callableStatement.setInt(3,d_id);

            ResultSet resultSet = callableStatement.executeQuery();
            priceRange = processResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priceRange;
    }

    public List<Vehicle> getByColor(String v_color, int d_id){
        String query = "{CALL GetByColor(?,?)}";
        List<Vehicle> byColor = new ArrayList<>();

        try(Connection connection = basicDataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(query)){

            callableStatement.setString(1,v_color);
            callableStatement.setInt(2,d_id);

            ResultSet resultSet = callableStatement.executeQuery();
            byColor = processResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return byColor;
    }

    public List<Vehicle> getByMakeAndModel(String v_make, String v_model, int d_id){
        String query = "{CALL GetVehicleByMakeAndModel(?,?,?)}";
        List<Vehicle> byMakeModel = new ArrayList<>();

        try(Connection connection = basicDataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(query)){

            callableStatement.setString(1,v_make);
            callableStatement.setString(2,v_model);
            callableStatement.setInt(3,d_id);

            ResultSet resultSet = callableStatement.executeQuery();
            byMakeModel = processResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return byMakeModel;
    }

    public List<Vehicle> getByYear(int v_min, int v_max, int d_id){
        String query = "{CALL GetByYearRange(?,?,?)}";
        List<Vehicle> byYear = new ArrayList<>();

        try(Connection connection = basicDataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(query)){

            callableStatement.setInt(1,v_min);
            callableStatement.setInt(2,v_max);
            callableStatement.setInt(3,d_id);

            ResultSet resultSet = callableStatement.executeQuery();
            byYear = processResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return byYear;
    }

    public List<Vehicle> getByType(String v_type, int d_id){
        String query = "{CALL GetByType(?,?)}";
        List<Vehicle> byType = new ArrayList<>();

        try(Connection connection = basicDataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(query)){

            callableStatement.setString(1,v_type);
            callableStatement.setInt(2,d_id);

            ResultSet resultSet = callableStatement.executeQuery();
            byType = processResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return byType;
    }

    public List<Vehicle> getByMileage(double v_min, double v_max, int d_id){
        String query = "{CALL GetByMileage(?,?,?)}";
        List<Vehicle> byMileage = new ArrayList<>();

        try(Connection connection = basicDataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(query)){

            callableStatement.setDouble(1,v_min);
            callableStatement.setDouble(2,v_max);
            callableStatement.setInt(3,d_id);

            ResultSet resultSet = callableStatement.executeQuery();
            byMileage = processResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return byMileage;
    }

    public List<Vehicle> getByVin(String v_vin, int d_id) {
        String query = "{CALL GetVehicleByVin(?, ?)}";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = basicDataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setString(1, v_vin);
            callableStatement.setInt(2, d_id);

            ResultSet resultSet = callableStatement.executeQuery();
            vehicles = processResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> getAllVehicles(int d_id){
        String query = "{CALL GetAllVehicles(?)}";
        List<Vehicle> allVehicles = new ArrayList<>();

        try(Connection connection = basicDataSource.getConnection();
            CallableStatement callableStatement = connection.prepareCall(query)){

            callableStatement.setInt(1,d_id);

            ResultSet resultSet = callableStatement.executeQuery();
            allVehicles = processResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allVehicles;
    }

    public void removeVehicle(String v_vin, int d_id) {
        String query = "{CALL RemoveVehicle(?, ?)}";

        try (Connection connection = basicDataSource.getConnection();
             CallableStatement callableStatement = connection.prepareCall(query)) {

            callableStatement.setString(1, v_vin);
            callableStatement.setInt(2, d_id);

            callableStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Vehicle> processResultSet(ResultSet resultSet) throws SQLException {
        List<Vehicle> vehicles = new ArrayList<>();
        while (resultSet.next()) {
            String vin = resultSet.getString("vin");
            int year = resultSet.getInt("year");
            String make = resultSet.getString("make");
            String model = resultSet.getString("model");
            String vehicleType = resultSet.getString("vehicleType");
            String color = resultSet.getString("color");
            double odometer = resultSet.getDouble("odometer");
            double price = resultSet.getDouble("price");
            Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
            vehicles.add(vehicle);
        }
        return vehicles;
    }




}
