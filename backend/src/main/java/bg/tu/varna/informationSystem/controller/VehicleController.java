package bg.tu.varna.informationSystem.controller;

import bg.tu.varna.informationSystem.dto.vehicles.VehicleRequestDto;
import bg.tu.varna.informationSystem.dto.vehicles.VehicleResponseDto;
import bg.tu.varna.informationSystem.service.VehicleService;
import bg.tu.varna.informationSystem.utils.ResourceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final ResourceValidator resourceValidator;
    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(ResourceValidator resourceValidator, VehicleService vehicleService) {
        this.resourceValidator = resourceValidator;
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public VehicleResponseDto save(@Valid @RequestBody VehicleRequestDto dto) {
        resourceValidator.validateCompanyAccess(dto.getCompanyId());
        return vehicleService.save(dto);
    }

    @GetMapping("{id}")
    public VehicleResponseDto findById(@PathVariable Long id) {
        VehicleResponseDto vehicleDto = vehicleService.getById(id);
        resourceValidator.validateCompanyAccess(vehicleDto.getCompany().getId());
        return vehicleDto;
    }
}
