package bg.tu.varna.informationSystem.dto.rents;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RentRequestDto {

    @NotEmpty
    private String dateFrom;

    @NotEmpty
    private String dateTo;

    @NotNull
    private Long clientId;

    @NotNull
    private Long vehicleId;

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}