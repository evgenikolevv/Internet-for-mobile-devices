package bg.tu.varna.informationSystem.dto.users;

import bg.tu.varna.informationSystem.annotations.Role;
import bg.tu.varna.informationSystem.common.RoleTypes;

import javax.validation.constraints.NotEmpty;

public class UserRequestDto {

    @NotEmpty
    private String username;
    @NotEmpty
    private String password;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Role(enumClass = RoleTypes.class, ignoreCase = true)
    private String roleName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
