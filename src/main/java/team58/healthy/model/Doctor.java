package team58.healthy.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String lastName;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Clinic clinic;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Checkup> checkups = new HashSet<Checkup>();

//    @ManyToMany(mappedBy = "doctorSet", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<CheckupType> checkupTypes = new HashSet<CheckupType>();

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Set<Checkup> getCheckups() {
        return checkups;
    }

    public void setCheckups(Set<Checkup> checkups) {
        this.checkups = checkups;
    }

//    public Set<CheckupType> getCheckupTypes() {
//        return checkupTypes;
//    }
//
//    public void setCheckupTypes(Set<CheckupType> checkupTypes) {
//        this.checkupTypes = checkupTypes;
//    }
}
