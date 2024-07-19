import jakarta.persistence.*

@Entity
@Table
data class Employee(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val address: String,
    val startDate: String,
    @OneToOne val officeTable: OfficeTable
)
