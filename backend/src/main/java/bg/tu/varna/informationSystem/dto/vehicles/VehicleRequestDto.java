package bg.tu.varna.informationSystem.dto.vehicles;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class VehicleRequestDto {

    @NotEmpty
    private String make;

    @NotEmpty
    private String model;

    @NotNull
    private Long year;

    @NotNull
    private Long carClassId;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long engineId;

    @NotNull
    private Long companyId;

    @NotEmpty
    private String doors;

    @NotNull
    private BigDecimal dayPrice;

    @NotNull
    private BigDecimal kilometerPrice;

    @NotNull
    private Boolean airConditioner;

    @NotEmpty
    private String photoUrl;

    @NotNull
    private Boolean smoke;


    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Long getCarClassId() {
        return carClassId;
    }

    public void setCarClassId(Long carClassId) {
        this.carClassId = carClassId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getEngineId() {
        return engineId;
    }

    public void setEngineId(Long engineId) {
        this.engineId = engineId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getDoors() {
        return doors;
    }

    public void setDoors(String doors) {
        this.doors = doors;
    }

    public BigDecimal getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(BigDecimal dayPrice) {
        this.dayPrice = dayPrice;
    }

    public BigDecimal getKilometerPrice() {
        return kilometerPrice;
    }

    public void setKilometerPrice(BigDecimal kilometerPrice) {
        this.kilometerPrice = kilometerPrice;
    }

    public Boolean getAirConditioner() {
        return airConditioner;
    }

    public void setAirConditioner(Boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public Boolean getSmoke() {
        return smoke;
    }

    public void setSmoke(Boolean smoke) {
        this.smoke = smoke;
    }
}
