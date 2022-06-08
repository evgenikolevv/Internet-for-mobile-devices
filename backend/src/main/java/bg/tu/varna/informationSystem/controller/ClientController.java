package bg.tu.varna.informationSystem.controller;

import bg.tu.varna.informationSystem.dto.clients.ClientRequestDto;
import bg.tu.varna.informationSystem.dto.clients.ClientResponseDto;
import bg.tu.varna.informationSystem.service.ClientService;
import bg.tu.varna.informationSystem.utils.ResourceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ResourceValidator resourceValidator;
    private final ClientService clientService;

    @Autowired
    public ClientController(ResourceValidator resourceValidator, ClientService clientService) {
        this.resourceValidator = resourceValidator;
        this.clientService = clientService;
    }

    @PostMapping
    public ClientResponseDto save(@Valid @RequestBody ClientRequestDto dto) {
        resourceValidator.validateCompanyAccess(dto.getCompanyId());
        return clientService.save(dto);
    }
}
