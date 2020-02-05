package team58.healthy.dto;

import team58.healthy.model.OneClick;

import java.util.Date;

public class OneClickDTOPretty {
    private Long id;
    private String checkupTypeName;
    private String clinicName;
    private String hallName;
    private String doctorFullName;
    private Date startDate;
    private String duration;
    private double price;

    public OneClickDTOPretty() { }

    public OneClickDTOPretty(OneClick oneClick) {
        id = oneClick.getId();
        checkupTypeName = oneClick.getCheckupType().getName();
        clinicName = oneClick.getClinic().getName();
        hallName = oneClick.getHall().getName();
        doctorFullName = oneClick.getDoctor().getName() + " " + oneClick.getDoctor().getLastName();
        startDate = oneClick.getStartTime();
        duration = oneClick.getDuration();
        price = oneClick.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getCheckupTypeName() {
        return checkupTypeName;
    }

    public String getClinicName() {
        return clinicName;
    }

    public String getHallName() {
        return hallName;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getDuration() {
        return duration;
    }

    public double getPrice() {
        return price;
    }
}
