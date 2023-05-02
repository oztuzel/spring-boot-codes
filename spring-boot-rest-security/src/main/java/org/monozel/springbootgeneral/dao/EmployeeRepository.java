package org.monozel.springbootgeneral.dao;

import org.monozel.springbootgeneral.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// new method for DAO, using Spring Data JPA
// extends JpaRepository<EntityType, PrimaryKey>
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // no need to write implementation methods
    // visit Spring Data JPA for provided methods
}
