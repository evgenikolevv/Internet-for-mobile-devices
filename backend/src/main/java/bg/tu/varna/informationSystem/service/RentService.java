package bg.tu.varna.informationSystem.service;

import bg.tu.varna.informationSystem.common.Messages;
import bg.tu.varna.informationSystem.common.VehicleStatus;
import bg.tu.varna.informationSystem.dto.clients.ClientResponseDto;
import bg.tu.varna.informationSystem.dto.rents.RentRequestDto;
import bg.tu.varna.informationSystem.dto.rents.RentResponseDto;
import bg.tu.varna.informationSystem.dto.rents.RentReturnVehicleDto;
import bg.tu.varna.informationSystem.dto.users.UserResponseDto;
import bg.tu.varna.informationSystem.dto.vehicles.VehicleResponseDto;
import bg.tu.varna.informationSystem.dto.vehicles.VehicleStatusDto;
import bg.tu.varna.informationSystem.entity.*;
import bg.tu.varna.informationSystem.exception.BadRequestException;
import bg.tu.varna.informationSystem.repository.RentRepository;
import bg.tu.varna.informationSystem.utils.ApplicationUtils;
import bg.tu.varna.informationSystem.utils.UserPrincipalUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class RentService {

    private final RentRepository rentRepository;
    private final ModelMapper modelMapper;
    private final VehicleService vehicleService;
    private final ClientService clientService;
    private final UserService userService;

    @Autowired
    public RentService(RentRepository rentRepository,
                       ModelMapper modelMapper,
                       VehicleService vehicleService,
                       ClientService clientService,
                       UserService userService) {
        this.rentRepository = rentRepository;
        this.modelMapper = modelMapper;
        this.vehicleService = vehicleService;
        this.clientService = clientService;
        this.userService = userService;
    }

    @Transactional
    public RentResponseDto save(RentRequestDto dto) {
        Rent rent = new Rent();
        LocalDateTime dateFrom = ApplicationUtils.parseDateTime(dto.getDateFrom());
        LocalDateTime dateTo = ApplicationUtils.parseDateTime(dto.getDateTo());
        long delta = Duration.between(dateFrom, dateTo).toDays() + 1L;

        Vehicle vehicle = vehicleService.getVehicleIfAvailable(dto.getVehicleId(), dateFrom, dateTo);
        VehicleDetails vehicleDetails = vehicleService.getVehicleDetails(dto.getVehicleId());
        Client client = clientService.findById(dto.getClientId());
        User user = userService.findById(UserPrincipalUtils.getPrincipalFromContext().getId());

        BigDecimal finalPrice = calculateFinalPrice(
                delta,
                vehicleDetails.getDayPrice(),
                0L,
                new BigDecimal("0"));

        rent.setDateFrom(dateFrom);
        rent.setDateTo(dateTo);
        rent.setVehicle(vehicle);
        rent.setUser(user);
        rent.setClient(client);
        rent.setKilometers(0L);
        rent.setFinalPrice(finalPrice);

        Rent savedRent = rentRepository.save(rent);
        VehicleStatusDto vehicleStatusDto = vehicleService.saveVehicleStatus(vehicle, rent, VehicleStatus.GOOD_CONDITION.getText());
        RentResponseDto result = convertToResponseDto(savedRent);
        result.setVehicleStatus(vehicleStatusDto);
        return result;
    }

    public Rent findById(Long id) {
        return rentRepository.findById(id).orElseThrow(() -> new BadRequestException(Messages.RENT_NOT_FOUND));
    }

    private BigDecimal calculateFinalPrice(long days, BigDecimal dayPrice, long kilometers, BigDecimal kilometerPrice) {
        BigDecimal priceOfDays = new BigDecimal(String.valueOf(days)).multiply(dayPrice);
        BigDecimal priceOfKilometers = new BigDecimal(String.valueOf(kilometers)).multiply(kilometerPrice);
        return new BigDecimal(priceOfDays.add(priceOfKilometers).toString());
    }

    private RentResponseDto convertToResponseDto(Rent rent) {
        RentResponseDto result = modelMapper.map(rent, RentResponseDto.class);
        VehicleResponseDto vehicleDto = vehicleService.getById(result.getVehicle().getId());
        ClientResponseDto clientDto = clientService.getById(result.getClient().getId());
        UserResponseDto userDto = userService.getById(result.getUser().getId());
        result.setVehicle(vehicleDto);
        result.setClient(clientDto);
        result.setUser(userDto);
        return result;
    }

    public RentResponseDto update(Long id, RentReturnVehicleDto dto) {
        LocalDateTime dateReturned = ApplicationUtils.parseDateTime(dto.getDateReturned());
        Rent rent = findById(id);
        VehicleDetails details = vehicleService.findVehicleDetailsByVehicle(rent.getVehicle());
        long delta = Duration.between(rent.getDateFrom(), rent.getDateTo()).toDays() + 1L;


        if (rent.getDateReturned() != null) {
            throw new BadRequestException(Messages.RENT_VEHICLE_ALREADY_RETURNED);
        }

        rent.setDateReturned(dateReturned);
        rent.setKilometers(dto.getKilometers());

        BigDecimal finalPrice = calculateFinalPrice(delta, details.getDayPrice(), dto.getKilometers(), details.getKilometerPrice());
        rent.setFinalPrice(addAdditionalCost(finalPrice, dto.getIsDamaged()));

        VehicleStatusDto status = vehicleService.saveVehicleStatus(rent.getVehicle(), rent, dto.getVehicleCondition());
        RentResponseDto result = convertToResponseDto(rentRepository.save(rent));
        result.setVehicleStatus(status);

        return result;
    }

    private BigDecimal addAdditionalCost(BigDecimal finalPrice, boolean isDamaged) {
        if (isDamaged) {
            return finalPrice.add(new BigDecimal((int) (Math.random() * (1000 - 100)) + 100));
        }
        return finalPrice;
    }

}
