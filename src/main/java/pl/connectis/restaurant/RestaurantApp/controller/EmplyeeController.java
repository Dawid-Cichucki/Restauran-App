package pl.connectis.restaurant.RestaurantApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.RestaurantApp.dto.EmployeeDTO;
import pl.connectis.restaurant.RestaurantApp.exception.NotFoundException;
import pl.connectis.restaurant.RestaurantApp.model.Employee;
import pl.connectis.restaurant.RestaurantApp.service.EmployeeService;

import java.util.Optional;

@RestController
@RequestMapping("/staff")
public class EmplyeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmplyeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/owner")
    public Long addBoss(@RequestBody EmployeeDTO employeeDTO) {
        Long getId = employeeService.addBoss(
                employeeDTO.getName(),
                employeeDTO.getSurname(),
                employeeDTO.getPersonalIdentityNumber(),
                employeeDTO.getJob(),
                employeeDTO.getSalary()
        );
        return getId;
    }

    @PostMapping("/employee")
    public Long addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Long getId = employeeService.addEmployee(
                employeeDTO.getName(),
                employeeDTO.getSurname(),
                employeeDTO.getPersonalIdentityNumber(),
                employeeDTO.getJob(),
                employeeDTO.getSalary(),
                employeeDTO.getSuperiorNumber()
        );
        return getId;
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployee(@PathVariable("id") Long id) {
        Optional<Employee> employeeOptional = employeeService.getEmployee(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            EmployeeDTO employeeDTO = employeeService.toDTO(employee);
            return employeeDTO;
        }
        throw new NotFoundException();
    }

    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @RequestParam Long salary) {
        Optional<Employee> employeeOptional = employeeService.getEmployee(id);
        if (employeeOptional.isPresent()) {
            employeeService.updateEmplyee(id, salary);
            return "emloyee updated";
        }
        throw new NotFoundException();
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        Optional<Employee> employeeOptional = employeeService.getEmployee(id);
        if (employeeOptional.isPresent()) {
            employeeService.removeEmployee(id);
            return "employee removed";
        }
        throw new NotFoundException();
    }
}
