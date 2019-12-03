package team58.healthy.dto;

import team58.healthy.model.ClinicCheckupType;

public class ClinicWithCheckupDTO {
    private Long id;
    private String name;
    private String country;
    private String city;
    private String address;
    private double averageRating;
    private double price;

    public ClinicWithCheckupDTO() { }

    public ClinicWithCheckupDTO(ClinicDTO clinicDTO, ClinicCheckupType clinicCheckupType) {
        this.id = clinicDTO.getId();
        this.name = clinicDTO.getName();
        this.country = clinicDTO.getCountry();
        this.city = clinicDTO.getCity();
        this.address = clinicDTO.getAddress();
        this.averageRating = clinicDTO.getAverageRating();
        this.price = clinicCheckupType.getPrice();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public double getPrice() {
        return price;
    }
}
