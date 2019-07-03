package com.otomoto.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.app.utils.AnnouncementSearchFields;
import com.otomoto.enums.CarColor;
import com.otomoto.enums.Currency;
import com.otomoto.enums.FuelType;
import com.otomoto.enums.VehicleSubtype;
import com.otomoto.enums.VehicleType;

@Entity
public class Announcement {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_Announcement")
	@SequenceGenerator(name="seq_Announcement",sequenceName="seq_Announcement")
	private Long id;
	
	@NotBlank
	@Size(max=200)
	private String title;
	
	@Size(max=3000)
	private String description;
	
	@NotNull
	@ManyToOne
	private Manufacturer manufacturer;
	
	@NotNull
	@ManyToOne
	private VehicleModel vehicleModel;
	
	@NotNull
	@Min(1900)
	@Max(2100)
	private Short productionYear;
	
	@NotNull
	@Min(0)
	@Max(999_999_999)
	//@Size(min=0, max=999_999_999, message="{validation.rangeError}")
	private Integer mileage;
	
	@Size(max=20)
	private String vin;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private FuelType fuelType;
 
	@NotNull
	@Digits(integer=9, fraction=2)
	private BigDecimal price;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private Currency currency;
	
	@NotNull
	private Boolean netPrice;
	
	@NotNull
	private Boolean priceNegotiate;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private VehicleType vehicleType;
	
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private VehicleSubtype vehicleSubtype;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private CarColor carColor;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "announcement")
	private List<Picture> pictures = new ArrayList<Picture>();
	
	@NotNull
	@ManyToOne(cascade ={CascadeType.PERSIST})
	@JoinColumn(name = "user_id")
	private User user;
	
	private Date creationDate;
	
	private Date deactivationDate;
	
	//@Size(min=0,max=999_999, message="{validation.rangeError}")
	@Min(0)
	@Max(value = 50_000, message="{validation.rangeError}")
	private Integer engineCapacity;
	
	//@Size(min=0,max=999_999, message="{validation.rangeError}")
	@Max(value=50000, message="{validation.rangeError}")
	private Integer enginePower;
	
	private Boolean accidents;
	
	private Boolean firstOwner;
	
	private Boolean damaged;
	
	private Byte doors;
	
	@Transient
	private AnnouncementSearchFields searchFields = new AnnouncementSearchFields();
	
	public void prepareFiledsForSearch() {
		if(title == null)
			title = "";

		if(vehicleType == null)
			setVehicleType(VehicleType.CAR);
		
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Manufacturer getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public VehicleModel getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(VehicleModel vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public Short getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(Short productionYear) {
		this.productionYear = productionYear;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Boolean getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(Boolean netPrice) {
		this.netPrice = netPrice;
	}

	public Boolean getPriceNegotiate() {
		return priceNegotiate;
	}

	public void setPriceNegotiate(Boolean priceNegotiate) {
		this.priceNegotiate = priceNegotiate;
	}

	public CarColor getCarColor() {
		return carColor;
	}

	public void setCarColor(CarColor carColor) {
		this.carColor = carColor;
	}

	public List<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(List<Picture> pictures) {
		this.pictures = pictures;
	}

	public VehicleType getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	public VehicleSubtype getVehicleSubtype() {
		return vehicleSubtype;
	}

	public void setVehicleSubtype(VehicleSubtype vehicleSubtype) {
		this.vehicleSubtype = vehicleSubtype;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getDeactivationDate() {
		return deactivationDate;
	}

	public void setDeactivationDate(Date deactivationDate) {
		this.deactivationDate = deactivationDate;
	}

	public Boolean getAccidents() {
		return accidents;
	}

	public void setAccidents(Boolean accidents) {
		this.accidents = accidents;
	}

	public Boolean getFirstOwner() {
		return firstOwner;
	}

	public void setFirstOwner(Boolean firstOwner) {
		this.firstOwner = firstOwner;
	}

	public Boolean getDamaged() {
		return damaged;
	}

	public void setDamaged(Boolean damaged) {
		this.damaged = damaged;
	}

	public Integer getEngineCapacity() {
		return engineCapacity;
	}

	public void setEngineCapacity(Integer engineCapacity) {
		this.engineCapacity = engineCapacity;
	}

	public Integer getEnginePower() {
		return enginePower;
	}

	public void setEnginePower(Integer enginePower) {
		this.enginePower = enginePower;
	}

	public AnnouncementSearchFields getSearchFields() {
		return searchFields;
	}

	public void setSearchFields(AnnouncementSearchFields searchFields) {
		this.searchFields = searchFields;
	}

	public Byte getDoors() {
		return doors;
	}

	public void setDoors(Byte doors) {
		this.doors = doors;
	}

	
}
