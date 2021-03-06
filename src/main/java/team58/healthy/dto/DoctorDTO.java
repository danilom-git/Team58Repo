package team58.healthy.dto;

import team58.healthy.model.Checkup;
import team58.healthy.model.CheckupType;
import team58.healthy.model.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DoctorDTO {

    private Long id;
    private String name;
    private String lastName;
    private int workingTime;
    private double rating;
    private String email;
    private Long clinicId;
    private String clinicName;
    private Boolean firstPasswordChanged;
    //    private float averageRating;
    private List<Long> checkupTypeIds;
    private List<Long> checkupIds;

    public DoctorDTO() {
        checkupIds = new ArrayList<Long>();
        checkupTypeIds = new ArrayList<Long>();
    }

    public DoctorDTO(Doctor doctor) {
        this.firstPasswordChanged = doctor.getFirstPasswordChanged();
        this.id = doctor.getId();
        this.name = doctor.getName();
        this.rating = doctor.getRating();
        this.lastName = doctor.getLastName();
        this.workingTime = doctor.getWorkingTime();
        this.email = doctor.getEmail();
        if (doctor.getClinic() != null) {
            this.clinicId = doctor.getClinic().getId();
            this.clinicName = doctor.getClinic().getName();
        }

        if (doctor.getCheckupTypes() != null && doctor.getCheckups() != null) {
            Set<CheckupType> checkupTypes = doctor.getCheckupTypes();
            this.checkupTypeIds = new ArrayList<>();
            for (CheckupType checkupType : checkupTypes)
                checkupTypeIds.add(checkupType.getId());

            Set<Checkup> checkups = doctor.getCheckups();
            this.checkupIds = new ArrayList<>();
            for (Checkup checkup : checkups)
                checkupIds.add(checkup.getId());
        }


    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public List<Long> getCheckupTypeIds() {
        return checkupTypeIds;
    }

    public List<Long> getCheckupIds() {
        return checkupIds;
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setWorkingTime(int workingTime) {
        this.workingTime = workingTime;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public Boolean getFirstPasswordChanged() {
        return firstPasswordChanged;
    }

    public void setFirstPasswordChanged(Boolean firstPasswordChanged) {
        this.firstPasswordChanged = firstPasswordChanged;
    }

    public void setCheckupTypeIds(List<Long> checkupTypeIds) {
        this.checkupTypeIds = checkupTypeIds;
    }

    public void setCheckupIds(List<Long> checkupIds) {
        this.checkupIds = checkupIds;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}
