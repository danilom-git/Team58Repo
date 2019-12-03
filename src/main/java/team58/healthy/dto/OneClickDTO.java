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
    private Duration duration;
    private CheckupType checkupType;
    private String hallName;
    private String doctorFullName;
    private double price;

    public OneClickDTO() { }

    public OneClickDTO(OneClick oneClick) {
        this.id = oneClick.getId();
        this.startTime = oneClick.getStartTime();
        this.duration = oneClick.getDuration();
        this.checkupType = oneClick.getCheckupType();
        this.hallName = oneClick.getHall().getName();
        this.doctorFullName = oneClick.getDoctor().getName() + " " + oneClick.getDoctor().getLastName();
        this.price = oneClick.getPrice();
    }

    public Long getId() {
        return id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public Duration getDuration() {
        return duration;
    }

    public CheckupType getCheckupType() {
        return checkupType;
    }

    public String getHallName() {
        return hallName;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public double getPrice() {
        return price;
    }
}
