package team58.healthy.dto;

import team58.healthy.model.Absence;
import team58.healthy.model.AbsenceRequest;

import java.util.Date;

public class AbsenceRequestDTO {
    private Long id;
    private Long doctorId;
    private Long clinicId;
    private Date startDate;
    private Date endDate;
    private String type;
    private Boolean answered;

    public AbsenceRequestDTO() {
    }

    public AbsenceRequestDTO(AbsenceRequest request){
        this.id = request.getId();
        this.doctorId = request.getDoctor().getId();
        this.clinicId = request.getClinic().getId();
        this.startDate = request.getStartDate();
        this.endDate = request.getEndDate();
        this.answered= request.getAnswered();
        this.type = request.getType();
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

    public Boolean getAnswered() {
        return answered;
    }

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
