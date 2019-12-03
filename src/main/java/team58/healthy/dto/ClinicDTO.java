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
    }



    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public double getAverageRating() {
        return averageRating;
    }
}
