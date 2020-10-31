package com.vladimir.dentalclinic.controllers;

import com.vladimir.dentalclinic.dto.DiagnosisDTO;
import com.vladimir.dentalclinic.service.DiagnosisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class DiagnosisController {

    private DiagnosisService diagnosisService;

    @Autowired
    public DiagnosisController(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    @RequestMapping(value = "/diagnosis", method = RequestMethod.GET)
    public List<DiagnosisDTO> findAll() {
        return diagnosisService.findAll();
    }
}
