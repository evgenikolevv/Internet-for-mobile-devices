package bg.tu.varna.informationSystem.service;

import bg.tu.varna.informationSystem.common.Messages;
import bg.tu.varna.informationSystem.dto.clients.ClientRequestDto;
import bg.tu.varna.informationSystem.dto.clients.ClientResponseDto;
import bg.tu.varna.informationSystem.entity.Client;
import bg.tu.varna.informationSystem.entity.Company;
import bg.tu.varna.informationSystem.exception.BadRequestException;
import bg.tu.varna.informationSystem.repository.ClientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;
    private final CompanyService companyService;

    @Autowired
    public ClientService(ClientRepository clientRepository,
                         ModelMapper modelMapper,
                         CompanyService companyService) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
        this.companyService = companyService;
    }

    public ClientResponseDto save(ClientRequestDto dto) {
        Client client = convertToEntity(dto);
        Company company = companyService.findById(dto.getCompanyId());
        client.setCompany(company);

        Client savedClient = clientRepository.save(client);
        return convertToResponseDto(clientRepository.save(savedClient));
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new BadRequestException(Messages.CLIENT_NOT_FOUND));
    }

    public ClientResponseDto getById(Long id) {
        return convertToResponseDto(findById(id));
    }

    private ClientResponseDto convertToResponseDto(Client client) {
        ClientResponseDto responseDto = modelMapper.map(client, ClientResponseDto.class);
        responseDto.setCompanyId(client.getCompany().getId());
        return responseDto;
    }

    private Client convertToEntity(ClientRequestDto dto) {
        Client client = modelMapper.map(dto, Client.class);
        Company company = companyService.findById(dto.getCompanyId());
        client.setCompany(company);
        return client;
    }
}
