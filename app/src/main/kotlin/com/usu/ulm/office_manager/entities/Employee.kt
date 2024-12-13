package com.usu.ulm.office_manager.entities;

import com.example.demo.dto.EmployeeDTO
import jakarta.annotation.Nullable
import jakarta.persistence.*

@Entity
@Table(name = "employee")
data class EmployeeEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    var id: Long = 0,
    var firstName: String,
    var lastName: String,
    var address: String,
    var startDate: String,

    @OneToOne
    @JoinColumn(name = "office_table_id")
    var officeTable: OfficeTableEntity? = null,

    @ManyToOne
    @JoinColumn(name = "office_id")
    var office: OfficeEntity? = null,
)
