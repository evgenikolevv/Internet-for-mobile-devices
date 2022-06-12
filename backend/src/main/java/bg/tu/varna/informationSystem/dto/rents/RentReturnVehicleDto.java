package bg.tu.varna.informationSystem.dto.rents;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RentReturnVehicleDto {

    @NotEmpty
    private String vehicleCondition;

    @NotNull
    private Long kilometers;

    @NotEmpty
    private String dateReturned;

    @NotNull
    private Boolean isDamaged;

    public String getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

    public Long getKilometers() {
        return kilometers;
    }

    public void setKilometers(Long kilometers) {
        this.kilometers = kilometers;
    }

    public String getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(String dateReturned) {
        this.dateReturned = dateReturned;
    }

    public Boolean getIsDamaged() {
        return isDamaged;
    }

    public void setIsDamaged(Boolean isDamaged) {
        this.isDamaged = isDamaged;
    }
}
