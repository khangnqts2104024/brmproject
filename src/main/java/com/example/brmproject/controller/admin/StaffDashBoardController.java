package com.example.brmproject.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.brmproject.domain.dto.CustomerDTO;
import com.example.brmproject.domain.dto.SignupRequestDTO;
import com.example.brmproject.domain.dto.StaffDTO;
import com.example.brmproject.service.CustomerService;
import com.example.brmproject.service.StaffService;

import groovyjarjarantlr4.v4.parse.ANTLRParser.parserRule_return;

import org.springframework.ui.Model;
import org.springframework.http.HttpHeaders;



import java.util.List;

@Controller
public class StaffDashBoardController {

    @Autowired
    StaffService staffService;

    
    @GetMapping("/staff/employees/getAll")
    public String allStaff(Model model, @RequestHeader HttpHeaders headers) {
        List<StaffDTO> allStaff = staffService.findAll();
        // In ra các header
        model.addAttribute("allStaff", allStaff);
        model.addAttribute("messeage", "Xin chào");

        return "adminTemplate/staffDashboard/StaffDashboard";
    }

    @GetMapping("/staff/employees/create")
    public String createStaff(Model model){
        SignupRequestDTO signupRequestStaff = new SignupRequestDTO();
        model.addAttribute("signupRequestStaff", signupRequestStaff);
        return "adminTemplate/staffDashboard/CreateStaffDashboard";
    }

}
