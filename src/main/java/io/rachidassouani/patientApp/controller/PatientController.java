package io.rachidassouani.patientApp.controller;

import io.rachidassouani.patientApp.model.Patient;
import io.rachidassouani.patientApp.service.PatientService;
import io.rachidassouani.patientApp.util.Constant;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("patients")
    public String patients(Model model,
                           @RequestParam(name="page", defaultValue = "0") int page,
                           @RequestParam(name = "size", defaultValue = Constant.DEFAULT_SIZE) int size,
                           @RequestParam(name = "fullName", defaultValue = "") String fullName) {

        Page<Patient> patientPages = patientService.findAllByFullName(fullName, page, size);

        model.addAttribute("patientPages", patientPages);
        model.addAttribute("totalPages", new int[patientPages.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("fullName", fullName);
        return "patients";
    }



}
