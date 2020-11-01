package com.vladimir.dentalclinic.service;

import com.vladimir.dentalclinic.dto.AppointmentDTO;
import com.vladimir.dentalclinic.dto.PatientDTO;
import com.vladimir.dentalclinic.exceptions.NoSuchEntityException;
import com.vladimir.dentalclinic.model.Appointment;
import com.vladimir.dentalclinic.repositories.AppointmentRepository;
import com.vladimir.dentalclinic.utils.EntityAndDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private EntityAndDTOConverter converter;

    private static final String NO_SUCH_APPOINTMENT_MESSAGE = "Appointment with such id is no found ";

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, EntityAndDTOConverter converter) {
        this.appointmentRepository = appointmentRepository;
        this.converter = converter;
    }

    public AppointmentDTO findById(long id) throws NoSuchEntityException {
        Appointment foundEntity = appointmentRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException(NO_SUCH_APPOINTMENT_MESSAGE + id));
        return convertEntityToDTO(foundEntity);
    }

    public List<AppointmentDTO> findAll() {
        return appointmentRepository.findAll()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public List<AppointmentDTO> findAllNotInVisit() {
        return appointmentRepository.findAllNotInVisit()
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public void cancel(long id) {
        appointmentRepository.deleteById(id);
    }

    private AppointmentDTO convertEntityToDTO(Appointment entity) {
        return converter.convert(entity, AppointmentDTO.class);
    }

}
