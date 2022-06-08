package bg.tu.varna.informationSystem.service;

import bg.tu.varna.informationSystem.common.Messages;
import bg.tu.varna.informationSystem.dto.carclasses.CarClassDto;
import bg.tu.varna.informationSystem.entity.CarClass;
import bg.tu.varna.informationSystem.exception.BadRequestException;
import bg.tu.varna.informationSystem.repository.CarClassRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarClassService {

    private final CarClassRepository carClassRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CarClassService(CarClassRepository carClassRepository, ModelMapper modelMapper) {
        this.carClassRepository = carClassRepository;
        this.modelMapper = modelMapper;
    }

    public CarClass findById(Long id) {
        return carClassRepository.findById(id).orElseThrow(() -> new BadRequestException(Messages.CAR_CLASS_NOT_FOUND));
    }

    public CarClassDto getById(Long id) {
        return convertToResponseDto(findById(id));
    }

    private CarClassDto convertToResponseDto(CarClass carClass) {
        return modelMapper.map(carClass, CarClassDto.class);
    }
}
