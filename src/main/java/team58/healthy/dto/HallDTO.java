package team58.healthy.dto;

import team58.healthy.model.Hall;

public class HallDTO {
    private Long id;
    private String name;

    public HallDTO()
    {

    }

    public HallDTO(Hall hall)
    {
        this.id = hall.getId();
        this.name = hall.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
