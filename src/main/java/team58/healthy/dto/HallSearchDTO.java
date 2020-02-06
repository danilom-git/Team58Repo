package team58.healthy.dto;

import team58.healthy.model.Checkup;
import team58.healthy.model.Hall;

import java.util.List;

public class HallSearchDTO {
    private Long id;
    private String name;
    private String number;
    private Long clinicId;
    private List<CheckupDTO> checkups;

    public HallSearchDTO() {
    }

    public HallSearchDTO(Hall hall,List<CheckupDTO> checkups)
    {
        this.id = hall.getId();
        this.name = hall.getName();
        this.number = hall.getNumber();
        this.clinicId = hall.getClinic().getId();
        this.checkups = checkups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public List<CheckupDTO> getCheckups() {
        return checkups;
    }

    public void setCheckups(List<CheckupDTO> checkups) {
        this.checkups = checkups;
    }
}
