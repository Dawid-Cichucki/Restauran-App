package pl.connectis.restaurant.RestaurantApp.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.connectis.restaurant.RestaurantApp.model.Employee;


public class EmployeeDTO {


    private Long id;
    private String name;
    private String surname;
    private String personalIdentityNumber;
    private String job;
    private Long salary;
    @JsonIgnore
    private Employee superior;
    private Long superiorNumber;


    public EmployeeDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.surname = employee.getSurname();
        this.personalIdentityNumber = employee.getPersonalIdentityNumber();
        this.job = employee.getJob();
        this.salary = employee.getSalary();
        this.superior = employee.getSuperior();
        this.superiorNumber = employee.getSuperiorNumber();
    }

    public EmployeeDTO() {
    }

    public Long getSuperiorNumber() {
        return superiorNumber;
    }

    public void setSuperiorNumber(Long superiorNumber) {
        this.superiorNumber = superiorNumber;
    }

    public Employee getSuperior() {
        return superior;
    }

    public void setSuperior(Employee superior) {
        this.superior = superior;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPersonalIdentityNumber() {
        return personalIdentityNumber;
    }

    public void setPersonalIdentityNumber(String personalIdentityNumber) {
        this.personalIdentityNumber = personalIdentityNumber;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}
