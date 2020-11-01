package com.vladimir.dentalclinic.controllers;

import com.vladimir.dentalclinic.dto.AppointmentDTO;
import com.vladimir.dentalclinic.exceptions.NoSuchEntityException;
import com.vladimir.dentalclinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return appointmentService.findAllNotInVisit();
    }

    @RequestMapping(value = "/appointments/{id}", method = RequestMethod.GET)
    public AppointmentDTO getAppointment(@PathVariable long id) throws NoSuchEntityException {
        return appointmentService.findById(id);
    }

}
