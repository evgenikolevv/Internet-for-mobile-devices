package bg.tu.varna.informationSystem.common;

public enum VehicleStatus {

    GOOD_CONDITION("Колата е в добро състояние."),
    BAD_CONDITION("Колата е повредена.");

    private final String text;

    VehicleStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
