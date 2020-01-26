package pl.connectis.restaurant.RestaurantApp.dto;


import com.fasterxml.jackson.annotation.JsonProperty;



public class EmployeeDTO {


    private Long id;
    private String name;
    private String surname;
    private String personalIdentityNumber;
    private String job;
    private Long salary;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private EmployeeDTO superiorDTO;
    private Long superiorNumber;


    public EmployeeDTO(Long id,
                       String name,
                       String surname,
                       String personalIdentityNumber,
                       String job,
                       Long salary,
                       EmployeeDTO superiorDTO,
                       Long superiorNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.personalIdentityNumber = personalIdentityNumber;
        this.job = job;
        this.salary = salary;
        this.superiorDTO = superiorDTO;
        this.superiorNumber = superiorNumber;
    }

    public EmployeeDTO() {
    }

    public Long getSuperiorNumber() {
        return superiorNumber;
    }

    public void setSuperiorNumber(Long superiorNumber) {
        this.superiorNumber = superiorNumber;
    }

    public EmployeeDTO getSuperiorDTO() {
        return superiorDTO;
    }

    public void setSuperiorDTO(EmployeeDTO superiorDTO) {
        this.superiorDTO = superiorDTO;
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
