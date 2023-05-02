package org.monozel.springbootgeneral.rest;

import org.monozel.springbootgeneral.dao.EmployeeDAO;
import org.monozel.springbootgeneral.entity.Employee;
import org.monozel.springbootgeneral.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    // inject employee dao
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController (EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    // /employee and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll () {
        return employeeService.findAll();
    }

    // get single employee  /employee/{employeeId}
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);
        if(theEmployee == null) {
            throw new RuntimeException("Employee id not found - " +employeeId);
        }

        return theEmployee;
    }

    // add mapping for POST     /employees - add new employee
    // posting json in request body
    @PostMapping("/employees")
    public Employee addEmployee (@RequestBody Employee theEmployee) {
        // new employee save if id 0, employee is updated if id != 0
        // they pass an id in JSON ... set id to 0
        // this is to force a save of new item ... instead of update
        theEmployee.setId(0);

        // .save method return dbEmployee, it has updated id from database
        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    // add mapping for PUT      /employees - update existing employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        // theEmployee.getId() != 0
        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }


    // add mapping for DELETE   /employees/{employeeId} - delete employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int id) {
        Employee tempEmployee = employeeService.findById(id);
        if(tempEmployee == null) {
            throw new RuntimeException("Employee id not found: " + id);
        }
        employeeService.deleteById(id);
        return "Deleted employee id: " + id;
    }


}
