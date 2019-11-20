package team58.healthy.model;

import javax.persistence.*;

@Entity
public class Checkup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//inkrement
    private Long id;

    //@Column(name = "patient")
    //private Patient patient;

    //@Column(name = "clinic")
    //private Clinic clinic;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Doctor doctor;

    //private CheckupType checkupType;
    //private Hall hall;
    //private Diagnosis diagnosis;
    //private Prescription prescription;

}
