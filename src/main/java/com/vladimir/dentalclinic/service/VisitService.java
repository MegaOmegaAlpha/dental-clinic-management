package com.vladimir.dentalclinic.service;

import com.vladimir.dentalclinic.dto.VisitDTO;
import com.vladimir.dentalclinic.exceptions.NoSuchEntityException;
import com.vladimir.dentalclinic.model.Visit;
import com.vladimir.dentalclinic.repositories.*;
import com.vladimir.dentalclinic.utils.EntityAndDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VisitService {

    private VisitRepository visitRepository;
    private DentistRepository dentistRepository;
    private PatientRepository patientRepository;
    private DiagnosisRepository diagnosisRepository;
    private ProcedureRepository procedureRepository;
    private AppointmentRepository appointmentRepository;
    private EntityAndDTOConverter converter;

    private static final String NO_SUCH_VISIT_MESSAGE = "Visit with such id is not found ";

    @Autowired
    public VisitService(VisitRepository visitRepository, DentistRepository dentistRepository, PatientRepository patientRepository, DiagnosisRepository diagnosisRepository, ProcedureRepository procedureRepository, AppointmentRepository appointmentRepository, EntityAndDTOConverter converter) {
        this.visitRepository = visitRepository;
        this.dentistRepository = dentistRepository;
        this.patientRepository = patientRepository;
        this.diagnosisRepository = diagnosisRepository;
        this.procedureRepository = procedureRepository;
        this.appointmentRepository = appointmentRepository;
        this.converter = converter;
    }

    public VisitDTO findById(long id) throws NoSuchEntityException {
        return convertToDTO(visitRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(NO_SUCH_VISIT_MESSAGE + id)));
    }

    public VisitDTO saveVisit(VisitDTO visitDTO) {
        Visit visit = new Visit();
        visit.setComplaint(visitDTO.getComplaint());
        visit.setStatus(visitDTO.getStatus());
        visit.setAppointment(visitDTO.getAppointment() == null ? null : appointmentRepository.findById(visitDTO.getAppointment().getId()).get());
        visit.setPatient(patientRepository.findById(visitDTO.getPatient().getId()).get());
        visit.setDentist(dentistRepository.findById(visitDTO.getDentist().getId()).get());
        visit.setProcedure(procedureRepository.findById(visitDTO.getProcedure().getId()).get());
        visit.setDiagnosis(visitDTO.getDiagnosis() == null ? null : diagnosisRepository.findById(visitDTO.getDiagnosis().getId()).get());
        visit.setVisitDate(new Date(System.currentTimeMillis()));
        return convertToDTO(visitRepository.save(visit));
    }

    public List<VisitDTO> findAll() {
        return visitRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private VisitDTO convertToDTO(Visit visit) {
        return converter.convert(visit, VisitDTO.class);
    }

}
