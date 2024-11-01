package com.usu.ulm.office_manager.entities;

import jakarta.annotation.Nullable
import jakarta.persistence.*

@Entity
@Table(name = "officeTable")
data class OfficeTableEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "office_table_id")
    val id: Long = 0,
    val name: String,
    val utilizedArea: Long,

    @ManyToOne
    @JoinColumn(name = "office_id")
    val office: OfficeEntity? = null,

    @OneToOne
    @JoinColumn(name = "employee_id")
    val employee: EmployeeEntity? = null
)
