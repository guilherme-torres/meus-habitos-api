package guilherme.meus_habitos_api.services;

import guilherme.meus_habitos_api.dto.CreateHabitCompletionDto;
import guilherme.meus_habitos_api.dto.HabitCompletionDto;
import guilherme.meus_habitos_api.entities.Habit;
import guilherme.meus_habitos_api.entities.HabitCompletion;
import guilherme.meus_habitos_api.entities.User;
import guilherme.meus_habitos_api.exceptions.HabitNotFoundException;
import guilherme.meus_habitos_api.exceptions.UserNotFoundException;
import guilherme.meus_habitos_api.repositories.HabitCompletionRepository;
import guilherme.meus_habitos_api.repositories.HabitRepository;
import guilherme.meus_habitos_api.repositories.UserRepository;
import guilherme.meus_habitos_api.security.JwtTokenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitCompletionService {
    private final HabitCompletionRepository habitCompletionRepository;
    private final HabitRepository habitRepository;

    public HabitCompletionService(HabitCompletionRepository habitCompletionRepository, HabitRepository habitRepository) {
        this.habitCompletionRepository = habitCompletionRepository;
        this.habitRepository = habitRepository;
    }

    public void save(CreateHabitCompletionDto createHabitCompletionDto) throws HabitNotFoundException {
        Habit habit = habitRepository.findById(createHabitCompletionDto.getHabitId()).orElseThrow(() -> new HabitNotFoundException("Habit not found."));
        HabitCompletion habitCompletion = new HabitCompletion();
        habitCompletion.setHabit(habit);
        habitCompletion.setCheckedAt(createHabitCompletionDto.getCheckedAt());
        habitCompletion.setDone(createHabitCompletionDto.isDone());
        habitCompletionRepository.save(habitCompletion);
    }

    public List<HabitCompletionDto> findByHabit(String habitId) throws HabitNotFoundException {
        Habit habit = habitRepository.findById(habitId).orElseThrow(() -> new HabitNotFoundException("Habit not found."));
        return habitCompletionRepository.findByHabit(habit);
    }
}
