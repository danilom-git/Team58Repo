package team58.healthy.model;

import javax.persistence.*;
import java.time.Duration;
import java.util.Date;

@Entity
public class OneClick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date startTime;

    @Column
    private Duration duration;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CheckupType checkupType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Hall hall;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Doctor doctor;

    @Column
    private double price;

}
