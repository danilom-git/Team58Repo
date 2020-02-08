package team58.healthy.dto;

import team58.healthy.model.Clinic;
import team58.healthy.model.ClinicRating;

public class ClinicDTO {
    private Long id;
    private String name;
    private String country;
    private String city;
    private String address;
    private double averageRating;
    private double xCoord;
    private double yCoord;

    public ClinicDTO() {

    }

    public ClinicDTO(Clinic clinic) {
        this.id = clinic.getId();
        this.name = clinic.getName();
        this.country = clinic.getCountry();
        this.city = clinic.getCity();
        this.address = clinic.getAddress();
        int sum = 0;
        for (ClinicRating rating : clinic.getClinicRatings()) {
            sum += rating.getRating();
        }
        this.averageRating = (double)sum / clinic.getClinicRatings().size();
        this.xCoord = clinic.getxCoord();
        this.yCoord = clinic.getyCoord();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public double getxCoord() {
        return xCoord;
    }

    public void setxCoord(double xCoord) {
        this.xCoord = xCoord;
    }

    public double getyCoord() {
        return yCoord;
    }

    public void setyCoord(double yCoord) {
        this.yCoord = yCoord;
    }
}
