package com.usu.ulm.office_manager.controllers

import com.example.demo.dto.OfficeTableDTO
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
    fun create(@RequestBody officeTable: OfficeTableEntity): ResponseEntity<OfficeTableDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(officeTable))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody officeTable: OfficeTableEntity): ResponseEntity<OfficeTableDTO?> {
        return if (service.exists(id)) {
            ResponseEntity.ok(service.update(id, officeTable))
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
        val unusedTables = service.findUnusedTables()
        return ResponseEntity.ok(unusedTables)
    }

    @GetMapping("/unplacedUnused")
    fun getUnplacedUnusedTables(): ResponseEntity<List<OfficeTableDTO>> {
        val unusedTables = service.findUnusedTables()
        return ResponseEntity.ok(unusedTables)
    }
}