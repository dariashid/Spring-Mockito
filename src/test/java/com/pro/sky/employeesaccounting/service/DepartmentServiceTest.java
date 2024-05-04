package com.pro.sky.employeesaccounting.service;

import Model.Employee;
import exception.EmployeeNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import service.DepartmentService;
import service.DepartmentServiceImpl;
import service.EmployeeService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

public class DepartmentServiceTest {
    @InjectMocks
    private DepartmentService departmentService;
    @Mock
    private EmployeeService employeeService;

    private List<Employee> employees = List.of(new Employee("Вячеслав", "Смирнов", 70000, 1),
            new Employee("Владислав", "Петров", 60000, 2),
            new Employee("Дмитрий", "Иванов", 71000, 2));

    @Test
    public void shouldReturnEmployeeWithMaxSalary() {
        when(employeeService.findAllEmployees()).thenReturn(employees);

        Employee employee = departmentService.findEmployeeMaxSalaryByDepartment(2);

        assertEquals("Вячеслав", employee.getFirstName());
        assertEquals("Смирнов", employee.getLastName());
    }

    @Test
    public void shouldReturnEmployeeWithMinSalary() {
        when(employeeService.findAllEmployees()).thenReturn(employees);

        Employee employee = departmentService.findEmployeeMinSalaryByDepartment(2);

        assertEquals("Вячеслав", employee.getFirstName());
        assertEquals("Смирнов", employee.getLastName());
    }

    @Test
    public void shouldReturnAllEmployeeGroupeByDepartment() {
        when(employeeService.findAllEmployees()).thenReturn(employees);
        Map<Integer, List<Employee>> result = departmentService.allEmployeesByDepartment();

        assertEquals( 2, result.size());
        assertEquals( 1, result.get(2).size());
        assertEquals( 2, result.get(1).size());
        Assertions.assertThat(result.get(1)).hasSize(2).contains(employees.get(0), employees.get(1));
        Assertions.assertThat(result.get(2)).hasSize(1).contains(employees.get(2), employees.get(1));
    }
@Test
    public void shouldThrowException(){
       when(employeeService.findAllEmployees()).thenReturn(new ArrayList<>()) ;
       assertThrows(EmployeeNotFoundException.class, () -> departmentService.findEmployeeMaxSalaryByDepartment(1));

}
}


