package com.usu.ulm.office_manager.entities;

import jakarta.persistence.*

@Entity
@Table(name = "officeTable")
data class OfficeTable(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val utilizedArea: Long,

    @ManyToOne
    val office: Office,
)
