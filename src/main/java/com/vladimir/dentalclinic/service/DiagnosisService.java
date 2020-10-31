package com.vladimir.dentalclinic.service;

import com.vladimir.dentalclinic.dto.DiagnosisDTO;
import com.vladimir.dentalclinic.model.Diagnosis;
import com.vladimir.dentalclinic.repositories.DiagnosisRepository;
import com.vladimir.dentalclinic.utils.EntityAndDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiagnosisService {

    private DiagnosisRepository diagnosisRepository;
    private EntityAndDTOConverter converter;

    @Autowired
    public DiagnosisService(DiagnosisRepository diagnosisRepository, EntityAndDTOConverter converter) {
        this.diagnosisRepository = diagnosisRepository;
        this.converter = converter;
    }

    public List<DiagnosisDTO> findAll() {
        return diagnosisRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private DiagnosisDTO convertToDTO(Diagnosis entity) {
        return this.converter.convert(entity, DiagnosisDTO.class);
    }

}
