package com.usu.ulm.office_manager.repositories;

import com.usu.ulm.office_manager.entities.Employee
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<Employee, Long>
