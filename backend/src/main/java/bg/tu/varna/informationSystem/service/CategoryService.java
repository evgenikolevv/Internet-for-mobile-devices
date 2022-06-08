package bg.tu.varna.informationSystem.service;

import bg.tu.varna.informationSystem.common.Messages;
import bg.tu.varna.informationSystem.dto.categories.CategoryDto;
import bg.tu.varna.informationSystem.entity.Category;
import bg.tu.varna.informationSystem.exception.BadRequestException;
import bg.tu.varna.informationSystem.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new BadRequestException(Messages.CATEGORY_NOT_FOUND));
    }

    public CategoryDto getById(Long id) {
        return convertToResponseDto(findById(id));
    }

    private CategoryDto convertToResponseDto(Category category) {
        return modelMapper.map(category, CategoryDto.class);
    }
}
