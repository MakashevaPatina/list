package pro.sky.Javalist;

import org.springframework.stereotype.Service;
import pro.sky.Javalist.exception.EmployeeAlreadyAddedException;
import pro.sky.Javalist.exception.EmployeeNotFoundException;
import pro.sky.Javalist.exception.EmployeeStorageFullException;

import java.util.*;

@Service
public class EmployeeService {
    private static final int maxEmployees = 10;
    private Map<String, Employee> employees = new HashMap<>();

    public EmployeeService() {
        addEmployee(new Employee("Tolstoy", "Leo"));
        addEmployee(new Employee("Dostoevsky", "Fyodor"));
        addEmployee(new Employee("Chekhov", "Anton"));
        addEmployee(new Employee("Hemingway", "Ernest"));
        addEmployee(new Employee("Orwell", "George"));
        addEmployee(new Employee("Atwood", "Margaret"));
        addEmployee(new Employee("Lee", "Harper"));
        addEmployee(new Employee("Murakami", "Haruki"));
        addEmployee(new Employee("Kafka", "Franz"));
    }

    public void addEmployee(Employee employee) {
        if (employees.size() >= maxEmployees) {
            throw new EmployeeStorageFullException("Превышен лимит количества сотрудников.");
        }
        if (employees.containsKey(employee.getFullname())) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен.");
        }
        employees.put(employee.getFullname(), employee);
    }

    public void removeEmployee(String firstName, String lastName) {
        Employee employee = findEmployee(firstName, lastName);
        employees.remove(employee.getFullname(), employee);
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullname())) {
            return employees.get(employee.getFullname());
        }
        throw new EmployeeNotFoundException("Сотрудник не найден.");
    }

    public Map<String, Employee> getAllEmployees() {
        return new HashMap<>(employees);
    }

}
