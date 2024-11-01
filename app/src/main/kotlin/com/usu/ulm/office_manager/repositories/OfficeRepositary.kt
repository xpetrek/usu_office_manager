package com.usu.ulm.office_manager.repositories;

import com.usu.ulm.office_manager.entities.OfficeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface OfficeRepository : JpaRepository<OfficeEntity, Long>
