package bg.tu.varna.informationSystem.service;

import bg.tu.varna.informationSystem.common.Messages;
import bg.tu.varna.informationSystem.dto.vehicledetails.VehicleDetailsDto;
import bg.tu.varna.informationSystem.dto.vehicles.VehicleRequestDto;
import bg.tu.varna.informationSystem.dto.vehicles.VehicleResponseDto;
import bg.tu.varna.informationSystem.entity.*;
import bg.tu.varna.informationSystem.exception.BadRequestException;
import bg.tu.varna.informationSystem.repository.VehicleDetailsRepository;
import bg.tu.varna.informationSystem.repository.VehicleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleDetailsRepository vehicleDetailsRepository;
    private final CompanyService companyService;
    private final CarClassService carClassService;
    private final CategoryService categoryService;
    private final EngineService engineService;
    private final ModelMapper modelMapper;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository,
                          VehicleDetailsRepository vehicleDetailsRepository,
                          CompanyService companyService,
                          CarClassService carClassService,
                          CategoryService categoryService,
                          EngineService engineService,
                          ModelMapper modelMapper) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleDetailsRepository = vehicleDetailsRepository;
        this.companyService = companyService;
        this.carClassService = carClassService;
        this.categoryService = categoryService;
        this.engineService = engineService;
        this.modelMapper = modelMapper;
    }

    public VehicleResponseDto getById(Long id) {
        Vehicle vehicle = findVehicleById(id);
        VehicleDetails vehicleDetails = findVehicleDetailsByVehicle(vehicle);
        return convertToResponseDto(vehicle, vehicleDetails);
    }

    public Vehicle findVehicleById(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new BadRequestException(Messages.VEHICLE_NOT_FOUND));
    }

    public VehicleDetails findVehicleDetailsByVehicle(Vehicle vehicle) {
        return vehicleDetailsRepository.findByVehicle(vehicle).orElseThrow(() -> new BadRequestException(Messages.VEHICLE_DETAILS_NOT_FOUND));
    }

    @Transactional
    public VehicleResponseDto save(VehicleRequestDto dto) {
        Engine engine = engineService.findById(dto.getEngineId());
        Category category = categoryService.findById(dto.getCategoryId());
        CarClass carClass = carClassService.findById(dto.getCarClassId());
        Company company = companyService.findById(dto.getCompanyId());

        Vehicle vehicle = new Vehicle();
        vehicle.setMake(dto.getMake());
        vehicle.setModel(dto.getModel());
        vehicle.setYear(dto.getYear());
        vehicle.setIsAvailable(true);
        vehicle.setEngine(engine);
        vehicle.setCategory(category);
        vehicle.setCarClass(carClass);
        vehicle.setCompany(company);

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        VehicleDetails vehicleDetails = new VehicleDetails();
        vehicleDetails.setVehicle(savedVehicle);
        vehicleDetails.setDoors(dto.getDoors());
        vehicleDetails.setAirConditioner(dto.getAirConditioner());
        vehicleDetails.setSmoke(dto.getSmoke());
        vehicleDetails.setDayPrice(dto.getDayPrice());
        vehicleDetails.setKilometerPrice(dto.getKilometerPrice());
        vehicleDetails.setPhotoUrl(dto.getPhotoUrl());

        VehicleDetails savedDetails = vehicleDetailsRepository.save(vehicleDetails);

        return convertToResponseDto(savedVehicle, savedDetails);
    }

    private VehicleResponseDto convertToResponseDto(Vehicle vehicle, VehicleDetails vehicleDetails) {
        VehicleResponseDto result = modelMapper.map(vehicle, VehicleResponseDto.class);
        VehicleDetailsDto vehicleDetailsDto = modelMapper.map(vehicleDetails, VehicleDetailsDto.class);
        result.setVehicleDetails(vehicleDetailsDto);
        return result;
    }
}
