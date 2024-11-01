package com.usu.ulm.office_manager.repositories;

import com.usu.ulm.office_manager.entities.OfficeTableEntity
import org.springframework.data.jpa.repository.JpaRepository

interface TableRepository : JpaRepository<OfficeTableEntity, Long>
