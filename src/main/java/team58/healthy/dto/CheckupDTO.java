package team58.healthy.dto;

import team58.healthy.model.Checkup;

import java.util.Date;

public class CheckupDTO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private Long patientId;
    private Long doctorId;
    private Long checkupTypeId;
    private Long clinicId;
    private Long hallId;

    public CheckupDTO() { }

    public CheckupDTO(Checkup checkup) {
        this.id = checkup.getId();
        this.startDate = checkup.getStartDate();
        this.endDate = checkup.getEndDate();
        this.patientId = checkup.getPatient().getId();
        this.doctorId = checkup.getDoctor().getId();
        this.checkupTypeId = checkup.getCheckupType().getId();
        this.clinicId = checkup.getClinic().getId();
        this.hallId = checkup.getHall().getId();
    }

    public Long getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Long getPatientId() {
        return patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public Long getCheckupTypeId() {
        return checkupTypeId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public Long getHallId() {
        return hallId;
    }
}
