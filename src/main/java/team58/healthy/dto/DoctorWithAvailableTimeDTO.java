package team58.healthy.dto;

import javafx.util.Pair;

import java.util.Date;
import java.util.List;

public class DoctorWithAvailableTimeDTO {

    private Long id;
    private String name;
    private String lastName;
    private List<Pair<Date, Date>> availableTimes;

    public DoctorWithAvailableTimeDTO() {}

    public DoctorWithAvailableTimeDTO(DoctorDTO doctorDTO, List<Pair<Date, Date>> availableTimes) {
        this.id = doctorDTO.getId();
        this.name = doctorDTO.getName();
        this.lastName = doctorDTO.getLastName();
        this.availableTimes = availableTimes;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Pair<Date, Date>> getAvailableTimes() {
        return availableTimes;
    }
}
