package guilherme.meus_habitos_api.repositories;

import guilherme.meus_habitos_api.dto.HabitCompletionDto;
import guilherme.meus_habitos_api.entities.Habit;
import guilherme.meus_habitos_api.entities.HabitCompletion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HabitCompletionRepository extends JpaRepository<HabitCompletion, String> {
    @Query("SELECT new guilherme.meus_habitos_api.dto.HabitCompletionDto(h.id, h.checkedAt, h.done) FROM HabitCompletion h WHERE h.habit = :habit")
    List<HabitCompletionDto> findByHabit(@Param("habit") Habit habit);
}
