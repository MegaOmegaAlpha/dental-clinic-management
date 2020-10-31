package com.vladimir.dentalclinic.service;

import com.vladimir.dentalclinic.dto.PatientDTO;
import com.vladimir.dentalclinic.exceptions.NoSuchEntityException;
import com.vladimir.dentalclinic.model.Patient;
import com.vladimir.dentalclinic.repositories.PatientRepository;
import com.vladimir.dentalclinic.utils.EntityAndDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    private EntityAndDTOConverter converter;

    private static final String NO_SUCH_APPOINTMENT_MESSAGE = "Patient with such id is no found ";

    @Autowired
    public PatientService(PatientRepository patientRepository, EntityAndDTOConverter converter) {
        this.patientRepository = patientRepository;
        this.converter = converter;
    }

    public PatientDTO findById(long id) throws NoSuchEntityException {
        return convertToDTO(patientRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException(NO_SUCH_APPOINTMENT_MESSAGE + id))
        );
    }

    private PatientDTO convertToDTO(Patient patient) {
        PatientDTO result = converter.convert(patient, PatientDTO.class);
        return result;
    }
}