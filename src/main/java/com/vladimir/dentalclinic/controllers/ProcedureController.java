package com.vladimir.dentalclinic.controllers;

import com.vladimir.dentalclinic.dto.ProcedureDTO;
import com.vladimir.dentalclinic.service.ProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class ProcedureController {

    private ProcedureService procedureService;

    @Autowired
    public ProcedureController(ProcedureService procedureService) {
        this.procedureService = procedureService;
    }

    @RequestMapping(value = "/procedures", method = RequestMethod.GET)
    public List<ProcedureDTO> findAll() {
        return procedureService.findAll();
    }
}
