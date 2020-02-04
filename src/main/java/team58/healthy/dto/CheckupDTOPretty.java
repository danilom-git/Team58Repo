package team58.healthy.dto;
import team58.healthy.model.Checkup;
import java.util.Date;

public class CheckupDTOPretty {

    private Long id;
    private String clinicName;
    private String doctorFullName;
    private Date startDate;
    private Date endDate;
    private String checkupTypeName;

    public CheckupDTOPretty() { }

    public CheckupDTOPretty(Checkup checkup) {
        id = checkup.getId();
        clinicName = checkup.getClinic().getName();
        doctorFullName = checkup.getDoctor().getName() + " " + checkup.getDoctor().getLastName();
        startDate = checkup.getStartDate();
        endDate = checkup.getEndDate();
        checkupTypeName = checkup.getCheckupType().getName();
    }

    public Long getId() {
        return id;
    }

    public String getClinicName() {
        return clinicName;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getCheckupTypeName() {
        return checkupTypeName;
    }
}
