package com.app.dto;

import com.app.entity.VehicleModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Setter
@Getter
public class ManufacturerDto {
    private Long id;
    @NotBlank
    private String name;
    private List<VehicleModel> vehicleModel;
    private String vehicleModelName;
}
