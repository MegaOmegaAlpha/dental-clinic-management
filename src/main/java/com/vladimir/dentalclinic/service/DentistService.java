package com.vladimir.dentalclinic.service;

import com.sun.org.apache.bcel.internal.generic.ARETURN;
import com.vladimir.dentalclinic.dto.DentistDTO;
import com.vladimir.dentalclinic.exceptions.NoSuchEntityException;
import com.vladimir.dentalclinic.model.Dentist;
import com.vladimir.dentalclinic.repositories.DentistRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DentistService {

    private DentistRepository dentistRepository;
    private ModelMapper modelMapper;

    private static final String NO_ENTITY_MESSAGE = "Doctor with such ID is not found";

    @Autowired
    public DentistService(DentistRepository dentistRepository, ModelMapper modelMapper) {
        this.dentistRepository = dentistRepository;
        this.modelMapper = modelMapper;
    }

    public List<DentistDTO> findAll() {
        return dentistRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public DentistDTO findById(long id) throws NoSuchEntityException {
        Dentist dentistFound = dentistRepository.findById(id).orElseThrow(() -> new NoSuchEntityException(NO_ENTITY_MESSAGE));
        return convertEntityToDTO(dentistFound);
    }

    public DentistDTO save(Dentist entity) {
        Dentist savedEntity = dentistRepository.save(entity);
        return convertEntityToDTO(savedEntity);
    }

    private DentistDTO convertEntityToDTO(Dentist entity) {
        DentistDTO result = modelMapper.map(entity, DentistDTO.class);
        result.setSpecializationName(entity.getSpecialization().getName());
        return result;
    }
}
