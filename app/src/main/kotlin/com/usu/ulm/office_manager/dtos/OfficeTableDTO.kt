package com.example.demo.dto

import com.usu.ulm.office_manager.entities.OfficeTableEntity

data class OfficeTableDTO(
    val id: Long,
    val name: String,
    val utilizedArea: Long,
    val officeId: Long?,
    val employeeId: Long?
)

data class OfficeTableUpdateDTO(
    val name: String,
    val utilizedArea: Long,
    val officeId: Long?,
    val employeeId: Long?
)

fun OfficeTableEntity.toDTO() = OfficeTableDTO(
    id = this.id,
    name = this.name,
    utilizedArea = this.utilizedArea,
    officeId = this.office?.id,
    employeeId = this.employee?.id
)