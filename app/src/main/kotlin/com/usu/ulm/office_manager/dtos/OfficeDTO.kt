package com.example.demo.dto

import com.usu.ulm.office_manager.entities.OfficeEntity

data class OfficeDTO(
    val id: Long,
    val name: String,
    val area: Int,
    val tableIds: List<Long>
)

fun OfficeEntity.toDTO() = OfficeDTO(
    id = this.id,
    name = this.name,
    area = this.area,
    tableIds = this.tables?.map { it.id } ?: emptyList()
)