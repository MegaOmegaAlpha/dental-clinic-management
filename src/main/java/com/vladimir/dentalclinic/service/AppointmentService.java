package com.vladimir.dentalclinic.service;

import com.vladimir.dentalclinic.dto.AppointmentDTO;
import com.vladimir.dentalclinic.dto.PatientDTO;
import com.vladimir.dentalclinic.exceptions.NoSuchEntityException;
import com.vladimir.dentalclinic.model.Appointment;
import com.vladimir.dentalclinic.model.Dentist;
import com.vladimir.dentalclinic.repositories.AppointmentRepository;
import com.vladimir.dentalclinic.repositories.DentistRepository;
import com.vladimir.dentalclinic.repositories.PatientRepository;
import com.vladimir.dentalclinic.utils.EntityAndDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private EntityAndDTOConverter converter;
    private DentistRepository dentistRepository;
    private PatientRepository patientRepository;

    private static final String NO_SUCH_APPOINTMENT_MESSAGE = "Appointment with such id is no found ";

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository,
                              EntityAndDTOConverter converter,
                              DentistRepository dentistRepository,
                              PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.converter = converter;
        this.dentistRepository = dentistRepository;
        this.patientRepository = patientRepository;
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

    public List<AppointmentDTO> findAllNotInVisitByUser(String username) {
        Dentist currentDentist = dentistRepository.findByUserId(username);
        return appointmentRepository.findAllNotInVisit(currentDentist.getId())
                .stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    public AppointmentDTO saveAppointment(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
        appointment.setAppointmentTime(Time.valueOf(appointmentDTO.getAppointmentTime() + ":00"));
        appointment.setPatient(patientRepository.findById(appointmentDTO.getPatient().getId()).get());
        appointment.setDentist(dentistRepository.findById(appointmentDTO.getDentist().getId()).get());
        return convertEntityToDTO(appointmentRepository.save(appointment));
    }

    public void cancel(long id) {
        appointmentRepository.deleteById(id);
    }

    private AppointmentDTO convertEntityToDTO(Appointment entity) {
        return converter.convert(entity, AppointmentDTO.class);
    }

}
