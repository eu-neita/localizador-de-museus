package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.database.MuseumFakeDatabase;
import com.betrybe.museumfinder.exception.InvalidCoordinateException;
import com.betrybe.museumfinder.exception.MuseumNotFoundException;
import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import com.betrybe.museumfinder.util.CoordinateUtil;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Museum service.
 */
@Service
public class MuseumService implements MuseumServiceInterface {
  private final MuseumFakeDatabase museumDatabase;

  @Autowired
  public MuseumService(MuseumFakeDatabase museumDatabase) {
    this.museumDatabase = museumDatabase;
  }

  @Override
  public Museum getClosestMuseum(Coordinate coordinate, Double maxDistance) {
    boolean isValid = CoordinateUtil.isCoordinateValid(coordinate);
    if (isValid) {
      throw new MuseumNotFoundException("coordinate is invalid");
    }
    Optional<Museum> closestMuseum = museumDatabase.getClosestMuseum(coordinate, maxDistance);
    if (closestMuseum.isEmpty()) {
      throw new MuseumNotFoundException("not found museum");
    }
    return closestMuseum.get();
  }

  @Override
  public Museum createMuseum(Museum museum) {
    if (!CoordinateUtil.isCoordinateValid(museum.getCoordinate())) {
      throw new InvalidCoordinateException("Invalid coordinates");
    }
    return museumDatabase.saveMuseum(museum);
  }

  @Override
  public Museum getMuseum(Long id) {
    return null;
  }
}
