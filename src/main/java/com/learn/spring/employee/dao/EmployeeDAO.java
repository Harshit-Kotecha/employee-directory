package com.learn.spring.employee.dao;

import com.learn.spring.employee.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();

    Employee save(Employee employee);

    Employee   findById(int id);

    void deleteById(int id);
}
