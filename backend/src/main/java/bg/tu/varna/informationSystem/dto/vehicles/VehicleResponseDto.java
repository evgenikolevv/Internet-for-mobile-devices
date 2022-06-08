package bg.tu.varna.informationSystem.dto.vehicles;

import bg.tu.varna.informationSystem.dto.carclasses.CarClassDto;
import bg.tu.varna.informationSystem.dto.categories.CategoryDto;
import bg.tu.varna.informationSystem.dto.companies.CompanyResponseDto;
import bg.tu.varna.informationSystem.dto.engines.EngineDto;
import bg.tu.varna.informationSystem.dto.vehicledetails.VehicleDetailsDto;

public class VehicleResponseDto {

    private Long id;
    private String make;
    private String model;
    private Long year;
    private CarClassDto carClass;
    private CategoryDto category;
    private EngineDto engine;
    private CompanyResponseDto company;
    private VehicleDetailsDto vehicleDetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public CarClassDto getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClassDto carClass) {
        this.carClass = carClass;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public EngineDto getEngine() {
        return engine;
    }

    public void setEngine(EngineDto engine) {
        this.engine = engine;
    }

    public CompanyResponseDto getCompany() {
        return company;
    }

    public void setCompany(CompanyResponseDto company) {
        this.company = company;
    }

    public VehicleDetailsDto getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(VehicleDetailsDto vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }
}
