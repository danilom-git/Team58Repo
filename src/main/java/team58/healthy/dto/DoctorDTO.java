package team58.healthy.dto;

import team58.healthy.model.Checkup;
import team58.healthy.model.Doctor;

import java.util.HashSet;
import java.util.Set;

public class DoctorDTO {

    private Long id;
    private String name;
    private String lastName;
    private Long clinicId;
    private String clinicName;
//    private float averageRating;

    public DoctorDTO() {

    }

    public DoctorDTO(Doctor doctor) {
        this.id  = doctor.getId();
        this.name = doctor.getName();
        this.lastName = doctor.getLastName();
//        this.clinicId = doctor.getClinic().getId();
//        this.clinicName = doctor.getClinic().getName();
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
}
