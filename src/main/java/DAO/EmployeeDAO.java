package DAO;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {
    void create(Employee employee) throws SQLException;
    Employee readById(int id);
    List<Employee> readAll();
    void updateById(int id, Employee employee);
    void deleteById(int id);
}

