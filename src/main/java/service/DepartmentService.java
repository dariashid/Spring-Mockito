package service;

import Model.Employee;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface DepartmentService {
    Optional<Employee> findEmployeeMinSalaryByDepartment(int department);

    Optional<Employee> findEmployeeMaxSalaryByDepartment(int department);

    List<Employee> allEmployeesByDepartment(int department);

    Map<Integer, List<Employee>> findAll();
}
