package com.testament.veltahleon.services.ifc.places;

import com.testament.veltahleon.model.entities.places.Location;

import java.util.Collection;

public interface LocationService {

    Collection<Location> getLocationsWithPagination(int pageNumber, int numberOfRecords);
    Collection<Location> getLocations();
    Location getLocationByID(Long id);
    Location getLocationByName(String name);
    Boolean deleteLocationByID(Long id);
    Location saveLocation(Location location);
    Location updateLocation(Long id, Location location);
}
