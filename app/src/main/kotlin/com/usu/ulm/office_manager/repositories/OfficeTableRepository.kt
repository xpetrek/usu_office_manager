package com.usu.ulm.office_manager.repositories;

import com.usu.ulm.office_manager.entities.OfficeTableEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OfficeTableRepository : JpaRepository<OfficeTableEntity, Long> {

    @Query("SELECT t FROM OfficeTableEntity t WHERE t.office IS NULL")
    fun findUnusedTables(): List<OfficeTableEntity>

    @Query("SELECT t FROM OfficeTableEntity t WHERE t.employee IS NULL")
    fun findUnplacedTables(): List<OfficeTableEntity>

    @Query("SELECT t FROM OfficeTableEntity t WHERE t.office IS NULL AND t.employee IS NULL")
    fun findUnplacedUnusedTables(): List<OfficeTableEntity>
}
