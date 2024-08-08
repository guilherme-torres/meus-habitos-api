package guilherme.meus_habitos_api.dto;

public class CreateHabitDto {
    private String description;

    public CreateHabitDto() {}

    public CreateHabitDto(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
