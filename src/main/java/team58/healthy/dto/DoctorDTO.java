package team58.healthy.dto;

import team58.healthy.model.Checkup;
import team58.healthy.model.Doctor;

import java.util.HashSet;
import java.util.Set;

public class DoctorDTO {

    private Long id;
    private String ime;
    private String prezime;
    private boolean odsustvo;
    private boolean godisnji;
    private String nazivKlinike;
    private float prosecnaOcena;
    private int radnoVreme;

    public DoctorDTO() {

    }

    public DoctorDTO(Doctor doctor) {
        this.id  = doctor.getId();
        this.ime = doctor.getIme();
        this.prezime = doctor.getPrezime();
        this.odsustvo = doctor.getOdsustvo();
        this.godisnji = doctor.getGodisnji();
        this.nazivKlinike = doctor.getNazivKlinike();
        this.prosecnaOcena = doctor.getProsecnaOcena();
        this.radnoVreme = doctor.getRadnoVreme();
    }

    public Long getId() {
        return id;
    }

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

}
