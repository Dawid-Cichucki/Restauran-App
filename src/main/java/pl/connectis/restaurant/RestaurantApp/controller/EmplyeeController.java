package pl.connectis.restaurant.RestaurantApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.connectis.restaurant.RestaurantApp.dto.EmployeeDTO;
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

    @PostMapping("/add/owner")
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

    @PostMapping("/add/employee")
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

    @GetMapping("/get/{id}")
    public EmployeeDTO getEmployee(@PathVariable("id") Long id){
        Optional<Employee> employeeOptional = employeeService.getEmployee(id);
        return new EmployeeDTO(employeeOptional.get());
    }

    @PutMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @RequestParam Long salary){
        employeeService.updateEmplyee(id, salary);
        return "emloyee updated";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id){
        employeeService.removeEmployee(id);
        return "employee removed";
    }
}
