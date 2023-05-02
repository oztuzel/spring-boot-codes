package org.monozel.springbootgeneral.dao;

import org.monozel.springbootgeneral.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// new method for DAO, using Spring Data JPA
// extends JpaRepository<EntityType, PrimaryKey>
@RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    // no need to write implementation methods
    // visit Spring Data JPA for provided methods
}
