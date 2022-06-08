package bg.tu.varna.informationSystem.service;

import bg.tu.varna.informationSystem.common.Messages;
import bg.tu.varna.informationSystem.dto.engines.EngineDto;
import bg.tu.varna.informationSystem.entity.Engine;
import bg.tu.varna.informationSystem.exception.BadRequestException;
import bg.tu.varna.informationSystem.repository.EngineRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EngineService {

    private final EngineRepository engineRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public EngineService(EngineRepository engineRepository, ModelMapper modelMapper) {
        this.engineRepository = engineRepository;
        this.modelMapper = modelMapper;
    }

    public Engine findById(Long id) {
        return engineRepository.findById(id).orElseThrow(() -> new BadRequestException(Messages.ENGINE_NOT_FOUND));
    }

    public EngineDto getById(Long id) {
        return convertToResponseDto(findById(id));
    }

    private EngineDto convertToResponseDto(Engine engine) {
        return modelMapper.map(engine, EngineDto.class);
    }
}
