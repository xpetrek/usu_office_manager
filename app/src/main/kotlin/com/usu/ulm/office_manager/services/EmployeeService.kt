package com.usu.ulm.office_manager.services

import com.example.demo.dto.CreateEmployeeDTO
import com.example.demo.dto.EmployeeDTO
import com.example.demo.dto.EmployeeUpdateDTO
import com.example.demo.dto.toDTO
import com.usu.ulm.office_manager.entities.EmployeeEntity
import com.usu.ulm.office_manager.entities.OfficeEntity
import com.usu.ulm.office_manager.repositories.EmployeeRepository
import com.usu.ulm.office_manager.repositories.OfficeTableRepository
import com.usu.ulm.office_manager.repositories.OfficeRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository,
    private val tableRepository: OfficeTableRepository,
    private val officeRepository: OfficeRepository
) {

    fun findAll(): List<EmployeeDTO> {
        return employeeRepository.findAll().map { it.toDTO() }
    }

    fun findOne(id: Long): EmployeeDTO? {
        return employeeRepository.findById(id).orElse(null)?.toDTO()
    }

    fun create(employee: CreateEmployeeDTO): EmployeeDTO {
        val employeeEntity = EmployeeEntity(
            firstName = employee.firstName,
            lastName = employee.lastName,
            address = employee.address,
            startDate = employee.startDate,
        )
        val savedEntity = employeeRepository.save(employeeEntity)
        return savedEntity.toDTO()
    }

    @Transactional
    fun update(id: Long, updateDTO: EmployeeUpdateDTO): EmployeeEntity? {
        val employee = employeeRepository.findById(id).orElse(null) ?: return null

        updateDTO.officeId?.let { }
        employee.apply {
            firstName = updateDTO.firstName
            lastName = updateDTO.lastName
            address = updateDTO.address
            startDate = updateDTO.startDate
            office = updateDTO.officeId?.let { officeRepository.findById(it).orElse(null) }
            officeTable = updateDTO.officeTableId?.let { tableRepository.findById(it).orElse(null) }
        }

        return employeeRepository.save(employee)
    }

    fun delete(id: Long) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id)
        }
    }

    fun exists(id: Long): Boolean = employeeRepository.existsById(id)

    fun attachEmployeeToTable(employeeId: Long, tableId: Long): EmployeeDTO? {
        val employee = employeeRepository.findById(employeeId).orElse(null) ?: return null
        val table = tableRepository.findById(tableId).orElse(null) ?: return null

        if (employee.office?.id != table.office?.id) {
            throw IllegalStateException("Cannot attach employee to a table in a different office.")
        }

        employee.apply {
            this.officeTable = table
        }

        val updatedEmployee = employeeRepository.save(employee)
        return updatedEmployee.toDTO()
    }

    fun attachEmployeeToOffice(employeeId: Long, officeId: Long): EmployeeDTO? {
        val employee = employeeRepository.findById(employeeId).orElse(null) ?: return null
        val office = officeRepository.findById(officeId).orElse(null) ?: return null

        employee.apply {
            this.office = office
        }

        val updatedEmployee = employeeRepository.save(employee)
        return updatedEmployee.toDTO()
    }
}