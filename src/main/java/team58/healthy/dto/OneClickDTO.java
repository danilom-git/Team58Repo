package team58.healthy.dto;

import team58.healthy.model.CheckupType;
import team58.healthy.model.Doctor;
import team58.healthy.model.Hall;
import team58.healthy.model.OneClick;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.Duration;
import java.util.Date;

public class OneClickDTO {

    private Long id;
    private Date startTime;
    private Date endTime;
    private int duration;
    private double price;

    private Long clinicId;
    private Long checkupTypeId;
    private Long hallId;
    private Long doctorId;


    public OneClickDTO() { }

    public OneClickDTO(OneClick oneClick) {
        this.id = oneClick.getId();
        this.startTime = oneClick.getStartTime();
        this.endTime = oneClick.getEndTime();
        this.duration = oneClick.getDuration();
        this.price = oneClick.getPrice();
        this.clinicId = oneClick.getClinic().getId();
        this.checkupTypeId = oneClick.getCheckupType().getId();
        this.hallId = oneClick.getHall().getId();
        this.doctorId = oneClick.getDoctor().getId();
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

    public int getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public Long getCheckupTypeId() {
        return checkupTypeId;
    }

    public Long getHallId() {
        return hallId;
    }

    public Long getDoctorId() {
        return doctorId;
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

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public void setCheckupTypeId(Long checkupTypeId) {
        this.checkupTypeId = checkupTypeId;
    }

    public void setHallId(Long hallId) {
        this.hallId = hallId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }
}
