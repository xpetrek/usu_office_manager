package com.usu.ulm.office_manager.controllers;

import com.usu.ulm.office_manager.entities.OfficeEntity
import com.usu.ulm.office_manager.repositories.OfficeRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/offices")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
class OfficeController(private val repository: OfficeRepository) {

    @GetMapping("/")
    fun findAll() = repository.findAll()

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long) = repository.findById(id)

    @PostMapping
    fun create(@RequestBody office: OfficeEntity) = repository.save(office)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody office: OfficeEntity) {
        if (repository.existsById(id)) {
            repository.save(office)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = repository.deleteById(id)
}
