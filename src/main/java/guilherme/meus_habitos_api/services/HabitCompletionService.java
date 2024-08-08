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
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;
    private final HabitRepository habitRepository;

    public HabitCompletionService(HabitCompletionRepository habitCompletionRepository, JwtTokenService jwtTokenService, UserRepository userRepository, HabitRepository habitRepository) {
        this.habitCompletionRepository = habitCompletionRepository;
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
        this.habitRepository = habitRepository;
    }

    public void save(CreateHabitCompletionDto createHabitCompletionDto, String authorization) throws UserNotFoundException {
        String token = authorization.replace("Bearer ", "");
        String subject = jwtTokenService.getSubjectFromToken(token);
        User user = userRepository.findByUsername(subject).orElseThrow(() -> new UserNotFoundException("User not found."));
        Habit habit = habitRepository.findById(createHabitCompletionDto.getHabitId()).orElseThrow(() -> new RuntimeException("habit not found"));
        HabitCompletion habitCompletion = new HabitCompletion();
        habitCompletion.setHabit(habit);
        habitCompletion.setUser(user);
        habitCompletion.setCheckedAt(createHabitCompletionDto.getCheckedAt());
        habitCompletion.setDone(createHabitCompletionDto.isDone());
        habitCompletionRepository.save(habitCompletion);
    }

    public List<HabitCompletionDto> findByUserAndHabit(String habitId, String authorization) throws UserNotFoundException, HabitNotFoundException {
        String token = authorization.replace("Bearer ", "");
        String subject = jwtTokenService.getSubjectFromToken(token);
        User user = userRepository.findByUsername(subject).orElseThrow(() -> new UserNotFoundException("User not found."));
        Habit habit = habitRepository.findById(habitId).orElseThrow(() -> new HabitNotFoundException("Habit not found."));
        return habitCompletionRepository.findByUserAndHabit(user, habit);
    }
}
