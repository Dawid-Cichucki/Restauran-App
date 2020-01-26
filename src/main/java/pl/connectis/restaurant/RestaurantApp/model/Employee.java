package pl.connectis.restaurant.RestaurantApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true, nullable = false)
    private String personalIdentityNumber;

    @Column(nullable = false)
    private String job;

    @Column(nullable = false)
    private Long salary;

    @ManyToOne
    private Employee superior;

    @Column
    private Long superiorNumber;


    public Employee( String name, String surname, String personalIdentityNumber, String job, Long salary, Employee superior) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
