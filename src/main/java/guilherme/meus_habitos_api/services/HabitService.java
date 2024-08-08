package guilherme.meus_habitos_api.services;

import guilherme.meus_habitos_api.dto.CreateHabitDto;
import guilherme.meus_habitos_api.dto.HabitDto;
import guilherme.meus_habitos_api.entities.Habit;
import guilherme.meus_habitos_api.entities.User;
import guilherme.meus_habitos_api.exceptions.HabitNotFoundException;
import guilherme.meus_habitos_api.exceptions.UserNotFoundException;
import guilherme.meus_habitos_api.repositories.HabitRepository;
import guilherme.meus_habitos_api.repositories.UserRepository;
import guilherme.meus_habitos_api.security.JwtTokenService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitService {
    private final HabitRepository habitRepository;
    private final JwtTokenService jwtTokenService;
    private final UserRepository userRepository;

    public HabitService(HabitRepository habitRepository, JwtTokenService jwtTokenService, UserRepository userRepository) {
        this.habitRepository = habitRepository;
        this.jwtTokenService = jwtTokenService;
        this.userRepository = userRepository;
    }

    public void save(CreateHabitDto createHabitDto, String authorization) {
        String token = authorization.replace("Bearer ", "");
        String subject = jwtTokenService.getSubjectFromToken(token);
        User user = userRepository.findByUsername(subject).orElseThrow(() -> new RuntimeException("user not found"));
        Habit habit = new Habit();
        habit.setUser(user);
        habit.setDescription(createHabitDto.getDescription());
        habitRepository.save(habit);
    }

    public List<HabitDto> findByUser(String authorization) throws UserNotFoundException {
        String token = authorization.replace("Bearer ", "");
        String subject = jwtTokenService.getSubjectFromToken(token);
        User user = userRepository.findByUsername(subject).orElseThrow(() -> new UserNotFoundException("User not found."));
        return habitRepository.findByUser(user);
    }

    public Habit findById(String id) throws HabitNotFoundException {
        return habitRepository.findById(id).orElseThrow(() -> new HabitNotFoundException("Habit not found."));
    }

    public void delete(String id) throws HabitNotFoundException {
        Habit habit = habitRepository.findById(id).orElseThrow(() -> new HabitNotFoundException("Habit not found."));
        habitRepository.delete(habit);
    }
}
