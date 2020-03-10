package com.app.entity;

import com.app.enums.VehicleType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class VehicleModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_VehicleModel")
	@SequenceGenerator(name = "seq_VehicleModel", sequenceName = "seq_VehicleModel")
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	@ManyToOne
	private Manufacturer manufacturer;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private VehicleType vehicleType;
	
/*	@Enumerated(EnumType.STRING)
	private VehicleSubtype vehicleSubtype;*/
	
	@Transient
	private Boolean toDelete = false;
	

	public VehicleModel(@NotNull Long id,@NotNull String name, @NotNull Manufacturer manufacturer, @NotNull VehicleType vehicleType) {
		super();
		this.id = id;
		this.name = name;
		this.manufacturer = manufacturer;
		this.vehicleType = vehicleType;
	}

	public VehicleModel(@NotNull String name, @NotNull Manufacturer manufacturer, @NotNull VehicleType vehicleType) {
		super();
		this.name = name;
		this.manufacturer = manufacturer;
		this.vehicleType = vehicleType;
	}
	
	public VehicleModel() {
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public Boolean getToDelete() {
		return toDelete;
	}

	public void setToDelete(Boolean toDelete) {
		this.toDelete = toDelete;
	}

/*	public VehicleSubtype getVehicleSubtype() {
		return vehicleSubtype;
	}

	public void setVehicleSubtype(VehicleSubtype vehicleSubtype) {
		this.vehicleSubtype = vehicleSubtype;
	}*/

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	
}
