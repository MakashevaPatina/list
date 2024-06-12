package pro.sky.Javalist;

import org.springframework.stereotype.Service;
import pro.sky.Javalist.exception.EmployeeAlreadyAddedException;
import pro.sky.Javalist.exception.EmployeeNotFoundException;
import pro.sky.Javalist.exception.EmployeeStorageFullException;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    private static final int maxEmployees = 10;
    private List<Employee> employees = new ArrayList<>();
    public EmployeeService() {
        // Добавляем 9 предопределенных сотрудников
        employees.add(new Employee("Tolstoy", "Leo"));
        employees.add(new Employee("Dostoevsky", "Fyodor"));
        employees.add(new Employee("Chekhov", "Anton"));
        employees.add(new Employee("Hemingway", "Ernest"));
        employees.add(new Employee("Orwell", "George"));
        employees.add(new Employee("Atwood", "Margaret"));
        employees.add(new Employee("Lee", "Harper"));
        employees.add(new Employee("Murakami", "Haruki"));
        employees.add(new Employee("Kafka", "Franz"));
    }
    public void addEmployee(Employee employee) {
        if (employees.size() >= maxEmployees) {
            throw new EmployeeStorageFullException("Превышен лимит количества сотрудников.");
        }
        if (employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен.");
        }
        employees.add(employee);
    }

    public void removeEmployee(String firstName, String lastName) {
        Employee employee = findEmployee(firstName, lastName);
        employees.remove(employee);
    }

    public Employee findEmployee(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getLastName().equals(lastName) && employee.getFirstName().equals(firstName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не найден.");

    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

}
