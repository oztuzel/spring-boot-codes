package org.monozel.springbootgeneral.dao;

import org.monozel.springbootgeneral.entity.Employee;

import java.util.List;

// using old version JPA method
public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int theId);
    Employee save (Employee theEmployee);
    void deleteById(int theId);
}
