package team58.healthy.dto;

import team58.healthy.model.AbsenceRequest;

import java.util.Date;

public class AbsenceRequestViewDTO {
    private Long id;
    private Long doctorId;
    private Long clinicId;
    private Date startDate;
    private Date endDate;
    private String type;
    private String doctorName;
    private String doctorLastName;

    public AbsenceRequestViewDTO() {
    }

    public AbsenceRequestViewDTO(AbsenceRequest request){
        this.id = request.getId();
        this.doctorId = request.getDoctor().getId();
        this.clinicId = request.getClinic().getId();
        this.startDate = request.getStartDate();
        this.endDate = request.getEndDate();
        this.type = request.getType();
        this.doctorName = request.getDoctor().getName();
        this.doctorLastName = request.getDoctor().getLastName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }
}
