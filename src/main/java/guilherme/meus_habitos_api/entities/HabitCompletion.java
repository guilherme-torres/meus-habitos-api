package guilherme.meus_habitos_api.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "habit_completion")
public class HabitCompletion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;

    @Column(name = "checked_at", nullable = false)
    private LocalDate checkedAt;

    @Column(name = "done", nullable = false)
    private boolean done;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Habit getHabit() {
        return habit;
    }

    public void setHabit(Habit habit) {
        this.habit = habit;
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
