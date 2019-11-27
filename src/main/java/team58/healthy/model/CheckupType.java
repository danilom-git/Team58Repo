package team58.healthy.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CheckupType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private boolean available;

//    @ManyToMany(mappedBy = "checkupTypes", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private Set<Doctor> doctors = new HashSet<Doctor>();

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

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
