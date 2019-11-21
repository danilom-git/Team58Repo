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

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Doctor> doctorList;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OneClick> oneClickList;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Hall> hallList;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClinicAdmin> clinicAdminList;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClinicRating> clinicRatingList;


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

    public List<Doctor> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public List<OneClick> getOneClickList() {
        return oneClickList;
    }

    public void setOneClickList(List<OneClick> oneClickList) {
        this.oneClickList = oneClickList;
    }

    public List<Hall> getHallList() {
        return hallList;
    }

    public void setHallList(List<Hall> hallList) {
        this.hallList = hallList;
    }

    public List<ClinicAdmin> getClinicAdminList() {
        return clinicAdminList;
    }

    public void setClinicAdminList(List<ClinicAdmin> clinicAdminList) {
        this.clinicAdminList = clinicAdminList;
    }

    public List<ClinicRating> getClinicRatingList() {
        return clinicRatingList;
    }

    public void setClinicRatingList(List<ClinicRating> clinicRatingList) {
        this.clinicRatingList = clinicRatingList;
    }
}
