package guilherme.meus_habitos_api.dto;

import java.time.LocalDate;

public class HabitCompletionDto {
    private String id;
    private LocalDate checkedAt;
    private boolean done;

    public HabitCompletionDto() {}

    public HabitCompletionDto(String id, LocalDate checkedAt, boolean done) {
        this.id = id;
        this.checkedAt = checkedAt;
        this.done = done;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
