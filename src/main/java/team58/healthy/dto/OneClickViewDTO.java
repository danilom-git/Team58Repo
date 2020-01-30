package team58.healthy.dto;

import team58.healthy.model.OneClick;

import java.util.Date;

public class OneClickViewDTO {

    private Long id;
    private Date startTime;
    private Date endTime;
    private String duration;
    private double price;
    private Long clinicId;
    private String checkupType;
    private String hallNumber;
    private String doctorName;
    private String doctorLastName;

    public OneClickViewDTO() {
    }

    public OneClickViewDTO(OneClick oneClick) {
        this.id = oneClick.getId();
        this.startTime = oneClick.getStartTime();
        this.endTime = oneClick.getEndTime();
        this.duration = oneClick.getDuration();
        this.price = oneClick.getPrice();
        this.clinicId = oneClick.getClinic().getId();

        this.checkupType = oneClick.getCheckupType().getName();
        this.hallNumber = oneClick.getHall().getNumber();
        this.doctorName = oneClick.getDoctor().getName();
        this.doctorLastName = oneClick.getDoctor().getLastName();
    }

    public Long getId() {
        return id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public String getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public String getCheckupType() {
        return checkupType;
    }

    public String getHallNumber() {
        return hallNumber;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public void setCheckupType(String checkupType) {
        this.checkupType = checkupType;
    }

    public void setHallNumber(String hallNumber) {
        this.hallNumber = hallNumber;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }
}
