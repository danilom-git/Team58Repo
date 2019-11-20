package team58.healthy.dto;

import team58.healthy.model.Clinic;

public class ClinicDTO {
    private Long id;
    private String name;
    private String address;
    private String prices;

    public ClinicDTO() {

    }

    public ClinicDTO(Long id, String name, String address, String prices) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.prices = prices;
    }

    public ClinicDTO(Clinic clinic) {
        this(clinic.getId(), clinic.getName(), clinic.getAddress(), clinic.getPrices());
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

    public String getPrices() {
        return prices;
    }
}
