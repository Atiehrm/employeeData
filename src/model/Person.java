package model;

public class Person {
    private int id;
    private String fullName;
    private String personalId;

    public Person(String fullName, String personalId) {
        this.fullName = fullName;
        this.personalId = personalId;
    }

    public Person(int id, String fullName, String personalId) {
        this.id=id;
        this.fullName = fullName;
        this.personalId = personalId;
    }

    public Person() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", personalId='" + personalId + '\'' +
                '}';
    }
}
