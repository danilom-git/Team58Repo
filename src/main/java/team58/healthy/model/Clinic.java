package team58.healthy.model;

import java.util.List;

public class Clinic {

    private int id;
    private String name;
    private String address;
    private String prices;
    private List<Doctor> doctorList;
    private List<OneClick> oneClickList;
    private List<Hall> hallList;

    private List<ClinicAdmin> clinicAdminList;
}
