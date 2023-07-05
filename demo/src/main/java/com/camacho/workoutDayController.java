import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/workoutDays")
public class WorkoutDayController {
    @Autowired
    private WorkoutDayRepository repository;

    @GetMapping
    public List<WorkoutDay> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public WorkoutDay add(@Valid @RequestBody WorkoutDay workoutDay) {
        return repository.save(workoutDay);
    }

    @PutMapping("/{id}")
    public WorkoutDay update(@PathVariable Long id, @Valid @RequestBody WorkoutDay newWorkoutDay) {
        return repository.findById(id)
                .map(workoutDay -> {
                    workoutDay.setName(newWorkoutDay.getName());
                    workoutDay.setDate(newWorkoutDay.getDate());
                    return repository.save(workoutDay);
                })
                .orElseGet(() -> {
                    newWorkoutDay.setId(id);
                    return repository.save(newWorkoutDay);
                });
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
