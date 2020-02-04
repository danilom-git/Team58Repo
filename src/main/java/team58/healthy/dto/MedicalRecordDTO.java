package team58.healthy.dto;

import team58.healthy.model.MedicalRecord;

public class MedicalRecordDTO {
    private Long patientId;
    private double height;
    private double weight;
    private int age;
    private double diopter;
    private String bloodType;

    public MedicalRecordDTO() {}

    public MedicalRecordDTO(MedicalRecord medicalRecord) {
        patientId = medicalRecord.getPatient().getId();
        height = medicalRecord.getHeight();
        weight = medicalRecord.getWeight();
        age = medicalRecord.getAge();
        diopter = medicalRecord.getDiopter();
        bloodType = medicalRecord.getBloodType();
    }

    public Long getPatientId() {
        return patientId;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public int getAge() {
        return age;
    }

    public double getDiopter() {
        return diopter;
    }

    public String getBloodType() {
        return bloodType;
    }
}
