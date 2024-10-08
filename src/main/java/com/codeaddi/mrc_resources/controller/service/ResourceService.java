package com.codeaddi.mrc_resources.controller.service;

import com.codeaddi.mrc_resources.controller.service.db.BladeService;
import com.codeaddi.mrc_resources.controller.service.db.BoatService;
import com.codeaddi.mrc_resources.controller.service.db.ResourceInUseService;
import com.codeaddi.mrc_resources.model.enums.EquipmentType;
import com.codeaddi.mrc_resources.model.http.ResourceUseDTO;
import com.codeaddi.mrc_resources.model.repository.entity.Blade;
import com.codeaddi.mrc_resources.model.repository.entity.Boat;
import com.codeaddi.mrc_resources.model.repository.entity.ResourceInUse;

import java.util.*;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ResourceService { // Todo, maybe delete this one

    @Autowired
    BladeService bladeService;
    @Autowired
    BoatService boatService;
    @Autowired
    ResourceInUseService resourceInUseService;

    public List<ResourceUseDTO<Object>> getResourcesForDate(
            Date date, EquipmentType equipmentType) { // Todo take in type opf resource and filter by that

        List<?> allBlades = equipmentType.equals(EquipmentType.BLADE) ? bladeService.getAllBlades() : boatService.getAllBoats();
        List<ResourceInUse> resourcesInUseOnDate = resourceInUseService.getAllResourceInUseForDate(date, equipmentType);

        List<ResourceUseDTO<Object>> allResourcesOnDate = new ArrayList<>();

        for (Object resource : allBlades) {
            Long id = getIdFromResource(resource);

            List<ResourceInUse> resourcesInUseOnDay =
                    getResourcesInUseForThisResource(id, resourcesInUseOnDate);
            allResourcesOnDate.add(
                    ResourceUseDTO.builder().resource(resource).inUseOnDate(resourcesInUseOnDay).build());
        }
        return allResourcesOnDate;
    }

    private static Long getIdFromResource(Object resource) {
        Long id = 0L;
        if (resource instanceof Blade) {
            id = ((Blade) resource).getId();
        } else if (resource instanceof Boat) {
            id = ((Boat) resource).getId();
        }
        return id;
    }

    private List<ResourceInUse> getResourcesInUseForThisResource(
            Long id, List<ResourceInUse> bladesInUseOnThisDate) {
        return bladesInUseOnThisDate.stream()
                .filter(resource -> resource.getResource_id().equals(id))
                .toList();
    }

}
