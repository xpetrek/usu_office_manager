package com.usu.ulm.office_manager.services

import com.example.demo.dto.EmployeeDTO
import com.example.demo.dto.toDTO
import com.usu.ulm.office_manager.entities.EmployeeEntity
import com.usu.ulm.office_manager.repositories.EmployeeRepository
import com.usu.ulm.office_manager.repositories.TableRepository
import com.usu.ulm.office_manager.repositories.OfficeRepository
import org.springframework.stereotype.Service

@Service
class EmployeeService(
    private val employeeRepository: EmployeeRepository,
    private val tableRepository: TableRepository,
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

        // Ensure the table is in the same office or the employee has no office
        if (employee.office != null && employee.office?.id != table.office?.id) {
            // Adding logging or error throwing
            throw IllegalStateException("Cannot attach employee to a table in a different office.")
        }

        // Update the employee's assigned table and, by association, their office if necessary
        val updatedEmployee = employee.copy(
            officeTable = table,
            office = table.office ?: employee.office
        )

        employeeRepository.save(updatedEmployee)

        // Update the table to reflect the employee assignment
        val updatedTable = table.copy(employee = updatedEmployee)
        tableRepository.save(updatedTable)

        return updatedEmployee.toDTO()
    }

    fun attachEmployeeToOffice(employeeId: Long, officeId: Long): EmployeeDTO? {
        val employee = employeeRepository.findById(employeeId).orElse(null) ?: return null
        val office = officeRepository.findById(officeId).orElse(null) ?: return null

        // Check if employee can be attached here (Can add more rules if needed)
        var updatedEmployee = employee.copy(office = office)

        // Remove employee from current table if it exists and doesn't belong to the new office
        if (employee.officeTable != null && employee.officeTable?.office?.id != office.id) {
            updatedEmployee = updatedEmployee.copy(officeTable = null)
        }

        employeeRepository.save(updatedEmployee)

        return updatedEmployee.toDTO()
    }
}