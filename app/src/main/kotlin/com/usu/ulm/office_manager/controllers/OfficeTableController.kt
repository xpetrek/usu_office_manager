package com.usu.ulm.office_manager.controllers

import com.example.demo.dto.CreateOfficeDTO
import com.example.demo.dto.OfficeTableDTO
import com.example.demo.dto.OfficeTableUpdateDTO
import com.example.demo.dto.toDTO
import com.usu.ulm.office_manager.entities.OfficeTableEntity
import com.usu.ulm.office_manager.services.TableService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tables")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@Tag(
    name = "Office table",
)
class OfficeTableController(private val service: TableService) {

    @GetMapping("/")
    fun findAll(): ResponseEntity<List<OfficeTableDTO>> {
        return ResponseEntity.ok(service.findAll())
    }

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long): ResponseEntity<OfficeTableDTO> {
        return service.findOne(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping("/")
    fun create(@RequestBody createOfficeDTO: CreateOfficeDTO): ResponseEntity<OfficeTableDTO> {
        val newOfficeTable = service.create(createOfficeDTO)
        return ResponseEntity.status(HttpStatus.CREATED).body(newOfficeTable)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @RequestBody officeTableUpdateDTO: OfficeTableUpdateDTO
    ): ResponseEntity<OfficeTableDTO?> {
        return if (service.exists(id)) {
            val updatedTable = service.update(id, officeTableUpdateDTO)
            ResponseEntity.ok(updatedTable?.toDTO())
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

    @GetMapping("/unused")
    fun getUnusedTables(): ResponseEntity<List<OfficeTableDTO>> {
        val unusedTables = service.findUnusedTables()
        return ResponseEntity.ok(unusedTables)
    }

    @GetMapping("/unplaced")
    fun getUnplacedTables(): ResponseEntity<List<OfficeTableDTO>> {
        val unusedTables = service.findUnplacedTables()
        return ResponseEntity.ok(unusedTables)
    }

    @GetMapping("/unplacedUnused")
    fun getUnplacedUnusedTables(): ResponseEntity<List<OfficeTableDTO>> {
        val unusedTables = service.findUnplacedUnusedTables()
        return ResponseEntity.ok(unusedTables)
    }
}