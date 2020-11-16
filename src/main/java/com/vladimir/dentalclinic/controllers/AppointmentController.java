package com.vladimir.dentalclinic.controllers;

import com.vladimir.dentalclinic.dto.AppointmentDTO;
import com.vladimir.dentalclinic.exceptions.NoSuchEntityException;
import com.vladimir.dentalclinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentController {

    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RequestMapping(value = "/appointments", method = RequestMethod.GET)
    public List<AppointmentDTO> getAllAppointments() {
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return appointmentService.findAllNotInVisitByUser(user.getUsername());
    }

    @RequestMapping(value = "/appointments/{id}", method = RequestMethod.GET)
    public AppointmentDTO getAppointment(@PathVariable long id) throws NoSuchEntityException {
        return appointmentService.findById(id);
    }

    @RequestMapping(value = "/appointments", method = RequestMethod.POST)
    public AppointmentDTO createAppointment(@RequestBody AppointmentDTO appointmentDTO) {
        return appointmentService.saveAppointment(appointmentDTO);
    }

    @RequestMapping(value = "appointments/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> cancelAppointment(@PathVariable long id) {
        appointmentService.cancel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
