package dataAccess;

import model.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao extends BaseDao {

    public EmployeeDao() throws ClassNotFoundException, SQLException {
    }

    public List<Employee> getEmployeeList() throws SQLException {
        List<Employee> employeeList = new ArrayList<>();
        String sql = "select  * from employee where year_employment<=1400 and " +
                "year_employment>1395 Order by year_employment desc ";
        PreparedStatement preparedStatement =getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            employeeList.add(getEmployee(resultSet));
        }
        return employeeList;
    }

    private Employee getEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setFullName(resultSet.getString("fullname"));
        employee.setPersonalId(resultSet.getString("personal_id"));
        employee.setEmploymentYear(resultSet.getInt("year_employment"));
        employee.setSalary(resultSet.getLong("salary"));
        return employee;
    }

    public List<Integer> getEmployeeListYear() throws SQLException {
        List<Integer> employeeListYear = new ArrayList<>();
        String sql = "select  distinct year_employment from employee where year_employment<=1400 and " +
                "year_employment>1395 Order by year_employment desc ";
        PreparedStatement preparedStatement =getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            employeeListYear.add(resultSet.getInt("year_employment"));
        }
        return employeeListYear;
    }
}
