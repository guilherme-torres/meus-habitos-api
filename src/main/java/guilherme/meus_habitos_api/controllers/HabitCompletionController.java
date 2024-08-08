package guilherme.meus_habitos_api.controllers;

import guilherme.meus_habitos_api.dto.CreateHabitCompletionDto;
import guilherme.meus_habitos_api.dto.HabitCompletionDto;
import guilherme.meus_habitos_api.exceptions.HabitNotFoundException;
import guilherme.meus_habitos_api.exceptions.UserNotFoundException;
import guilherme.meus_habitos_api.services.HabitCompletionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habit-completions")
public class HabitCompletionController {
    private final HabitCompletionService habitCompletionService;

    public HabitCompletionController(HabitCompletionService habitCompletionService) {
        this.habitCompletionService = habitCompletionService;
    }

    @PostMapping
    public ResponseEntity<HabitCompletionDto> save(@RequestBody CreateHabitCompletionDto body) throws HabitNotFoundException {
        habitCompletionService.save(body);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<HabitCompletionDto>> findByHabit(@RequestParam("habitId") String habitId) throws HabitNotFoundException {
        return ResponseEntity.ok().body(habitCompletionService.findByHabit(habitId));
    }
}
