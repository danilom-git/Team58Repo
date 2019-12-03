package team58.healthy.dto;

import team58.healthy.model.CheckupType;
import team58.healthy.model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorDTO {

    private Long id;
    private String name;
    private String lastName;
    private Long clinicId;
    private String clinicName;
    //    private float averageRating;
    private List<CheckupType> checkupTypes;

    public DoctorDTO() {

    }

    public DoctorDTO(Doctor doctor) {
        this.id  = doctor.getId();
        this.name = doctor.getName();
        this.lastName = doctor.getLastName();
        this.clinicId = doctor.getClinic().getId();
        this.clinicName = doctor.getClinic().getName();
        this.checkupTypes = new ArrayList<>(doctor.getCheckupTypes());
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

    public String getClinicName() {
        return clinicName;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public List<CheckupType> getCheckupTypes() {
        return checkupTypes;
    }
}
