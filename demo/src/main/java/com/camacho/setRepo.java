import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface setRepo extends JpaRepository<Set, Long> {
    List<Set> findByExerciseId(Long exerciseId);
}
