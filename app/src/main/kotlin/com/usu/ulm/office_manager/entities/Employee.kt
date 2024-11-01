package com.usu.ulm.office_manager.entities;

import jakarta.annotation.Nullable
import jakarta.persistence.*

@Entity
@Table(name = "employee")
data class Employee(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val address: String,
    val startDate: String,

    @OneToOne
    val officeTable: OfficeTable? = null,

    @OneToOne
    val office: Office
)
