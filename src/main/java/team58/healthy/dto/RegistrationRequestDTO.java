package team58.healthy.dto;

import team58.healthy.model.RegistrationRequest;

import java.util.Date;

public class RegistrationRequestDTO {

    private Long id;
    private String healthInsuranceId;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private String country;
    private String city;
    private String address;
    private String phoneNumber;

    public RegistrationRequestDTO() { }

    public RegistrationRequestDTO(Long id, String healthInsuranceId, String email, String password, String name, String lastName, String country, String city, String address, String phoneNumber) {
        this.id = id;
        this.healthInsuranceId = healthInsuranceId;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastName = lastName;
        this.country = country;
        this.city = city;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public RegistrationRequestDTO(RegistrationRequest request) {
        this.id = request.getId();
        this.healthInsuranceId = request.getHealthInsuranceId();
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.name = request.getName();
        this.lastName = request.getLastName();
        this.country = request.getCountry();
        this.city = request.getCity();
        this.address = request.getAddress();
        this.phoneNumber = request.getPhoneNumber();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHealthInsuranceId() {
        return healthInsuranceId;
    }

    public void setHealthInsuranceId(String healthInsuranceId) {
        this.healthInsuranceId = healthInsuranceId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
