package pl.connectis.restaurant.RestaurantApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String surname;
    private String personalIdentityNumber;
    private String job;
    private Long salary;
    @JsonIgnore
    @ManyToOne
    private Employee superior;
    private Long superiorNumber;


    public Employee(Long id, String name, String surname, String personalIdentityNumber, String job, Long salary, Employee superior) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.personalIdentityNumber = personalIdentityNumber;
        this.job = job;
        this.salary = salary;
        this.superior = superior;
    }

    public Employee() {
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
