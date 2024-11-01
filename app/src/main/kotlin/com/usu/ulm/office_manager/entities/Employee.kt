package com.usu.ulm.office_manager.entities;

import jakarta.annotation.Nullable
import jakarta.persistence.*

@Entity
@Table(name = "employee")
data class EmployeeEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val address: String,
    val startDate: String,

    @OneToOne
    @JoinColumn(name = "office_table_id")
    val officeTable: OfficeTableEntity? = null,

    @OneToOne
    @JoinColumn(name = "office_id")
    val office: OfficeEntity
)
