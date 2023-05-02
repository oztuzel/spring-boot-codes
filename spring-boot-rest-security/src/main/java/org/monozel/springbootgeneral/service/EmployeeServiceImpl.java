package org.monozel.springbootgeneral.service;

import jakarta.transaction.Transactional;
import org.monozel.springbootgeneral.dao.EmployeeDAO;
import org.monozel.springbootgeneral.dao.EmployeeRepository;
import org.monozel.springbootgeneral.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepository employeeRepository;
    @Autowired
    public EmployeeServiceImpl (EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> result = employeeRepository.findById(theId);
        // Optinal<..> is different pattern for checking nulls.
        Employee theEmployee = null;

        if(result.isPresent()) {
            theEmployee = result.get();
        }else {
            throw new RuntimeException("Did not find employee id : " +theId);
        }
        return theEmployee;
    }

    // JpaRepository provides @Transactional functionality
//  @Transactional  
    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    // JpaRepository provides @Transactional functionality
//    @Transactional
    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}
