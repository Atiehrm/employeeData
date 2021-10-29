package view;

import model.Employee;

import model.enums.SalaryRange;
import service.EmployeeService;

import java.sql.SQLException;
import java.util.*;

public class Main {
    private static EmployeeService employeeService;

    static {
        try {
            employeeService = new EmployeeService();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws SQLException {
        showEmployeesDataLastFiveYear();

    }

    public static Map<Integer, Map<SalaryRange, List<Employee>>> getEmployeesPerYearAndSalaryRange()
            throws SQLException {
        Map<Integer, List<Employee>> employeesPerYear = new HashMap<>();
        List<Employee> employeeList = employeeService.getEmployeeList();
        for (Employee employee : employeeList) {
            mapEmployeesPerYear(employeesPerYear, employee);
        }
        Map<Integer, Map<SalaryRange, List<Employee>>> resultMap = new HashMap<>();
        for (Map.Entry<Integer, List<Employee>> entry : employeesPerYear.entrySet()) {
            Map<SalaryRange, List<Employee>> employeesPerSalary = new HashMap<>();
            List<Employee> entryValue = entry.getValue();
            for (Employee employee : entryValue) {
                mapEmployeesPerSalaryRange(employeesPerSalary, employee);
            }
            resultMap.put(entry.getKey(), employeesPerSalary);
        }
        //   System.out.println(resultMap);
        return resultMap;
    }

    private static void mapEmployeesPerSalaryRange(Map<SalaryRange,
            List<Employee>> employeesPerSalary, Employee employee) {
        SalaryRange rangeSalary;
        long salary = employee.getSalary();
        rangeSalary = getSalaryRange(salary);
        List<Employee> employeeSalary = employeesPerSalary.get(rangeSalary);
        if (employeeSalary == null) {
            employeeSalary = new ArrayList<>();
        }
        employeeSalary.add(employee);
        employeesPerSalary.put(rangeSalary, employeeSalary);
    }

    private static void mapEmployeesPerYear(Map<Integer, List<Employee>> employeesPerYear, Employee employee) {
        int year = employee.getEmploymentYear();
        List<Employee> employees = employeesPerYear.get(year);
        if (employees == null) { // not added to resultMap before
            employees = new ArrayList<>();
        }
        employees.add(employee);
        employeesPerYear.put(year, employees);
    }

    private static SalaryRange getSalaryRange(long salary) {
        SalaryRange rangeSalary;
        if (salary >= 1000000 && salary <= 5000000) {
            rangeSalary = SalaryRange.ONE_TO_FIVE;
        } else if (salary > 5000000 && salary <= 10000000) {
            rangeSalary = SalaryRange.FIVE_TO_TEN;
        } else {
            rangeSalary = SalaryRange.UP_TO_TEN;
        }
        return rangeSalary;
    }

    public static void showEmployeesDataLastFiveYear() throws SQLException {
        Map<Integer, Map<SalaryRange, List<Employee>>> resultMap =
                getEmployeesPerYearAndSalaryRange();
        int column = 1;
        printHeader();
        for (Map.Entry<Integer, Map<SalaryRange, List<Employee>>> entry : resultMap.entrySet()) {
            int checkForNextLine = 0;
            System.out.print("\t" + column + "\t\t\t" + entry.getKey() + "\t\t");
            for (Map.Entry<SalaryRange, List<Employee>> entryInnerMapBySalary :
                    entry.getValue().entrySet()) {
                if (checkForNextLine != 0) {
                    System.out.print("\t\t\t\t\t\t\t   ");
                }
                System.out.print("\t\t" + entryInnerMapBySalary.getKey() + "\t\t");
                int checkForInnerNextLine = 0;
                for (Employee employee : entryInnerMapBySalary.getValue()) {
                    if (checkForInnerNextLine != 0) {
                        System.out.print("\t\t\t\t\t\t\t\t\t\t\t\t\t");
                    }
                    System.out.println("\t" + employee.getPersonalId() + "\t\t\t"
                            + employee.getFullName());
                    checkForInnerNextLine++;
                }
                checkForNextLine++;
            }
            column++;

            System.out.println("------------------------------------------------------------" +
                    "------------------------------------");
        }
        System.out.println();
    }

    private static void printHeader() {
        System.out.println("------------------------------------------------------------" +
                "----------------------------------");
        System.out.println("| column |   yearEmployment    |        salary     |    personalId  " +
                "|       fullName       |");
        System.out.println("------------------------------------------------------------" +
                "------------------------------------");
    }
}


