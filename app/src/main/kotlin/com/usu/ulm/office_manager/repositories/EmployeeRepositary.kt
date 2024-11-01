package com.usu.ulm.office_manager.repositories;

import com.usu.ulm.office_manager.entities.EmployeeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRepository : JpaRepository<EmployeeEntity, Long>
