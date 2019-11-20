package team58.healthy.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//inkrement
    private Long id;

    @Column(name = "ime")
    private String ime;

    @Column(name = "prezime")
    private String prezime;

    @Column(name = "odsustvo")
    private boolean odsustvo;

    public Long getId() {
        return id;
    }


    @Column(name = "godisnji")
    private boolean godisnji;

    @Column(name = "nazivklinike")
    private String nazivKlinike;

    @Column(name = "prosecnaocena")
    private float prosecnaOcena;

    @Column(name = "radnovreme")
    private int radnoVreme;

    @OneToMany(mappedBy = "doctor",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Checkup> zakazaniPregledi = new HashSet<Checkup>();


    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public boolean getOdsustvo() {
        return odsustvo;
    }

    public boolean getGodisnji() {
        return godisnji;
    }

    public String getNazivKlinike() {
        return nazivKlinike;
    }

    public float getProsecnaOcena() {
        return prosecnaOcena;
    }

    public int getRadnoVreme() {
        return radnoVreme;
    }

    public Set<Checkup> getZakazaniPregledi() {
        return zakazaniPregledi;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setOdsustvo(boolean odsustvo) {
        this.odsustvo = odsustvo;
    }

    public void setGodisnji(boolean godisnji) {
        this.godisnji = godisnji;
    }

    public void setNazivKlinike(String nazivKlinike) {
        this.nazivKlinike = nazivKlinike;
    }

    public void setProsecnaOcena(float prosecnaOcena) {
        this.prosecnaOcena = prosecnaOcena;
    }

    public void setRadnoVreme(int radnoVreme) {
        this.radnoVreme = radnoVreme;
    }

    public void setZakazaniPregledi(Set<Checkup> zakazaniPregledi) {
        this.zakazaniPregledi = zakazaniPregledi;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", odsustvo=" + odsustvo +
                ", godisnji=" + godisnji +
                ", nazivKlinike='" + nazivKlinike + '\'' +
                ", prosecnaOcena=" + prosecnaOcena +
                ", radnoVreme=" + radnoVreme +
                ", zakazaniPregledi=" + zakazaniPregledi +
                '}';
    }


}
