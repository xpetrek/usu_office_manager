package com.usu.ulm.office_manager.services

import com.example.demo.dto.OfficeTableDTO
import com.example.demo.dto.toDTO
import com.usu.ulm.office_manager.entities.OfficeTableEntity
import com.usu.ulm.office_manager.repositories.TableRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TableService(
    @Autowired private val tableRepository: TableRepository
) {

    fun findAll(): List<OfficeTableDTO> {
        return tableRepository.findAll().map { it.toDTO() }
    }

    fun findOne(id: Long): OfficeTableDTO? {
        return tableRepository.findById(id).orElse(null)?.toDTO()
    }

    fun create(table: OfficeTableEntity): OfficeTableDTO {
        val savedEntity = tableRepository.save(table)
        return savedEntity.toDTO()
    }

    fun update(id: Long, updatedTable: OfficeTableEntity): OfficeTableDTO? {
        return if (tableRepository.existsById(id)) {
            val savedEntity = tableRepository.save(updatedTable.copy(id = id))
            savedEntity.toDTO()
        } else {
            null
        }
    }

    fun delete(id: Long) {
        if (tableRepository.existsById(id)) {
            tableRepository.deleteById(id)
        }
    }

    fun exists(id: Long): Boolean = tableRepository.existsById(id)

}