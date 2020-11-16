package com.vladimir.dentalclinic.controllers;

import com.vladimir.dentalclinic.dto.DentistDTO;
import com.vladimir.dentalclinic.service.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class DentistController {

    private DentistService dentistService;

    @Autowired
    public DentistController(DentistService dentistService) {
        this.dentistService = dentistService;
    }

    @RequestMapping(value = "/dentists", method = RequestMethod.GET)
    public List<DentistDTO> findAll() {
        return dentistService.findAll();
    }
}
