import org.springframework.data.jpa.repository.JpaRepository

interface TableRepository : JpaRepository<OfficeTable, Long>
