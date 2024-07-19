import org.springframework.data.jpa.repository.JpaRepository

interface OfficeRepository : JpaRepository<Office, Long>
