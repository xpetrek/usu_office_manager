package com.example.demo.dto

data class EmployeeDTO(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val address: String,
    val startDate: String,
    val officeTableId: Long?
)
