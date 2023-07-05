import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface exerciseRepo extends JpaRepository<Exercise, Long> {
    List<Exercise> findByWorkoutDayId(Long workoutDayId);
}
