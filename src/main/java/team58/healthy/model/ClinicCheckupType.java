package team58.healthy.model;

import javax.persistence.*;

@Entity
public class ClinicCheckupType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Clinic clinic;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CheckupType checkupType;

    @Column
    private double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public CheckupType getCheckupType() {
        return checkupType;
    }

    public void setCheckupType(CheckupType checkupType) {
        this.checkupType = checkupType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
