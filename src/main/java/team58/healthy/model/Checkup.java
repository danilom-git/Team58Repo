package team58.healthy.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Checkup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CheckupType checkupType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Clinic clinic;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Hall hall;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Doctor doctor;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Diagnosis diagnosis;
//
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Prescription prescription;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public CheckupType getCheckupType() {
        return checkupType;
    }

    public void setCheckupType(CheckupType checkupType) {
        this.checkupType = checkupType;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }
}
