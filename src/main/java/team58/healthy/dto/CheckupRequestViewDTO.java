package team58.healthy.dto;

import team58.healthy.model.CheckupRequest;

import java.util.Date;

public class CheckupRequestViewDTO {
    private Long id;
    private Date startDate;
    private Date endDate;
    private Long doctorId;
    private Long checkupTypeId;
    private Long clinicId;
    private String doctorName;
    private String doctorLastName;

    public CheckupRequestViewDTO() {
    }

    public CheckupRequestViewDTO(CheckupRequest checkupRequest) {
        this.id = checkupRequest.getId();
        this.startDate = checkupRequest.getStartDate();
        this.endDate = checkupRequest.getEndDate();
        this.doctorId = checkupRequest.getDoctor().getId();
        this.checkupTypeId = checkupRequest.getCheckupType().getId();
        this.clinicId = checkupRequest.getClinic().getId();
        this.doctorName = checkupRequest.getDoctor().getName();
        this.doctorLastName = checkupRequest.getDoctor().getLastName();
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
}
