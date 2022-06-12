package bg.tu.varna.informationSystem.utils;

import bg.tu.varna.informationSystem.common.Messages;
import bg.tu.varna.informationSystem.common.RoleTypes;
import bg.tu.varna.informationSystem.entity.Client;
import bg.tu.varna.informationSystem.entity.Company;
import bg.tu.varna.informationSystem.exception.BadRequestException;
import bg.tu.varna.informationSystem.security.UserPrincipal;
import bg.tu.varna.informationSystem.service.ClientService;
import bg.tu.varna.informationSystem.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResourceValidator {

    private final CompanyService companyService;
    private final ClientService clientService;

    @Autowired
    public ResourceValidator(CompanyService companyService, ClientService clientService) {
        this.companyService = companyService;
        this.clientService = clientService;
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

    public void validateClientAccess(Long clientId) {
        Client client = clientService.findById(clientId);
        validateCompanyAccess(client.getCompany().getId());
    }
}
