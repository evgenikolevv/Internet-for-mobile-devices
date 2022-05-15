package bg.tu.varna.informationSystem.dto.companies;

import javax.validation.constraints.NotEmpty;

public class CompanyRequestDto {

    @NotEmpty
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
