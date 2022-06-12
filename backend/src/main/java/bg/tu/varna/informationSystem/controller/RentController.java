package bg.tu.varna.informationSystem.controller;

import bg.tu.varna.informationSystem.dto.rents.RentRequestDto;
import bg.tu.varna.informationSystem.dto.rents.RentResponseDto;
import bg.tu.varna.informationSystem.dto.rents.RentReturnVehicleDto;
import bg.tu.varna.informationSystem.service.RentService;
import bg.tu.varna.informationSystem.utils.ResourceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("rents")
public class RentController {

    private final RentService rentService;
    private final ResourceValidator resourceValidator;
    @Autowired
    public RentController(RentService rentService, ResourceValidator resourceValidator) {
        this.rentService = rentService;
        this.resourceValidator = resourceValidator;
    }

    @PostMapping
    public RentResponseDto save(@Valid @RequestBody RentRequestDto dto) {
        resourceValidator.validateClientAccess(dto.getClientId());
        return rentService.save(dto);
    }

    @PutMapping("/{id}")
    public RentResponseDto returnVehicle(@PathVariable Long id, @Valid @RequestBody RentReturnVehicleDto dto) {
        resourceValidator.validateRentAccess(id);
        return rentService.update(id, dto);
    }
}
