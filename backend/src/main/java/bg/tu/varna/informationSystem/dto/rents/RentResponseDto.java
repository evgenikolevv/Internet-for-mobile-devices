package bg.tu.varna.informationSystem.dto.rents;

import bg.tu.varna.informationSystem.dto.clients.ClientResponseDto;
import bg.tu.varna.informationSystem.dto.users.UserResponseDto;
import bg.tu.varna.informationSystem.dto.vehicles.VehicleResponseDto;
import bg.tu.varna.informationSystem.dto.vehicles.VehicleStatusDto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RentResponseDto {

    private Long id;
    private VehicleResponseDto vehicle;
    private ClientResponseDto client;
    private UserResponseDto user;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private BigDecimal finalPrice;
    private Long kilometers;
    private VehicleStatusDto vehicleStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleResponseDto getVehicle() {
        return vehicle;
    }

    public void setVehicle(VehicleResponseDto vehicle) {
        this.vehicle = vehicle;
    }

    public ClientResponseDto getClient() {
        return client;
    }

    public void setClient(ClientResponseDto client) {
        this.client = client;
    }

    public UserResponseDto getUser() {
        return user;
    }

    public void setUser(UserResponseDto user) {
        this.user = user;
    }

    public LocalDateTime getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDateTime dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDateTime getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDateTime dateTo) {
        this.dateTo = dateTo;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Long getKilometers() {
        return kilometers;
    }

    public void setKilometers(Long kilometers) {
        this.kilometers = kilometers;
    }

    public VehicleStatusDto getVehicleStatus() {
        return vehicleStatus;
    }

    public void setVehicleStatus(VehicleStatusDto vehicleStatus) {
        this.vehicleStatus = vehicleStatus;
    }
}
