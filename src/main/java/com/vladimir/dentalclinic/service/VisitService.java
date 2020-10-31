package com.vladimir.dentalclinic.service;

import com.vladimir.dentalclinic.dto.VisitDTO;
import com.vladimir.dentalclinic.exceptions.NoSuchEntityException;
import com.vladimir.dentalclinic.model.Visit;
import com.vladimir.dentalclinic.repositories.VisitRepository;
import com.vladimir.dentalclinic.utils.EntityAndDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VisitService {

    private VisitRepository visitRepository;
    private EntityAndDTOConverter converter;

    private static final String NO_SUCH_VISIT_MESSAGE = "Visit with such id is not found ";

    @Autowired
    public VisitService(VisitRepository visitRepository, EntityAndDTOConverter converter) {
        this.visitRepository = visitRepository;
        this.converter = converter;
    }

    public VisitDTO findById(long id) throws NoSuchEntityException {
        return convertToDTO(visitRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(NO_SUCH_VISIT_MESSAGE + id)));
    }

    public void saveVisit(VisitDTO visitDTO) {
        Visit visit = convertToEntity(visitDTO);
        visitRepository.save(visit);
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

    private Visit convertToEntity(VisitDTO visitDTO) {
        return converter.convert(visitDTO, Visit.class);
    }

}
