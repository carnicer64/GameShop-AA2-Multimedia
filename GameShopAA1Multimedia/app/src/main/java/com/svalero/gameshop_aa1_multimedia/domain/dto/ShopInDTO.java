package com.svalero.gameshop_aa1_multimedia.domain.dto;

import com.svalero.gameshop_aa1_multimedia.domain.Employee;

public class ShopInDTO {
    private long id;
    private String name;
    private String adress;
    private String tlf;
    private String latitude;
    private String longitude;
    private Employee employee;

    public ShopInDTO(long id, String name, String adress, String tlf, String latitude, String longitude, Employee employee) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.tlf = tlf;
        this.latitude = latitude;
        this.longitude = longitude;
        this.employee = employee;
    }

    public ShopInDTO() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
