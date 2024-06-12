package pro.sky.Javalist;

import org.springframework.web.bind.annotation.*;
import pro.sky.Javalist.exception.EmployeeAlreadyAddedException;
import pro.sky.Javalist.exception.EmployeeNotFoundException;
import pro.sky.Javalist.exception.EmployeeStorageFullException;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
        employeeService.addEmployee(employee);
        return employee;
    }

    @GetMapping(path = "/remove")
    public Employee removeEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee employee = new Employee(firstName, lastName);
        employeeService.removeEmployee(firstName, lastName);
        return employee;
    }

    @GetMapping(path = "/find")
    public Employee findEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

}
