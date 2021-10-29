package service;

import dataAccess.BaseDao;
import dataAccess.EmployeeDao;
import model.Employee;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class EmployeeService {
    private final EmployeeDao employeeDao;

    public EmployeeService() throws SQLException, ClassNotFoundException {
        employeeDao = new EmployeeDao();
    }
    public List<Employee> getEmployeeList() throws SQLException {
        List<Employee> employeeList = employeeDao.getEmployeeList();
        employeeList.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.compareTo(o2);
            }
        });
        return employeeList;
    }
}
