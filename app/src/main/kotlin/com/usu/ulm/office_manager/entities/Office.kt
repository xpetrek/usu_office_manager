package com.usu.ulm.office_manager.entities;

import com.example.demo.dto.OfficeDTO
import jakarta.persistence.*

@Entity
@Table(name = "office")
data class OfficeEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "office_id")
    val id: Long = 0,
    val name: String,
    val area: Int,

    @OneToMany(mappedBy = "office")
    val tables: List<OfficeTableEntity>? = null,
)
