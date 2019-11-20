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
    private String address;

    @Column
    private String prices;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Doctor> doctorList;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OneClick> oneClickList;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Hall> hallList;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ClinicAdmin> clinicAdminList;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
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
