package edu.illinois.cs.cs124.ay2022.mp.models;

import java.util.ArrayList;
import java.util.List;

/*
 * Model storing information about a place retrieved from the backend server.
 *
 * You will need to understand some of the code in this file and make changes starting with MP1.
 */
@SuppressWarnings("unused")
public final class Place {
  /*
   * The Jackson JSON serialization library that we are using requires an empty constructor.
   * So don't remove this!
   */
  public Place() {}

  public Place(
      final String setId,
      final String setName,
      final double setLatitude,
      final double setLongitude,
      final String setDescription) {
    id = setId;
    name = setName;
    latitude = setLatitude;
    longitude = setLongitude;
    description = setDescription;
  }

  // ID of the place
  private String id;

  public String getId() {
    return id;
  }

  // Name of the person who submitted this favorite place
  private String name;

  public String getName() {
    return name;
  }

  // Latitude and longitude of the place
  private double latitude;

  public double getLatitude() {
    return latitude;
  }

  private double longitude;

  public double getLongitude() {
    return longitude;
  }

  // Description of the place
  private static String description;

  public String getDescription() {
    return description;
  }
  public static List<Place> search(final List<Place> places, final String search) {

    if (places == null || search == null) {
      throw new IllegalArgumentException();
    }
    if (places.size() == 0 || search.trim().isEmpty()) {
      return places;
    }
    List<Place> newList = new ArrayList<>();
    for (Place p : places) {
      String newDesc = p.getDescription();
      newDesc = newDesc.replaceAll("\\.", " ").replaceAll("!", " ")
          .replaceAll("\\?", " ").replaceAll(",", " ")
          .replaceAll(":", " ").replaceAll(";", " ")
          .replaceAll("/", " ");
      String[] parts = newDesc.split(" ");
      for (String s : parts) {
        s = s.replaceAll("[^a-zA-Z0-9]", "");
        if (s.trim().equalsIgnoreCase(search.trim())) {
          newList.add(p);
          break;
        }
      }
    }
    return newList;
  }
}
