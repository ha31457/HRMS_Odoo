package com.hss.hrms.service;

import com.hss.hrms.dto.CreateCompanyDTO;
import com.hss.hrms.dto.CreateEmployeeDTO;
import com.hss.hrms.model.CompanyDetails;
import com.hss.hrms.repository.CompanyDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private CompanyDetailsRepository companyDetailsRepository;

    public CreateCompanyDTO createCompany(CreateCompanyDTO dto){
        return companyDetailsRepository.save(dto);
    }


    public CreateCompanyDTO editCompany(CreateCompanyDTO dto) {
        return companyDetailsRepository.save(dto);
    }

    public CreateCompanyDTO viewcompany(Integer companyId) {
        Optional<CompanyDetails> response =  companyDetailsRepository.findById(companyId);
        if(response.isPresent()){
            CompanyDetails resp = response.get();
            CreateCompanyDTO dto = new CreateCompanyDTO();
            dto.setId(resp.getId());
            dto.setCompanyName(resp.getCompanyName());
            dto.setCompanyLocation(resp.getCompanyLocation());
            dto.setLogoUrl(resp.getLogoUrl());
            dto.setRegistrationNumber(resp.getRegistrationNumber());
            dto.setTaxId(resp.getTaxId());
            dto.setAddressLine1(resp.getAddressLine1());
            dto.setAddressLine2(resp.getAddressLine2());
            dto.setCity(resp.getCity());
            dto.setState(resp.getState());
            dto.setPostalCode(resp.getPostalCode());
            dto.setCountry(resp.getCountry());
            dto.setPhone(resp.getPhone());
            dto.setEmail(resp.getEmail());
            dto.setWebsite(resp.getWebsite());
            dto.setEstablishedDate(resp.getEstablishedDate());
            dto.setIsActive(resp.getIsActive());
            dto.setCreatedAt(resp.getCreatedAt());
            dto.setUpdatedAt(resp.getUpdatedAt());

            return dto;
        }else{
            return new CreateCompanyDTO();
        }
    }

    public void deleteCompany(Integer id) {
        companyDetailsRepository.deleteById(id);
    }

    public CreateEmployeeDTO addEmployee(CreateEmployeeDTO dto) {
        //to save the details in the database.
        return dto;
    }
}
