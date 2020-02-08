package team58.healthy.dto;

import team58.healthy.model.CheckupType;
import team58.healthy.model.ClinicCheckupType;

public class ClinicCheckupTypeDTO {
    private Long id;
    private Long clinicCheckupTypeId;
    private String name;
    private double price;
    private Long clinicId;


    public ClinicCheckupTypeDTO() {
    }

    public ClinicCheckupTypeDTO(ClinicCheckupType clinicCheckupType)
    {
        this.id = clinicCheckupType.getCheckupType().getId();
        this.name = clinicCheckupType.getCheckupType().getName();
        this.clinicId = clinicCheckupType.getClinic().getId();
        this.price = clinicCheckupType.getPrice();
        this.clinicCheckupTypeId = clinicCheckupType.getId();
    }

    public ClinicCheckupTypeDTO(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClinicCheckupTypeId() {
        return clinicCheckupTypeId;
    }

    public void setClinicCheckupTypeId(Long clinicCheckupTypeId) {
        this.clinicCheckupTypeId = clinicCheckupTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }
}
