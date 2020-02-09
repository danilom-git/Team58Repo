package team58.healthy.dto;

import team58.healthy.model.Clinic;
import team58.healthy.model.ClinicRating;
import team58.healthy.model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class ClinicReportDTO {

    private double clinicRating;
    private List<DoctorDTO> doctors;
    private List<CheckupDTO> checkups;

    public ClinicReportDTO() {
    }

    public ClinicReportDTO(Clinic clinic,List<CheckupDTO> checkups,List<DoctorDTO> doctors)
    {
        int sum = 0;
        for (ClinicRating rating : clinic.getClinicRatings()) {
            sum += rating.getRating();
        }
        this.clinicRating = (double)sum / clinic.getClinicRatings().size();
        this.doctors = doctors;
        this.checkups = checkups;
    }

    public double getClinicRating() {
        return clinicRating;
    }

    public void setClinicRating(double clinicRating) {
        this.clinicRating = clinicRating;
    }

    public List<DoctorDTO> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<DoctorDTO> doctors) {
        this.doctors = doctors;
    }

    public List<CheckupDTO> getCheckups() {
        return checkups;
    }

    public void setCheckups(List<CheckupDTO> checkups) {
        this.checkups = checkups;
    }
}
