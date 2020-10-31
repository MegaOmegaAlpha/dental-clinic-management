package com.vladimir.dentalclinic.service;

import com.vladimir.dentalclinic.dto.ProcedureDTO;
import com.vladimir.dentalclinic.model.Procedure;
import com.vladimir.dentalclinic.repositories.ProcedureRepository;
import com.vladimir.dentalclinic.utils.EntityAndDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProcedureService {

    private ProcedureRepository procedureRepository;
    private EntityAndDTOConverter converter;

    @Autowired
    public ProcedureService(ProcedureRepository procedureRepository, EntityAndDTOConverter converter) {
        this.procedureRepository = procedureRepository;
        this.converter = converter;
    }

    public List<ProcedureDTO> findAll() {
        return procedureRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ProcedureDTO convertToDTO(Procedure entity) {
        return this.converter.convert(entity, ProcedureDTO.class);
    }
}
