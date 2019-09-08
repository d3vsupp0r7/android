package org.lba.android.simple.trainer.activity.basic.mvp.model;

public class EmployeeMVPSampleModel {

    private String name = "";
    private String surname = "";

    public EmployeeMVPSampleModel() {
    }
    
    public EmployeeMVPSampleModel(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String email) {
        this.surname = email;
    }

    @Override
    public String toString() {
        return "Surname : " + surname + "\nName : " + name;
    }
}
