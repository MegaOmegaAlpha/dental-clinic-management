package com.vladimir.dentalclinic.controllers;

import com.vladimir.dentalclinic.dto.PatientDTO;
import com.vladimir.dentalclinic.exceptions.NoSuchEntityException;
import com.vladimir.dentalclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class PatientController {

    private PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @RequestMapping(value = "/patients/{id}", method = RequestMethod.GET)
    public PatientDTO getById(@PathVariable long id) throws NoSuchEntityException {
        return patientService.findById(id);
    }

    @RequestMapping(value = "/patients", method = RequestMethod.GET)
    public List<PatientDTO> getAll() {
        return patientService.findAll();
    }
}
