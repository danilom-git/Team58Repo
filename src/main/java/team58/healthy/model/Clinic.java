package team58.healthy.model;

import java.util.List;
import javax.persistence.*;

@Entity
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String address;

    @Column
    private double xCoord;

    @Column
    private double yCoord;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OneClick> oneClicks;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Hall> halls;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClinicAdmin> clinicAdmins;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ClinicRating> clinicRatings;

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

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public List<OneClick> getOneClicks() {
        return oneClicks;
    }

    public void setOneClicks(List<OneClick> oneClicks) {
        this.oneClicks = oneClicks;
    }

    public List<Hall> getHalls() {
        return halls;
    }

    public void setHalls(List<Hall> halls) {
        this.halls = halls;
    }

    public List<ClinicAdmin> getClinicAdmins() {
        return clinicAdmins;
    }

    public void setClinicAdmins(List<ClinicAdmin> clinicAdmins) {
        this.clinicAdmins = clinicAdmins;
    }

    public List<ClinicRating> getClinicRatings() {
        return clinicRatings;
    }

    public void setClinicRatings(List<ClinicRating> clinicRatings) {
        this.clinicRatings = clinicRatings;
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
