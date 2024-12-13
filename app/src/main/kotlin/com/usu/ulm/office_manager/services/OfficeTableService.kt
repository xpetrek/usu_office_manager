package com.usu.ulm.office_manager.services

import com.example.demo.dto.CreateOfficeDTO
import com.example.demo.dto.OfficeTableDTO
import com.example.demo.dto.OfficeTableUpdateDTO
import com.example.demo.dto.toDTO
import com.usu.ulm.office_manager.entities.OfficeTableEntity
import com.usu.ulm.office_manager.repositories.EmployeeRepository
import com.usu.ulm.office_manager.repositories.OfficeRepository
import com.usu.ulm.office_manager.repositories.OfficeTableRepository
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TableService(
    @Autowired private val tableRepository: OfficeTableRepository,
    @Autowired private val officeRepository: OfficeRepository,
    @Autowired private val employeeRepository: EmployeeRepository
) {

    fun findAll(): List<OfficeTableDTO> {
        return tableRepository.findAll().map { it.toDTO() }
    }

    fun findOne(id: Long): OfficeTableDTO? {
        return tableRepository.findById(id).orElse(null)?.toDTO()
    }

    fun create(createOfficeDTO: CreateOfficeDTO): OfficeTableDTO {
        val officeTableEntity = OfficeTableEntity(
            name = createOfficeDTO.name,
            utilizedArea = createOfficeDTO.area,
            office = null,
            employee = null
        )

        val savedEntity = tableRepository.save(officeTableEntity)
        return savedEntity.toDTO()
    }


    @Transactional
    fun update(id: Long, updateDTO: OfficeTableUpdateDTO): OfficeTableEntity? {
        val officeTable = tableRepository.findById(id).orElse(null) ?: return null

        officeTable.apply {
            name = updateDTO.name
            utilizedArea = updateDTO.utilizedArea
            office = updateDTO.officeId?.let { officeRepository.findById(it).orElse(null) }
            employee = updateDTO.employeeId?.let { employeeRepository.findById(it).orElse(null) }
        }

        return tableRepository.save(officeTable)
    }

    fun delete(id: Long) {
        if (tableRepository.existsById(id)) {
            tableRepository.deleteById(id)
        }
    }

    fun exists(id: Long): Boolean = tableRepository.existsById(id)

    fun findUnusedTables(): List<OfficeTableDTO> {
        return tableRepository.findUnusedTables()
            .map { it.toDTO() }
    }

    fun findUnplacedTables(): List<OfficeTableDTO> {
        return tableRepository.findUnplacedTables()
            .map { it.toDTO() }
    }

    fun findUnplacedUnusedTables(): List<OfficeTableDTO> {
        return tableRepository.findUnplacedUnusedTables()
            .map { it.toDTO() }
    }
}