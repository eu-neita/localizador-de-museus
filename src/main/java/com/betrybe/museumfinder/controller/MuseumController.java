package com.betrybe.museumfinder.controller;

import com.betrybe.museumfinder.dto.MuseumCreationDto;
import com.betrybe.museumfinder.dto.MuseumDto;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.service.MuseumServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Museum controller.
 */
@RestController
@RequestMapping("/museums")
public class MuseumController {
  private final MuseumServiceInterface museumService;

  @Autowired
  public MuseumController(MuseumServiceInterface museumService) {
    this.museumService = museumService;
  }

  /**
   * Museum post createMuseum.
   */
  @PostMapping
  public ResponseEntity<Museum> createMuseum(@RequestBody MuseumCreationDto museumDto) {
    // creating museum dto to Museum
    Museum museum = new Museum();
    Museum createdMuseum = museumService.createMuseum(museum);
    return ResponseEntity.status(HttpStatus.CREATED).body(createdMuseum);
  }

  /**
   * Museum get closestMuseum.
   */
  @GetMapping("/closest")
  public ResponseEntity<MuseumDto> getClosestMuseum(
      @RequestParam("lat") Double latitude,
      @RequestParam("lng") Double longitude,
      @RequestParam("max_dist_km") Double maxDistanceKm) {
    Museum closestMuseum = museumService
        .getClosestMuseum(new Coordinate(latitude, longitude), maxDistanceKm);

    if (closestMuseum != null) {
      MuseumDto museumDto = new MuseumDto(
          closestMuseum.getId(),
          closestMuseum.getName(),
          closestMuseum.getDescription(),
          closestMuseum.getAddress(),
          closestMuseum.getCollectionType(),
          closestMuseum.getSubject(),
          closestMuseum.getUrl(),
          new Coordinate(closestMuseum.getCoordinate()
              .latitude(), closestMuseum.getCoordinate().longitude())
      );

      return ResponseEntity.ok(museumDto);
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
