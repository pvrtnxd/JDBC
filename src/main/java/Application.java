import DAO.EmployeeDAO;
import DAO.Impl.EmployeeDAOImpl;
import model.Employee;

import java.sql.*;
public class Application {
    private static final EmployeeDAO EMPLOYEE_DAO = new EmployeeDAOImpl();
    public static final String USER = System.getenv("user");
    public static final String PASSWORD = System.getenv("password");
    public static final String URL = System.getenv("08001");

    public static void main(String[] args) throws SQLException {
        try (final Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE id = 2")
        ) {

            ResultSet set = statement.executeQuery();
            while (set.next()) {
                System.out.printf("First name: %s\n", set.getString(2));
                System.out.printf("Last name: %s\n", set.getString(3));
                System.out.printf("Gender: %s\n", set.getString(4));
                System.out.printf("Age: %d\n", set.getInt(5));
                System.out.printf("CityId: %s\n", set.getInt(6));
            }
            EMPLOYEE_DAO.create(new Employee("Igor", "Ignatov", "Male", 42, 2));
        }
    }
}