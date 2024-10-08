package guilherme.meus_habitos_api.dto;

public class HabitDto {
    private String id;
    private String description;

    public HabitDto() {}

    public HabitDto(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
