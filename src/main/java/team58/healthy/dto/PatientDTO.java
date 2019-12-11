package team58.healthy.dto;

import team58.healthy.model.Patient;

public class PatientDTO {


    private Long id;
    private String healthInsuranceId;
    private String email;
    private String name;
    private String lastName;
    private String address;
    private String city;
    private String country;
    private String phoneNumber;

    public PatientDTO() {
    }

    public PatientDTO(Patient patient){
        this.id = patient.getId();
        this.healthInsuranceId = patient.getHealthInsuranceId();
        this.email = patient.getEmail();
        this.name = patient.getName();
        this.lastName = patient.getLastName();
        this.address = patient.getAddress();
        this.city = patient.getCity();
        this.country = patient.getCountry();
        this.phoneNumber = patient.getPhoneNumber();
    }

    public Long getId() {
        return id;
    }

    public String getHealthInsuranceId() {
        return healthInsuranceId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHealthInsuranceId(String healthInsuranceId) {
        this.healthInsuranceId = healthInsuranceId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
