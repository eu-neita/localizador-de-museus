package com.betrybe.museumfinder.service;

import com.betrybe.museumfinder.model.Coordinate;
import com.betrybe.museumfinder.model.Museum;
import java.util.Optional;

/**
 * Interface for Museum service class.
 */
public interface MuseumServiceInterface {

  Optional<Museum> getClosestMuseum(Coordinate coordinate, Double maxDistance);

  Optional<Optional<Museum>> getClosestMuseum(Coordinate coordinate, double maxDistanceKm);

  Museum createMuseum(Museum museum);

  Museum getMuseum(Long id);
}
