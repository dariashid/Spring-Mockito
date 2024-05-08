package com.pro.sky.employeesaccounting.service;

import model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.DepartmentServiceImpl;
import service.EmployeeService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @InjectMocks
    private DepartmentServiceImpl departmentService;
    @Mock
    private EmployeeService employeeService;

    private List<Employee> employees = List.of(
            new Employee("Вячеслав", "Смирнов", 70000, 1),
            new Employee("Владислав", "Петров", 50000, 1),
            new Employee("Дмитрий", "Иванов", 60000, 2));

    @Test
    public void shouldReturnEmployeeWithMaxSalary() {
        when(employeeService.findAllEmployees()).thenReturn(employees);

        Optional<Employee> employee = departmentService.findEmployeeMaxSalaryByDepartment(1);

        assertEquals("Вячеслав", employee.get().getFirstName());
        assertEquals("Смирнов", employee.get().getLastName());
    }

    @Test
    public void shouldReturnEmployeeWithMinSalary() {
        when(employeeService.findAllEmployees()).thenReturn(employees);

        Optional<Employee> employee = departmentService.findEmployeeMinSalaryByDepartment(1);

        assertEquals("Владислав", employee.get().getFirstName());
        assertEquals("Петров", employee.get().getLastName());

    }

    @Test
    public void shouldReturnAllEmployeeGroupByDepartment() {
        when(employeeService.findAllEmployees()).thenReturn(employees);
        Map<Integer, List<Employee>> result = departmentService.findAll();

        assertEquals(2, result.size());
        assertEquals(1, result.get(2).size());
        assertEquals(2, result.get(1).size());
        Assertions.assertThat(result.get(1)).hasSize(2).contains(employees.get(0), employees.get(1));
        Assertions.assertThat(result.get(2)).hasSize(1).contains(employees.get(2));

    }
}


