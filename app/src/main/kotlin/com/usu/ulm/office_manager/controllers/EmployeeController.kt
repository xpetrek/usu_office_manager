package com.usu.ulm.office_manager.controllers;

import com.usu.ulm.office_manager.entities.EmployeeEntity
import com.usu.ulm.office_manager.repositories.EmployeeRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employees")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class EmployeeController(private val repository: EmployeeRepository) {
    @Operation(summary = "Get all employees in the application.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Successfully retrieved Employees")]
    )
    @GetMapping("/")
    fun findAll() = repository.findAll();

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long) = repository.findById(id)

    @PostMapping
    fun create(@RequestBody employee: EmployeeEntity) = repository.save(employee)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody employee: EmployeeEntity) {
        if (repository.existsById(id)) {
            repository.save(employee)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = repository.deleteById(id)
}
