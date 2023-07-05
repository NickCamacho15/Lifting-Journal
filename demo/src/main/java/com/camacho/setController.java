import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sets")
public class SetController {
    @Autowired
    private SetRepository repository;

    @GetMapping
    public List<Set> getAll() {
        return repository.findAll();
    }

    @GetMapping("/exercise/{exerciseId}")
    public List<Set> getByExerciseId(@PathVariable Long exerciseId) {
        return repository.findByExerciseId(exerciseId);
    }

    @PostMapping
    public Set add(@Valid @RequestBody Set set) {
        return repository.save(set);
    }

    @PutMapping("/{id}")
    public Set update(@PathVariable Long id, @Valid @RequestBody Set newSet) {
        return repository.findById(id)
                .map(set -> {
                    set.setWeight(newSet.getWeight());
                    set.setReps(newSet.getReps());
                    set.setExercise(newSet.getExercise());
                    return repository.save(set);
                })
                .orElseGet(() -> {
                    newSet.setId(id);
                    return repository.save(newSet);
                });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
