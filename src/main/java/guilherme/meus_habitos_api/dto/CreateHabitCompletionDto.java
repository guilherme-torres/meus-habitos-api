package guilherme.meus_habitos_api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class CreateHabitCompletionDto {
    private String habitId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate checkedAt;

    private boolean done;

    public CreateHabitCompletionDto() {}

    public CreateHabitCompletionDto(String habitId, LocalDate checkedAt, boolean done) {
        this.habitId = habitId;
        this.checkedAt = checkedAt;
        this.done = done;
    }

    public String getHabitId() {
        return habitId;
    }

    public void setHabitId(String habitId) {
        this.habitId = habitId;
    }

    public LocalDate getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(LocalDate checkedAt) {
        this.checkedAt = checkedAt;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
