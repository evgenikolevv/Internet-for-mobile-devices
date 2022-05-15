package bg.tu.varna.informationSystem.utils;

import bg.tu.varna.informationSystem.common.Messages;
import bg.tu.varna.informationSystem.common.RoleTypes;
import bg.tu.varna.informationSystem.entity.Company;
import bg.tu.varna.informationSystem.exception.BadRequestException;
import bg.tu.varna.informationSystem.security.UserPrincipal;
import bg.tu.varna.informationSystem.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceValidator {

    private final CompanyService companyService;

    @Autowired
    public ResourceValidator(CompanyService companyService) {
        this.companyService = companyService;
    }

    public void validateCompanyAccess(Long companyId) {
        UserPrincipal userPrincipal = UserPrincipalUtils.getPrincipalFromContext();

        if (userPrincipal.getRoleName().equals(RoleTypes.ADMIN.toString())) {
            return;
        }

        List<Company> companies = companyService.getCompaniesByUserId(userPrincipal.getId());
        if (companies.isEmpty()) {
            throw new BadRequestException(Messages.NOT_ASSIGNED_TO_COMPANY);
        }

        boolean none = companies.stream().noneMatch(company -> company.getId().equals(companyId));
        if (none) {
            throw new BadRequestException(Messages.ACCESS_DENIED);
        }
    }
}
