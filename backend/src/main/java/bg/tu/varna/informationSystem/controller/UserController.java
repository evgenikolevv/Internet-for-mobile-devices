package bg.tu.varna.informationSystem.controller;

import bg.tu.varna.informationSystem.dto.users.UserRequestDto;
import bg.tu.varna.informationSystem.dto.users.UserResponseDto;
import bg.tu.varna.informationSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAnyAuthority('add','view')")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String test() {
        return "imash pravo";
    }

    @PostMapping
    public UserResponseDto save(@Valid @RequestBody UserRequestDto userRequestDto) {
        return userService.save(userRequestDto);
    }
}
