package com.usu.ulm.office_manager.controllers;

import com.usu.ulm.office_manager.entities.Employee
import com.usu.ulm.office_manager.repositories.EmployeeRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employees")
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
    fun create(@RequestBody employee: Employee) = repository.save(employee)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody employee: Employee) {
        if (repository.existsById(id)) {
            repository.save(employee)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = repository.deleteById(id)
}
