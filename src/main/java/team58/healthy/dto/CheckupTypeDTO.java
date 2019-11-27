package team58.healthy.dto;

import team58.healthy.model.CheckupType;

public class CheckupTypeDTO {
    private Long id;
    private String name;
    private boolean available;

    public CheckupTypeDTO() { }

    public CheckupTypeDTO(CheckupType checkupType) {
        this.id = checkupType.getId();
        this.name = checkupType.getName();
        this.available = checkupType.isAvailable();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() {
        return available;
    }
}
