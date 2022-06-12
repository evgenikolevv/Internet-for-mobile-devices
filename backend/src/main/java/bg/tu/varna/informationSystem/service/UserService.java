package bg.tu.varna.informationSystem.service;

import bg.tu.varna.informationSystem.common.Messages;
import bg.tu.varna.informationSystem.common.RoleTypes;
import bg.tu.varna.informationSystem.dto.companies.CompanyResponseDto;
import bg.tu.varna.informationSystem.dto.users.UserRequestDto;
import bg.tu.varna.informationSystem.dto.users.UserResponseDto;
import bg.tu.varna.informationSystem.entity.Role;
import bg.tu.varna.informationSystem.entity.User;
import bg.tu.varna.informationSystem.exception.BadRequestException;
import bg.tu.varna.informationSystem.repository.UserRepository;
import bg.tu.varna.informationSystem.security.UserPrincipal;
import bg.tu.varna.informationSystem.utils.UserPrincipalUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final RoleService roleService;
    private final CompanyService companyService;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       ModelMapper modelMapper,
                       RoleService roleService,
                       CompanyService companyService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.roleService = roleService;
        this.companyService = companyService;
    }

    @Transactional
    public UserResponseDto save(UserRequestDto userRequestDto) {
        UserPrincipal userPrincipal = UserPrincipalUtils.getPrincipalFromContext();
        if (!userPrincipal.getRoleName().equals(RoleTypes.ADMIN.toString())
                && !userRequestDto.getRoleName().equals(RoleTypes.AGENT.toString())) {
            throw new BadRequestException(Messages.PERMISSION_DENIED);
        }

        Boolean ifExists = userRepository.existsByUsername(userRequestDto.getUsername());

        if (ifExists) {
            throw new BadRequestException(Messages.USERNAME_IS_TAKEN);
        }

        User user = convertToEntity(userRequestDto);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));

        User savedUser = userRepository.save(user);

        List<CompanyResponseDto> companies = companyService.findByIds(userRequestDto.getCompanyIds());
        if (companies.isEmpty()) {
            throw new BadRequestException(Messages.COMPANY_NOT_FOUND);
        }

        List<Long> ids = companies.stream().map(CompanyResponseDto::getId).collect(Collectors.toList());
        companyService.assignUserToCompanies(user.getId(), ids);

        return convertToResponseDto(savedUser);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new BadRequestException(Messages.USER_NOT_FOUND));
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

    public UserResponseDto getById(Long id) {
        return convertToResponseDto(findById(id));
    }
}
