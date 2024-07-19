import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tables")
class TableController(private val repository: TableRepository) {

    @GetMapping
    fun findAll() = repository.findAll()

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long) = repository.findById(id)

    @PostMapping
    fun create(@RequestBody officeTable: OfficeTable) = repository.save(officeTable)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody officeTable: OfficeTable) {
        if (repository.existsById(id)) {
            repository.save(officeTable)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = repository.deleteById(id)
}
