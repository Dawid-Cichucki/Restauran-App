package pl.connectis.restaurant.RestaurantApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.connectis.restaurant.RestaurantApp.model.Employee;
import pl.connectis.restaurant.RestaurantApp.repositories.EmployeeRepository;

import java.util.Optional;

import static pl.connectis.restaurant.RestaurantApp.validation.ValidationClass.*;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Long addBoss(String name, String surname, String pesel, String job, Long salary) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setPersonalIdentityNumber(pesel);
        employee.setJob(job);
        employee.setSalary(salary);
        employee.setSuperior(null);
        employee.setSuperiorNumber(null);

        validateEmployee(employee);

        employeeRepository.save(employee);

        return employee.getId();
    }

    public Long addEmployee(String name, String surname, String pesel, String job, Long salary, Long superiorId) {

        Employee superior = getEmployee(superiorId).get();

        Employee employee = new Employee();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setPersonalIdentityNumber(pesel);
        employee.setJob(job);
        employee.setSalary(salary);
        employee.setSuperiorNumber(superiorId);
        employee.setSuperior(superior);

        validateEmployee(employee);

        employeeRepository.save(employee);

        return employee.getId();
    }

    public void updateEmplyee(Long id, Long salary) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setSalary(salary);

            validateEmployee(employee);

            employeeRepository.save(employee);
            return;
        }
        throw new RuntimeException();
    }

    public void removeEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Optional<Employee> getEmployee(Long id) {
        return employeeRepository.findById(id);
    }

    private void validateEmployee(Employee employee) {
        if (isNullOrEmpty(employee.getName()) ||
                isNullOrEmpty(employee.getSurname()) ||
                isNullOrEmpty(employee.getJob()) ||
                isNullOrEmpty(employee.getPersonalIdentityNumber()) ||
                isNull(employee.getSalary())) {
            throw new RuntimeException();
        }
    }
}
