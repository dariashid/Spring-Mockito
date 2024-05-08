package com.pro.sky.employeesaccounting.service;

import exception.EmployeeAlreadyAddedException;
import exception.EmployeeNotFoundException;
import exception.InvalidInputException;
import model.Employee;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import service.EmployeeService;
import service.EmployeeServiceImpl;

import javax.naming.InvalidNameException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeServiceTest {

    private final EmployeeService service = new EmployeeServiceImpl();

    @Test
    public void shouldAddEmployee() {

        Employee employee = service.addEmployee("Вячеслав", "Смирнов", 70000, 1);

        assertEquals("Вячеслав", employee.getFirstName());
        assertEquals("Смирнов", employee.getLastName());
        assertEquals(70000, employee.getSalary());
        assertEquals(service.findEmployee("Вячеслав", "Смирнов"), employee);
    }

    @Test
    public void shouldFindEmployee() {
        service.addEmployee("Вячеслав", "Смирнов", 70000,1);

        Employee employee = service.findEmployee("Вячеслав", "Смирнов");
        assertEquals("Вячеслав", employee.getFirstName());
        assertEquals("Смирнов", employee.getLastName());
        assertEquals(70000, employee.getSalary());

    }

    @Test
    public void shouldRemoveEmployee() {
        service.addEmployee("Вячеслав", "Смирнов", 70000, 1);
        service.removeEmployee("Вячеслав", "Смирнов");

        assertThrows(EmployeeNotFoundException.class, () -> service.findEmployee("Вячеслав", "Смирнов"));

    }

    @Test
    public void shouldThrowExceptionDeletingNotExistingEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> service.findEmployee("Вячеслав", "Смирнов"));
    }

    @Test
    public void shouldReturnAllEmployees() {
        Employee addedEmployee = service.addEmployee("Вячеслав", "Смирнов", 70000, 1);
        Collection<Employee> employees = service.findAllEmployees();
        assertEquals(1, employees.size());

        MatcherAssert.assertThat(employees, Matchers.containsInAnyOrder(addedEmployee));

    }
    @Test
    public void shouldThrowExceptionWhenAddingExistingEmployee() {
        service.addEmployee("Вячеслав", "Смирнов", 70000,1);
        assertThrows(EmployeeAlreadyAddedException.class, () -> service.addEmployee("Вячеслав", "Смирнов",70000, 1));
        assertThrows(EmployeeAlreadyAddedException.class, () -> service.addEmployee("Вячеслав", "Смирнов", 7000, 1));
    }
    @Test
    public void shouldThrowExceptionWhenNameIsNotNumeric() {

        assertThrows(InvalidInputException.class, () -> service.findEmployee("1", "Смирнов"));

    }
    @Test
    public void shouldThrowExceptionWhenEmployeeNotExists() {

        assertThrows(EmployeeNotFoundException.class, () -> service.findEmployee("Вячеслав", "Смирнов"));

    }
}
