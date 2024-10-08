package com.codeaddi.mrc_resources.controller;

import com.codeaddi.mrc_resources.controller.service.ResourceService;
import com.codeaddi.mrc_resources.controller.service.db.BladeService;
import com.codeaddi.mrc_resources.controller.util.DateUtil;
import com.codeaddi.mrc_resources.model.enums.EquipmentType;
import com.codeaddi.mrc_resources.model.repository.entity.Blade;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/blades")
@RestController
@Slf4j
public class BladeController {

  @Autowired private BladeService bladeService;

  @Autowired private ResourceService resourceService;

  EquipmentType equipmentType = EquipmentType.BLADE;

  @GetMapping("/get_all")
  public ResponseEntity<List<Blade>> getAllBlades() {
    log.info("Retrieving all blades");
    return ResponseEntity.ok(bladeService.getAllBlades());
  }

  @GetMapping("/available")
  public ResponseEntity<?> getBladesAvailableAtTime(@RequestParam String date) {

    Date dateParsed = DateUtil.getDateFromString(date);

    if (dateParsed == null) {
      log.warn("Invalid date passed: {}", date);
      return ResponseEntity.badRequest()
          .body("Invalid/no date supplied. Please provide in the format dd/mm/yyyy");
    } else {
      return ResponseEntity.ok(resourceService.getResourcesForDate(dateParsed, equipmentType));
    }
  }
}
