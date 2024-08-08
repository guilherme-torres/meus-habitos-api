package guilherme.meus_habitos_api.controllers;

import guilherme.meus_habitos_api.dto.CreateHabitDto;
import guilherme.meus_habitos_api.dto.HabitDto;
import guilherme.meus_habitos_api.entities.Habit;
import guilherme.meus_habitos_api.exceptions.HabitNotFoundException;
import guilherme.meus_habitos_api.exceptions.UserNotFoundException;
import guilherme.meus_habitos_api.services.HabitService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitController {
    private final HabitService habitService;

    public HabitController(HabitService habitService) {
        this.habitService = habitService;
    }

    @PostMapping
    public ResponseEntity<HabitDto> save(@RequestBody CreateHabitDto body, @RequestHeader("Authorization") String authorization) {
        habitService.save(body, authorization);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<HabitDto> findByUser(@RequestHeader String authorization) throws UserNotFoundException {
        return habitService.findByUser(authorization);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitDto> findById(@PathVariable("id") String id) throws HabitNotFoundException {
        Habit habit = habitService.findById(id);
        HabitDto habitDto = new HabitDto(habit.getId(), habit.getDescription());
        return ResponseEntity.ok().body(habitDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HabitDto> delete(@PathVariable("id") String id) throws HabitNotFoundException {
        habitService.delete(id);
        return ResponseEntity.ok().build();
    }
}
