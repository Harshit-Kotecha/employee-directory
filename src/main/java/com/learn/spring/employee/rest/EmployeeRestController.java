package com.learn.spring.employee.rest;

import com.learn.spring.employee.service.EmployeeService;
import com.learn.spring.employee.entity.Employee;
import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    @GetMapping("/employee/{employeeId}")
    public Employee getEmployee(@PathVariable(name = "employeeId") int id) {
        Employee employee = employeeService.findById(id);

        if(employee == null) {
            throw new RuntimeException("Employee not found");
        }

        return employee;
    }

    @PostMapping("/employee")
    public Employee add(@RequestBody Employee employee) {
        if(employee == null) {
            throw new RuntimeException("Null data received");
        }
        employee.setId(0);

        return employeeService.save(employee);
    }

    @PutMapping("/employee")
    public Employee update(@RequestBody Employee employee) {
        if(employee == null) {
            throw new RuntimeException("Null data received");
        }

        return employeeService.save(employee);
    }

    @DeleteMapping("/employee/{id}")
    public String deleteById(@PathVariable int id) {
        Employee employee  = employeeService.findById(id);

        if(employee == null) {
            throw new RuntimeException("Employee not found");
        }

        employeeService.deleteById(id);

        return "Employee deleted at id = " + id;
    }
}
