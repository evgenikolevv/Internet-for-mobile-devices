package bg.tu.varna.informationSystem.service;

import bg.tu.varna.informationSystem.common.Messages;
import bg.tu.varna.informationSystem.dto.users.UserRequestDto;
import bg.tu.varna.informationSystem.dto.users.UserResponseDto;
import bg.tu.varna.informationSystem.entity.Role;
import bg.tu.varna.informationSystem.entity.User;
import bg.tu.varna.informationSystem.exception.BadRequestException;
import bg.tu.varna.informationSystem.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       ModelMapper modelMapper,
                       RoleService roleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
    }

    public UserResponseDto save(UserRequestDto userRequestDto) {
        Boolean ifExists = userRepository.existsByUsername(userRequestDto.getUsername());

        if (ifExists) {
            throw new BadRequestException(Messages.USERNAME_IS_TAKEN);
        }

        User user = convertToEntity(userRequestDto);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        User savedUser = userRepository.save(user);
        return convertToResponseDto(savedUser);
    }

    private UserResponseDto convertToResponseDto(User user) {
        UserResponseDto responseDto = modelMapper.map(user, UserResponseDto.class);
        Role role = roleService.findRoleById(user.getRoleId());
        responseDto.setRoleName(role.getName());
        return responseDto;
    }

    private User convertToEntity(UserRequestDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        Role role = roleService.findRoleByName(userDto.getRoleName());
        user.setRoleId(role.getId());
        return user;
    }
}
