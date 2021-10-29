package model;

public class Employee extends Person implements  Comparable<Employee>{
    private int employmentYear;
    private long salary;


    public Employee(int id, String fullName,String personalId ,int employmentYear, long salary) {
        super(id,fullName,personalId);
        this.employmentYear = employmentYear;
        this.salary = salary;
    }

    public Employee() {

    }

    public int getEmploymentYear() {
        return employmentYear;
    }

    public void setEmploymentYear(int employmentYear) {
        this.employmentYear = employmentYear;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                super.toString()+
                "employmentYear=" + employmentYear +
                ", salary=" + salary +
                '}';
    }

    @Override
    public int compareTo(Employee o) {
        if(this.employmentYear==o.employmentYear){
            return Long.compare(this.salary,o.salary);
        }else if (this.employmentYear<o.employmentYear){
            return 1;
        }
        return -1;
    }
}
