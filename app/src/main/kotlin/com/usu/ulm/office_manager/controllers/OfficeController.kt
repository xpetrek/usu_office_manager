import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/offices")
class OfficeController(private val repository: OfficeRepository) {

    @GetMapping
    fun findAll() = repository.findAll()

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long) = repository.findById(id)

    @PostMapping
    fun create(@RequestBody office: Office) = repository.save(office)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody office: Office) {
        if (repository.existsById(id)) {
            repository.save(office)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = repository.deleteById(id)
}
