package com.usu.ulm.office_manager.services

import com.example.demo.dto.EmployeeDTO
import com.example.demo.dto.toDTO
import com.usu.ulm.office_manager.entities.EmployeeEntity
import com.usu.ulm.office_manager.repositories.EmployeeRepository
import com.usu.ulm.office_manager.repositories.OfficeTableRepository
import com.usu.ulm.office_manager.repositories.OfficeRepository
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

    fun create(employee: EmployeeEntity): EmployeeDTO {
        val savedEntity = employeeRepository.save(employee)
        return savedEntity.toDTO()
    }

    fun update(id: Long, updatedEmployee: EmployeeEntity): EmployeeDTO? {
        return if (employeeRepository.existsById(id)) {
            val savedEntity = employeeRepository.save(updatedEmployee.copy(id = id))
            savedEntity.toDTO()
        } else {
            null
        }
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

        if (employee.office.id != table.office?.id) {
            throw IllegalStateException("Cannot attach employee to a table in a different office.")
        }

        val updatedEmployee = employee.copy(
            officeTable = table,
            office = table.office
        )

        employeeRepository.save(updatedEmployee)

        val updatedTable = table.copy(employee = updatedEmployee)
        tableRepository.save(updatedTable)

        return updatedEmployee.toDTO()
    }

    fun attachEmployeeToOffice(employeeId: Long, officeId: Long): EmployeeDTO? {
        val employee = employeeRepository.findById(employeeId).orElse(null) ?: return null
        val office = officeRepository.findById(officeId).orElse(null) ?: return null

        var updatedEmployee = employee.copy(office = office)

        if (employee.officeTable != null && employee.officeTable.office?.id != office.id) {
            updatedEmployee = updatedEmployee.copy(officeTable = null)
        }

        employeeRepository.save(updatedEmployee)

        return updatedEmployee.toDTO()
    }
}