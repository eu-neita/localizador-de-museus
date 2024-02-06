package com.betrybe.museumfinder.exception;

/**
 * Exception thrown when an invalid coordinate is encountered.
 */
public class InvalidCoordinateException extends RuntimeException{
  public InvalidCoordinateException(String message) {
    super(message);
  }
}
