package bg.tu.varna.informationSystem.controller;

import bg.tu.varna.informationSystem.dto.companies.CompanyRequestDto;
import bg.tu.varna.informationSystem.dto.companies.CompanyResponseDto;
import bg.tu.varna.informationSystem.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/companies")
@PreAuthorize("hasAnyAuthority('add','view')")
public class CompanyController {

    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public CompanyResponseDto save(@Valid @RequestBody CompanyRequestDto companyRequestDto) {
        return companyService.save(companyRequestDto);
    }
}
