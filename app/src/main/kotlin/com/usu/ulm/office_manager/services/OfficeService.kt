package com.usu.ulm.office_manager.services

import com.example.demo.dto.OfficeDTO
import com.example.demo.dto.toDTO
import com.usu.ulm.office_manager.entities.OfficeEntity
import com.usu.ulm.office_manager.entities.OfficeTableEntity
import com.usu.ulm.office_manager.repositories.OfficeRepository
import com.usu.ulm.office_manager.repositories.TableRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OfficeService(
    @Autowired private val officeRepository: OfficeRepository,
    @Autowired private val tableRepository: TableRepository
) {

    fun findAll(): List<OfficeDTO> {
        return officeRepository.findAll().map { it.toDTO() }
    }

    fun findOne(id: Long): OfficeDTO? {
        return officeRepository.findById(id).orElse(null)?.toDTO()
    }

    fun create(office: OfficeEntity): OfficeDTO {
        val savedEntity = officeRepository.save(office)
        return savedEntity.toDTO()
    }

    fun update(id: Long, updatedOffice: OfficeEntity): OfficeDTO? {
        return if (officeRepository.existsById(id)) {
            val savedEntity = officeRepository.save(updatedOffice.copy(id = id))
            savedEntity.toDTO()
        } else {
            null
        }
    }

    fun delete(id: Long) {
        if (officeRepository.existsById(id)) {
            officeRepository.deleteById(id)
        }
    }

    fun exists(id: Long): Boolean = officeRepository.existsById(id)

    fun createOffice(office: OfficeEntity): OfficeEntity {
        return officeRepository.save(office)
    }

    fun getOfficeById(id: Long): OfficeEntity? {
        return officeRepository.findById(id).orElse(null)
    }

    fun updateOffice(id: Long, updatedOffice: OfficeEntity): OfficeEntity? {
        return if (officeRepository.existsById(id)) {
            officeRepository.save(updatedOffice)
        } else {
            null
        }
    }

    fun deleteOffice(id: Long) {
        if (officeRepository.existsById(id)) officeRepository.deleteById(id)
    }

    fun addTableToOffice(officeId: Long, table: OfficeTableEntity): OfficeTableEntity? {
        val office = officeRepository.findById(officeId).orElse(null) ?: return null
        val newTable = table.copy(office = office)
        return tableRepository.save(newTable)
    }
}