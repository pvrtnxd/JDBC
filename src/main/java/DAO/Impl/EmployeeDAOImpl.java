package DAO.Impl;

import DAO.EmployeeDAO;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class EmployeeDAOImpl implements EmployeeDAO {

    private static final String INSERT_QUERY = "INSERT INTO employee (first_name, last_name, gender, age, city_id) VALUES ((?), (?), (?), (?), (?))";
    private static final String UPDATE_QUERY = "UPDATE employee SET first_name=(?), last_name=(?), gender=(?), age=(?), city_id=(?) WHERE id=(?)";
    private static final String DELETE_QUERY = "DELETE FROM employee WHERE id=(?)";
    private static final String SELECT_BY_ID_QUERY = "SELECT * FROM employee WHERE id = ?";
    private static final String SELECT_ALL_QUERY = "SELECT * FROM employee";

    private static final String USER = System.getenv("user");
    public static final String PASSWORD = System.getenv("password");
    public static final String URL = System.getenv("url");

    @Override
    public void create(Employee employee) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     INSERT_QUERY)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCityId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateById(int id, Employee employee) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     UPDATE_QUERY)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCityId());
            statement.setInt(6, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     DELETE_QUERY)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee readById(int id) {
        Employee employee = new Employee();
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     SELECT_BY_ID_QUERY)) {
            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                employee.setId(resultSet.getInt(1));
                employee.setFirstName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setGender(resultSet.getString(4));
                employee.setAge(resultSet.getInt(5));
                employee.setCityId(resultSet.getInt(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> readAll() {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(
                     SELECT_ALL_QUERY)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = (resultSet.getInt(1));
                String firtsName = (resultSet.getString(2));
                String lastName = (resultSet.getString(3));
                String gender = (resultSet.getString(4));
                int age = (resultSet.getInt(5));
                int cityId = (resultSet.getInt(6));

                employees.add(new Employee(id, firtsName, lastName, gender, age, cityId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }
}