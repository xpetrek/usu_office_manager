package com.example.demo.dto

import com.usu.ulm.office_manager.entities.EmployeeEntity

data class EmployeeDTO(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val address: String,
    val startDate: String,
    val officeId: Long?,
    val officeTableId: Long?
)

data class CreateEmployeeDTO(
    val firstName: String,
    val lastName: String,
    val address: String,
    val startDate: String,
)

data class EmployeeUpdateDTO(
    val firstName: String,
    val lastName: String,
    val address: String,
    val startDate: String,
    val officeId: Long?,
    val officeTableId: Long?
)

fun EmployeeEntity.toDTO() = EmployeeDTO(
    id = this.id,
    firstName = this.firstName,
    lastName = this.lastName,
    address = this.address,
    startDate = this.startDate,
    officeId = this.office?.id,
    officeTableId = this.officeTable?.id
)