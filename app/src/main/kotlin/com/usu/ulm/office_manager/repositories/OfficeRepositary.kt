package com.usu.ulm.office_manager.repositories;

import com.usu.ulm.office_manager.entities.Office
import org.springframework.data.jpa.repository.JpaRepository

interface OfficeRepository : JpaRepository<Office, Long>
