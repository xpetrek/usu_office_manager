package com.usu.ulm.office_manager.entities;

import com.example.demo.dto.OfficeTableDTO
import jakarta.annotation.Nullable
import jakarta.persistence.*

@Entity
@Table(name = "officeTable")
data class OfficeTableEntity(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "office_table_id")
    var id: Long = 0,
    var name: String,
    var utilizedArea: Long,

    @ManyToOne
    @JoinColumn(name = "office_id")
    var office: OfficeEntity? = null,

    @OneToOne
    @JoinColumn(name = "employee_id")
    var employee: EmployeeEntity? = null
)
