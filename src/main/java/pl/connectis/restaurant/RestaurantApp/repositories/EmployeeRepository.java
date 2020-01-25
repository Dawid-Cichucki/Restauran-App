package pl.connectis.restaurant.RestaurantApp.repositories;

import org.springframework.data.repository.CrudRepository;

import pl.connectis.restaurant.RestaurantApp.model.Employee;


public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
