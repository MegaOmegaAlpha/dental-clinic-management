package com.vladimir.dentalclinic.controllers;

import com.vladimir.dentalclinic.dto.VisitDTO;
import com.vladimir.dentalclinic.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class VisitController {

    private VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @RequestMapping(value = "/visits", method = RequestMethod.POST)
    public VisitDTO createVisit(@RequestBody VisitDTO visitDTO) {
        return visitService.saveVisit(visitDTO);
    }

}
