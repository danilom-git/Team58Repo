package team58.healthy.model;

import team58.healthy.dto.RegistrationRequestDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
public class RegistrationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String healthInsuranceId;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private String address;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String phoneNumber;

    @Column
    private Date creationDate;

    @Column
    private boolean accepted;

    @Column
    private boolean verified;

    @Column
    private boolean archived;

    public RegistrationRequest() { }

    public RegistrationRequest(RegistrationRequestDTO requestDTO) {
        this.healthInsuranceId = requestDTO.getHealthInsuranceId();
        this.email = requestDTO.getEmail();
        this.password = requestDTO.getPassword();
        this.name = requestDTO.getName();
        this.lastName = requestDTO.getLastName();
        this.address = requestDTO.getAddress();
        this.city = requestDTO.getCity();
        this.country = requestDTO.getCountry();
        this.phoneNumber = requestDTO.getPhoneNumber();
        this.creationDate = new Date();
        this.accepted = false;
        this.verified = false;
        this.archived = false;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}
