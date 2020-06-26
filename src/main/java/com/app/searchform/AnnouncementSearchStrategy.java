package com.app.searchform;

import com.app.entity.Announcement;
import com.app.entity.VehicleModel;
import com.app.enums.BooleanValuesForDropDown;
import com.app.enums.PaginationPageSize;
import com.app.enums.SearchEngineDropDownValues;
import com.app.enums.VehicleSubtype;
import com.app.projection.ManufacturerProjection;
import com.app.repository.AnnouncementRepository;
import com.app.repository.ManufacturerRepository;
import com.app.repository.VehicleModelRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class AnnouncementSearchStrategy implements SearchStrategy<Announcement> {

    private final AnnouncementRepository announcementRepository;

    private final VehicleModelRepository vehicleModelRepository;

    private final ManufacturerRepository manufacturerRepository;

    public AnnouncementSearchStrategy(AnnouncementRepository announcementRepository, VehicleModelRepository vehicleModelRepository, ManufacturerRepository manufacturerRepository) {
        this.announcementRepository = announcementRepository;
        this.vehicleModelRepository = vehicleModelRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public Page<Announcement> loadData(PageRequest pageRequest, Predicate predicate) {
        return announcementRepository.findByPredicatesAndLoadMainPicture(pageRequest, predicate);
    }

    @Override
    public Map<String, Object> prepareDataForHtmlElements(Announcement announcement) {
        Map<String, Object> model = new HashMap<>();

        model.putAll(prepareModelAttributesFromEnums());

        List<ManufacturerProjection> manufacturerList = manufacturerRepository.findByVehicleType(announcement.getVehicleType());
        model.put("manufacturerList", manufacturerList);

        model.putAll(prepareVehicleModelsIfManufacturerIsSet(announcement, manufacturerList));

        model.put("vehicleSubtypeList", VehicleSubtype.getVehicleSubtypesByVehicleType(announcement.getVehicleType()));

        return model;
    }

    private Map<String,Object> prepareVehicleModelsIfManufacturerIsSet(Announcement announcement, List<ManufacturerProjection> manufacturerList) {
        Map<String, Object> model = new HashMap<>();
        //  Optional<Long> manufacturerId = Optional.ofNullable(announcement.getManufacturerId()).or(() -> manufacturerList.stream().findFirst().map(ManufacturerProjection::getId));
        Optional<Long> manufacturerId = Optional.ofNullable(announcement.getManufacturerId());

        if (manufacturerId.isPresent()) {
            List<VehicleModel> vehicleModels = vehicleModelRepository.findByManufacturerIdAndVehicleType(manufacturerId.get(), announcement.getVehicleType());
            model.put("vehicleModelList", vehicleModels);

            if (announcement.getVehicleModel() == null)
                vehicleModels.stream().findAny().ifPresent(e -> {
                    // o ten link http://localhost:8081/otomoto/announcement/list?manufacturerId=-2 zwraca zle jak jest otomentowae
                    //      announcement.setVehicleModel(e);
                });
        }

        return model;
    }

    private Map<String,Object> prepareModelAttributesFromEnums() {
        Map<String, Object> model = new HashMap<>();

        model.put("pageSizes", PaginationPageSize.LIST);
        model.put("prices", SearchEngineDropDownValues.CAR_PRICES);
        model.put("mileages", SearchEngineDropDownValues.MILEAGES);
        model.put("engineCapacities", SearchEngineDropDownValues.ENGINE_CAPACITIES);
        model.put("enginePowers", SearchEngineDropDownValues.ENGINE_POWERS);
        model.put("doors", SearchEngineDropDownValues.DOORS);
        model.put("booleanValues", BooleanValuesForDropDown.values());

        return model;
    }

}


