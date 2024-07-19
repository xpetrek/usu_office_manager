import jakarta.persistence.*

@Entity
@Table(name = "office")
data class Office(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val area: Number,
)