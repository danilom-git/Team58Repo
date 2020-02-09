package team58.healthy.dto;

import team58.healthy.model.ClinicAdmin;

public class ClinicAdminDTO {

    private Long id;
    private String name;
    private String lastName;
    private String email;
    private Long clinicId;
    private Boolean firstPasswordChanged;

    public ClinicAdminDTO() {
    }

    public ClinicAdminDTO(ClinicAdmin clinicAdmin) {
        this.id = clinicAdmin.getId();
        this.name = clinicAdmin.getName();
        this.clinicId = clinicAdmin.getClinic().getId();
        this.lastName = clinicAdmin.getLastName();
        this.email = clinicAdmin.getEmail();
        this.firstPasswordChanged = clinicAdmin.getFirstPasswordChanged();
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getClinicId() {
        return clinicId;
    }

    public void setClinicId(Long clinicId) {
        this.clinicId = clinicId;
    }

    public Boolean getFirstPasswordChanged() {
        return firstPasswordChanged;
    }

    public void setFirstPasswordChanged(Boolean firstPasswordChanged) {
        this.firstPasswordChanged = firstPasswordChanged;
    }
}
