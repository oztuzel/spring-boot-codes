package org.monozel.springbootgeneral.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.monozel.springbootgeneral.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

// using old version JPA method
@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl (EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("from Employee", Employee.class);
        // execute query and get result list
        List<Employee> employees = theQuery.getResultList();
        // return the results

        return employees;
    }


    // NOTE: @Transactional annotation in service layer not there.
    @Override
    public Employee findById(int theId) {
        //get Employee (1. param is class, 2. param is primary key)
        Employee theEmployee = entityManager.find(Employee.class,theId);
        return theEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        // save Employee
        Employee dbEmployee = entityManager.merge(theEmployee);
        // insert employee if id==0, update employee if id != 0
        // and merge method returns inserted or updated employee in db (include id)
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        // find employee by id
        Employee theEmployee = entityManager.find(Employee.class, theId);

        // remove employee
        entityManager.remove(theEmployee);
    }
}
