package bg.tu.varna.informationSystem.common;

public enum RoleTypes {
    AGENT,
    MANAGER,
    ADMIN;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
