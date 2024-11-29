package com.usu.ulm.office_manager.controllers

import com.example.demo.dto.OfficeDTO
import com.example.demo.dto.OfficeTableDTO
import com.example.demo.dto.toDTO
import com.usu.ulm.office_manager.entities.OfficeEntity
import com.usu.ulm.office_manager.entities.OfficeTableEntity
import com.usu.ulm.office_manager.services.OfficeService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/offices")
@CrossOrigin(origins = ["*"], allowedHeaders = ["*"])
@Tag(
    name = "Office",
)
class OfficeController(private val service: OfficeService) {

    @GetMapping("/")
    fun findAll(): ResponseEntity<List<OfficeDTO>> {
        return ResponseEntity.ok(service.findAll())
    }

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long): ResponseEntity<OfficeDTO> {
        return service.findOne(id)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.notFound().build()
    }

    @PostMapping("/")
    fun create(@RequestBody office: OfficeEntity): ResponseEntity<OfficeDTO> {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(office))
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody office: OfficeEntity): ResponseEntity<OfficeDTO?> {
        return if (service.exists(id)) {
            ResponseEntity.ok(service.update(id, office))
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

    @PatchMapping("/{officeId}/updateTables")
    fun updateOfficeTables(
        @PathVariable officeId: Long,
        @RequestBody request: UpdateOfficeTablesRequest
    ): ResponseEntity<List<OfficeTableDTO>> {
        return try {
            val updatedTables = service.updateOfficeTables(officeId, request)
            ResponseEntity.status(HttpStatus.OK).body(updatedTables)
        } catch (e: IllegalStateException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(emptyList())
        }
    }
}

data class UpdateOfficeTablesRequest(
    val addedTables: List<Long>,
    val removedTables: List<Long>
)