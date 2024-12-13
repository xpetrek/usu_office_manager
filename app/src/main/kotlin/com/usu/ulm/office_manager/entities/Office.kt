package com.usu.ulm.office_manager.entities;

import com.example.demo.dto.OfficeDTO
import jakarta.persistence.*

@Entity
@Table(name = "office")
data class OfficeEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "office_id")
    var id: Long = 0,
    var name: String,
    var area: Long,

    @OneToMany(mappedBy = "office")
    var tables: List<OfficeTableEntity>? = null,
)
