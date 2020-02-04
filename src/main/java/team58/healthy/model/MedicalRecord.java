package team58.healthy.model;

import javax.persistence.*;

@Entity
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "medicalRecord", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Patient patient;

    @Column
    private double height;

    @Column
    private double weight;

    @Column
    private int age;

    @Column
    private double diopter;

    @Column
    private String bloodType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getDiopter() {
        return diopter;
    }

    public void setDiopter(double diopter) {
        this.diopter = diopter;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }
}
