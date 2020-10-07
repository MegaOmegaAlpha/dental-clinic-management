package com.vladimir.dentalclinic.controllers;

import com.vladimir.dentalclinic.dto.AppointmentDTO;
import com.vladimir.dentalclinic.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @RequestMapping(value = "/appointments", method = RequestMethod.GET)
    public List<AppointmentDTO> getAllAppointments() {
        return appointmentService.findAll();
    }

}
