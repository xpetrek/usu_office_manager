import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employees")
class EmployeeController(private val repository: EmployeeRepository) {

    @GetMapping
    fun findAll() = repository.findAll()

    @GetMapping("/{id}")
    fun findOne(@PathVariable id: Long) = repository.findById(id)

    @PostMapping
    fun create(@RequestBody employee: Employee) = repository.save(employee)

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody employee: Employee) {
        if (repository.existsById(id)) {
            repository.save(employee)
        }
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = repository.deleteById(id)
}
