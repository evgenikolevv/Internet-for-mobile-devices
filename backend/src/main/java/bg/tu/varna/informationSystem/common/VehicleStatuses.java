package bg.tu.varna.informationSystem.common;

public enum VehicleStatuses {

    GOOD_CONDITION("Колата е в добро състояние."),
    BAD_CONDITION("Колата е повредена.");

    private final String text;

    VehicleStatuses(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
