package team58.healthy.dto;

import team58.healthy.model.Clinic;

public class ClinicDTO {
    private Long id;
    private String name;
    private String country;
    private String city;
    private String address;

    public ClinicDTO() {

    }

    public ClinicDTO(Long id, String name, String country, String city, String address) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public ClinicDTO(Clinic clinic) {
        this(clinic.getId(), clinic.getName(), clinic.getCountry(), clinic.getCity(), clinic.getAddress());
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
}
