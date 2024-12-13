package com.usu.ulm.office_manager.controllers

import com.example.demo.dto.CreateEmployeeDTO
import com.example.demo.dto.EmployeeDTO
import com.example.demo.dto.EmployeeUpdateDTO
import com.example.demo.dto.toDTO
import com.usu.ulm.office_manager.entities.EmployeeEntity
import com.usu.ulm.office_manager.services.EmployeeService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@Tag(
    name = "Employees",
)
class EmployeeController(private val service: EmployeeService) {

    @GetMapping("/")
    fun findAll(): ResponseEntity<List<EmployeeDTO>> {
        return ResponseEntity.ok(service.findAll())
    }

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long): ResponseEntity<EmployeeDTO> {
        return service.findOne(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping("/")
    fun create(@RequestBody employee: CreateEmployeeDTO): ResponseEntity<EmployeeDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(employee))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody employeeUpdateDTO: EmployeeUpdateDTO): ResponseEntity<EmployeeDTO?> {
        return if (service.exists(id)) {
            val updatedEmployee = service.update(id, employeeUpdateDTO)
            ResponseEntity.ok(updatedEmployee?.toDTO())
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        return if (service.exists(id)) {
            service.delete(id)
            ResponseEntity.ok().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PatchMapping("/{employeeId}/attachToTable/{tableId}")
    fun attachToTable(@PathVariable employeeId: Long, @PathVariable tableId: Long): ResponseEntity<EmployeeDTO> {
        return service.attachEmployeeToTable(employeeId, tableId)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @PatchMapping("/{employeeId}/attachToOffice/{officeId}")
    fun attachToOffice(@PathVariable employeeId: Long, @PathVariable officeId: Long): ResponseEntity<EmployeeDTO> {
        return service.attachEmployeeToOffice(employeeId, officeId)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }
}