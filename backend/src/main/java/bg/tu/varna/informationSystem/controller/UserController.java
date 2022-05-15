package bg.tu.varna.informationSystem.controller;

import bg.tu.varna.informationSystem.dto.users.UserRequestDto;
import bg.tu.varna.informationSystem.dto.users.UserResponseDto;
import bg.tu.varna.informationSystem.service.UserService;
import bg.tu.varna.informationSystem.utils.ResourceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAnyAuthority('add','view')")
public class UserController {

    private final UserService userService;
    private final ResourceValidator resourceValidator;

    @Autowired
    public UserController(UserService userService, ResourceValidator resourceValidator) {
        this.userService = userService;
        this.resourceValidator = resourceValidator;
    }

    @PostMapping
    public UserResponseDto save(@Valid @RequestBody UserRequestDto userRequestDto) {
        userRequestDto.getCompanyIds().forEach(resourceValidator::validateCompanyAccess);
        return userService.save(userRequestDto);
    }
}
