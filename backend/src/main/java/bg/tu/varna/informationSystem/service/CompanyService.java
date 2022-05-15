package bg.tu.varna.informationSystem.service;

import bg.tu.varna.informationSystem.common.Messages;
import bg.tu.varna.informationSystem.dto.companies.CompanyRequestDto;
import bg.tu.varna.informationSystem.dto.companies.CompanyResponseDto;
import bg.tu.varna.informationSystem.entity.Company;
import bg.tu.varna.informationSystem.exception.BadRequestException;
import bg.tu.varna.informationSystem.repository.CompanyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, ModelMapper modelMapper) {
        this.companyRepository = companyRepository;
        this.modelMapper = modelMapper;
    }

    public CompanyResponseDto save(CompanyRequestDto companyRequestDto) {
        Boolean ifExists = companyRepository.existsByName(companyRequestDto.getName());

        if (ifExists) {
            throw new BadRequestException(Messages.NAME_IS_TAKEN);
        }

        Company company = convertToEntity(companyRequestDto);
        Company savedCompany = companyRepository.save(company);
        return convertToResponseDto(savedCompany);
    }

    public List<CompanyResponseDto> findByIds(List<Long> companyIds) {
        return companyRepository.findByIdIn(companyIds)
                .stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    private void assignUserToCompany(Long userId, Long companyId) {
        companyRepository.assignUserToCompany(userId, companyId);
    }

    public void assignUserToCompanies(Long userId, List<Long> companies) {
        companies.forEach(companyId -> assignUserToCompany(userId, companyId));
    }

    public List<Company> getCompaniesByUserId(Long userId) {
        return companyRepository.getCompaniesByUserId(userId);
    }

    private CompanyResponseDto convertToResponseDto(Company company) {
        return modelMapper.map(company, CompanyResponseDto.class);
    }

    private Company convertToEntity(CompanyRequestDto companyRequestDto) {
        return modelMapper.map(companyRequestDto, Company.class);
    }
}
