package com.usu.ulm.office_manager.controllers;

import com.usu.ulm.office_manager.entities.OfficeTableEntity
import com.usu.ulm.office_manager.repositories.TableRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tables")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class TableController(private val repository: TableRepository) {

    @GetMapping("/")
    fun findAll() = repository.findAll()

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long) = repository.findById(id)

    @PostMapping
    fun create(@RequestBody officeTable: OfficeTableEntity) = repository.save(officeTable)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody officeTable: OfficeTableEntity) {
        if (repository.existsById(id)) {
            repository.save(officeTable)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = repository.deleteById(id)
}
