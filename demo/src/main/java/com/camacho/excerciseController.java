import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    @Autowired
    private ExerciseRepository repository;

    @GetMapping
    public List<Exercise> getAll() {
        return repository.findAll();
    }

    @GetMapping("/workoutDay/{workoutDayId}")
    public List<Exercise> getByWorkoutDayId(@PathVariable Long workoutDayId) {
        return repository.findByWorkoutDayId(workoutDayId);
    }

    @PostMapping
    public Exercise add(@Valid @RequestBody Exercise exercise) {
        return repository.save(exercise);
    }

    @PutMapping("/{id}")
    public Exercise update(@PathVariable Long id, @Valid @RequestBody Exercise newExercise) {
        return repository.findById(id)
                .map(exercise -> {
                    exercise.setName(newExercise.getName());
                    exercise.setWorkoutDay(newExercise.getWorkoutDay());
                    return repository.save(exercise);
                })
                .orElseGet(() -> {
                    newExercise.setId(id);
                    return repository.save(newExercise);
                });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
