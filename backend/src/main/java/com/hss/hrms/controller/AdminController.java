package com.hss.hrms.controller;

import com.hss.hrms.dto.CreateCompanyDTO;
import com.hss.hrms.model.UserPrincipal;
import com.hss.hrms.model.Users;
import com.hss.hrms.service.AdminService;
import com.hss.hrms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import com.hss.hrms.dto.CreateEmployeeDTO;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/api/admin/create_company")
    public CreateCompanyDTO createCompany(@AuthenticationPrincipal UserPrincipal principal, @RequestBody CreateCompanyDTO dto){
        Users admin = userService.getUserByEmail(principal.getUsername());
        CreateCompanyDTO resp = adminService.createCompany(dto);
        return resp;
    }

    @PreAuthorize("hasRole('Admin')")
    @PutMapping("/api/admin/edit_company")
    public CreateCompanyDTO editCompany(@AuthenticationPrincipal UserPrincipal principal, @RequestBody CreateCompanyDTO dto){
        Users admin = userService.getUserByEmail(principal.getUsername());
        CreateCompanyDTO resp = adminService.editCompany(dto);
        return resp;
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/api/admin/company")
    public CreateCompanyDTO viewCompany(@AuthenticationPrincipal UserPrincipal principal, @RequestBody Integer companyId){
        Users admin = userService.getUserByEmail(principal.getUsername());
        return adminService.viewcompany(companyId);
    }

    @PreAuthorize("hasRole('Admin')")
    @DeleteMapping("/api/admin/company/delete/{id}")
    public void deleteCompany(@AuthenticationPrincipal UserPrincipal principal, @PathVariable Integer id){
        Users admin = userService.getUserByEmail(principal.getUsername());
        adminService.deleteCompany(id);
    }

    @PreAuthorize("hasRole('Admin')")
    @PostMapping("/api/admin/createHR")
    public CreateEmployeeDTO addEmployee(@AuthenticationPrincipal UserPrincipal principal, @RequestBody CreateEmployeeDTO dto){
        Users admin = userService.getUserByEmail(principal.getUsername());
        return adminService.addEmployee(dto);
    }

//    @PreAuthorize("hasRole('Admin')")
//    @PostMapping("/api/admin/create_department")
//    public CreateDepartmentDTO createDepartment(@AuthenticationPrincipal UserPrincipal principal, @RequestBody CreateDepartmentDTO dto){
//        return adminService.addDepartment(dto);
//    }

}
