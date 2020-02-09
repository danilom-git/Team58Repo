package team58.healthy.dto;

import team58.healthy.model.CheckupRequest;

import java.util.Date;

public class CheckupRequestDTO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private Long doctorId;
    private Long checkupTypeId;
    private Long clinicId;
    private Long patientId;

    public CheckupRequestDTO() { }

    public CheckupRequestDTO(CheckupRequest checkupRequest) {
        this.id = checkupRequest.getId();
        this.startDate = checkupRequest.getStartDate();
        this.endDate = checkupRequest.getEndDate();
        this.doctorId = checkupRequest.getDoctor().getId();
        this.checkupTypeId = checkupRequest.getCheckupType().getId();
        this.clinicId = checkupRequest.getClinic().getId();
        this.patientId = checkupRequest.getPatient().getId();
    }

    public CheckupRequestDTO(Date startDate, Date endDate, Long doctorId, Long checkupTypeId, Long clinicId, Long patientId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.doctorId = doctorId;
        this.checkupTypeId = checkupTypeId;
        this.clinicId = clinicId;
        this.patientId = patientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getCheckupTypeId() {
        return checkupTypeId;
    }

    public void setCheckupTypeId(Long checkupTypeId) {
        this.checkupTypeId = checkupTypeId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
