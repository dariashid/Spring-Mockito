package com.pro.sky.employeesaccounting.service;

import model.Employee;
import exception.EmployeeAlreadyAddedException;
import exception.EmployeeNotFoundException;
import exception.InvalidInputException;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import service.EmployeeService;
import service.EmployeeServiceImpl;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeServiceTest {
    private EmployeeService service = new EmployeeServiceImpl();

    @Test
    public void shouldAddEmployee() {

        Employee employee = service.add("Вячеслав", "Смирнов", 70000);

        assertEquals("Вячеслав", employee.getFirstName());
        assertEquals("Смирнов", employee.getLastName());
        assertEquals(70000, employee.getSalary());
        assertEquals(service.findEmployee("Вячеслав", "Смирнов"), employee);
    }

    @Test
    public void shouldThrowExceptionWhenNameIsNotNumeric() {
        assertThrows(InvalidInputException.class, () -> service.add("Вячеслав", "Смирнов", 70000));
        assertThrows(InvalidInputException.class, () -> service.add("Вячеслав", "Смирнов", 70000));
    }

    @Test
    public void shouldThrowExceptionWhenAddingExistingEmployee() {
        service.add("Вячеслав", "Смирнов", 70000);
        assertThrows(EmployeeAlreadyAddedException.class, () -> service.add("Вячеслав", "Смирнов", 70000));

    }

    @Test
    public void shouldFindEmployee() {
        service.add("Вячеслав", "Смирнов", 70000);

        Employee employee = service.findEmployee("Вячеслав", "Смирнов");
        assertEquals("Вячеслав", employee.getFirstName());
        assertEquals("Смирнов", employee.getLastName());
        assertEquals(70000, employee.getSalary());

    }

    @Test
    public void shouldThrowExceptionWhenEmployeeNotExists() {

        assertThrows(EmployeeNotFoundException.class, () -> service.findEmployee("Вячеслав", "Смирнов"));

    }

    @Test
    public void shouldRemoveEmployee() {
        service.add("Вячеслав", "Смирнов", 70000);
        service.removeEmployee("Вячеслав", "Смирнов");

        assertThrows(EmployeeNotFoundException.class, () -> service.findEmployee("Вячеслав", "Смирнов"));

    }

    @Test
    public void shouldThrowExceptionDeletingNotExistingEmployee() {
        assertThrows(EmployeeNotFoundException.class, () -> service.removeEmployee("Вячеслав", "Смирнов"));
    }

    @Test
    public void shouldReturnAllEmployees() {
        Employee addedEmployee = service.add("Вячеслав", "Смирнов", 70000);
        Collection<Employee> employees = service.findAllEmployees();
        assertEquals(1, employees.size());
        assertEquals(addedEmployee, employees.stream().findFirst().get());

        MatcherAssert.assertThat(employees, Matchers.containsInAnyOrder(addedEmployee));

    }

}
