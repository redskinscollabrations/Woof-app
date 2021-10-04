package com.example.main_app;

public class model {

    String vetname;
    String vet_type;
    String address;
    String city;
    String mobile;

    model(){


    }

    public model(String vetname, String vet_type, String address, String city, String mobile) {
        this.vetname = vetname;
        this.vet_type = vet_type;
        this.address = address;
        this.city = city;
        this.mobile = mobile;
    }

    public String getVetname() {
        return vetname;
    }

    public void setVetname(String vetname) {
        this.vetname = vetname;
    }

    public String getVet_type() {
        return vet_type;
    }

    public void setVet_type(String vet_type) {
        this.vet_type = vet_type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
