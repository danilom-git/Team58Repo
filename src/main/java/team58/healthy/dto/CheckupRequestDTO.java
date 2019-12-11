package team58.healthy.dto;

import team58.healthy.model.CheckupRequest;

import java.util.Date;

public class CheckupRequestDTO {
    private Long id;

    private Date startDate;
    private Date endDate;
    private Long doctorId;
    private Long checkupTypeId;

    public CheckupRequestDTO() { }

    public CheckupRequestDTO(CheckupRequest checkupRequest) {
        this.id = checkupRequest.getId();
        this.startDate = checkupRequest.getStartDate();
        this.endDate = checkupRequest.getEndDate();
        this.doctorId = checkupRequest.getDoctor().getId();
        this.checkupTypeId = checkupRequest.getCheckupType().getId();
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

    public Long getDoctorId() {
        return doctorId;
    }

    public Long getCheckupTypeId() {
        return checkupTypeId;
    }
}
