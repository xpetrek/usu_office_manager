package com.usu.ulm.office_manager.repositories;

import com.usu.ulm.office_manager.entities.OfficeTable
import org.springframework.data.jpa.repository.JpaRepository

interface TableRepository : JpaRepository<OfficeTable, Long>
