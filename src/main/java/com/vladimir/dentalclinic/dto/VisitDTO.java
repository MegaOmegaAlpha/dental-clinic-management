package com.vladimir.dentalclinic.dto;

import java.util.Date;

public class VisitDTO {

    private long id;
    private String complaint;
    private String status;
    private Date visitDate;
    private DentistDTO dentist;
    private PatientDTO patient;
    private DiagnosisDTO diagnosis;
    private ProcedureDTO procedureDTO;

    public long getId() {
        return id;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
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

    public DiagnosisDTO getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(DiagnosisDTO diagnosis) {
        this.diagnosis = diagnosis;
    }

    public ProcedureDTO getProcedureDTO() {
        return procedureDTO;
    }

    public void setProcedureDTO(ProcedureDTO procedureDTO) {
        this.procedureDTO = procedureDTO;
    }
}
