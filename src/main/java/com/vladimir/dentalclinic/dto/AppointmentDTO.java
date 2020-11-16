package com.vladimir.dentalclinic.dto;

import java.sql.Date;
import java.sql.Time;

public class AppointmentDTO {

    private long id;

    private Date appointmentDate;

    private String appointmentTime;

    private DentistDTO dentist;

    private PatientDTO patient;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public DentistDTO getDentist() {
        return dentist;
    }

    public void setDentist(DentistDTO dentist) {
        this.dentist = dentist;
    }

    public PatientDTO getPatient() {
        return patient;
    }

    public void setPatient(PatientDTO patient) {
        this.patient = patient;
    }
}
