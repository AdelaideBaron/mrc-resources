package com.codeaddi.mrc_resources.testUtils;

import com.codeaddi.mrc_resources.model.enums.BoatType;
import com.codeaddi.mrc_resources.model.enums.EquipmentStatus;
import com.codeaddi.mrc_resources.model.enums.EquipmentType;
import com.codeaddi.mrc_resources.model.enums.RowerLevel;
import com.codeaddi.mrc_resources.model.repository.entity.Blade;
import com.codeaddi.mrc_resources.model.repository.entity.Boat;
import com.codeaddi.mrc_resources.model.repository.entity.ResourceInUse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

public class TestData {
  public class DatesAndTimes {
    public static Instant instantNow = Instant.now();
    public static Instant specificDateInstant =
        Instant.ofEpochMilli(
            LocalDateTime.of(2025, 1, 1, 0, 0).toInstant(ZoneOffset.UTC).toEpochMilli());
    public static Date dateNow = Date.from(instantNow);

    public static Date specificDate = Date.from(specificDateInstant);
  }

  // Todo pujt the blades/boats into another class
  public static Boat boat1 =
      Boat.builder()
              .id(1L)
          .avgCrewWeight(70)
          .boatType(BoatType.COXED_FOUR)
          .name("Ian Marr")
          .minimumRowerLevel(RowerLevel.NOVICE)
          .status(EquipmentStatus.WORKING)
          .build();
  public static Boat boat2 =
          Boat.builder()
                  .id(2L)
                  .avgCrewWeight(70)
                  .boatType(BoatType.COXED_FOUR)
                  .name("Ian Marr")
                  .minimumRowerLevel(RowerLevel.NOVICE)
                  .status(EquipmentStatus.WORKING)
                  .build();
  public static Blade purpleBlades =
      Blade.builder().id(1L).name("Purples").amount(8).status(EquipmentStatus.WORKING).build();
  public static Blade orangeBlades =
      Blade.builder().id(2L).name("Oranges").amount(12).status(EquipmentStatus.WORKING).build();

  public class ResourcesInUse {
    public static ResourceInUse purpleBladeResourceToday =
        ResourceInUse.builder()
            .resource_id(1L)
            .equipmentType(EquipmentType.BLADE)
            .quantity(4)
            .upcomingSessionId(1L)
            .date(DatesAndTimes.dateNow)
            .startTime(LocalTime.NOON)
            .endTime(LocalTime.MIDNIGHT)
            .build();
    public static ResourceInUse purpleBladeResourceSetDate =
        ResourceInUse.builder()
            .resource_id(1L)
            .equipmentType(EquipmentType.BLADE)
            .quantity(4)
            .upcomingSessionId(2L)
            .date(DatesAndTimes.specificDate)
            .startTime(LocalTime.NOON)
            .endTime(LocalTime.MIDNIGHT)
            .build();
    public static ResourceInUse boatResourceToday =
        ResourceInUse.builder()
            .resource_id(1L)
            .equipmentType(EquipmentType.BOAT)
            .quantity(4)
            .upcomingSessionId(1L)
            .date(DatesAndTimes.dateNow)
            .startTime(LocalTime.NOON)
            .endTime(LocalTime.MIDNIGHT)
            .build();
    public static ResourceInUse boatResourceSetDate =
        ResourceInUse.builder()
            .resource_id(1L)
            .equipmentType(EquipmentType.BOAT)
            .quantity(4)
            .upcomingSessionId(1L)
            .date(DatesAndTimes.specificDate)
            .startTime(LocalTime.NOON)
            .endTime(LocalTime.MIDNIGHT)
            .build();

    public static List<ResourceInUse> allResourcesInUse =
        List.of(
            boatResourceToday,
            purpleBladeResourceToday,
            purpleBladeResourceSetDate,
            boatResourceSetDate);
  }
}
