package bg.tu.varna.informationSystem.security;


import javax.security.auth.Subject;
import java.security.Principal;
import java.util.List;

public class UserPrincipal implements Principal {

    private Long id;
    private String userName;
    private List<String> permissions;

    public UserPrincipal(Long id, String userName, List<String> permissions) {
        this.id = id;
        this.userName = userName;
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String getName() {
        return userName;
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }
}