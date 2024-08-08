package guilherme.meus_habitos_api.repositories;

import guilherme.meus_habitos_api.dto.HabitDto;
import guilherme.meus_habitos_api.entities.Habit;
import guilherme.meus_habitos_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HabitRepository extends JpaRepository<Habit, String> {
    @Query("SELECT new guilherme.meus_habitos_api.dto.HabitDto(h.id, h.description) from Habit h WHERE h.user = :user")
    List<HabitDto> findByUser(@Param("user") User user);
}
