package team58.healthy.dto;

import team58.healthy.model.ClinicAdmin;

public class ClinicAdminDTO {

    private Long id;
    private String name;
    private Long clinicId;

    public ClinicAdminDTO() {
    }

    public ClinicAdminDTO(ClinicAdmin clinicAdmin) {
        this.id = clinicAdmin.getId();
        this.name = clinicAdmin.getName();
        this.clinicId = clinicAdmin.getClinic().getId();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }
}
