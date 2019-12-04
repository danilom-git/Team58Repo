package team58.healthy.dto;

import team58.healthy.model.Hall;

public class HallDTO {
    private Long id;
    private String name;
    private String number;

    public HallDTO()
    {

    }

    public HallDTO(Hall hall)
    {
        this.id = hall.getId();
        this.name = hall.getName();
        this.number = hall.getNumber();
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
