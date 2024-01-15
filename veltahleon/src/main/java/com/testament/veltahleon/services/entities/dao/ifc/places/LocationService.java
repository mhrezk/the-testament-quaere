package com.testament.veltahleon.services.entities.dao.ifc.places;

import com.testament.veltahleon.model.entities.places.Location;

import java.util.Collection;

public interface LocationService {

    Collection<Location> getLocations();
    Location getLocationByID(Long id);
    Boolean deleteLocationByID(Long id);
    Location saveLocation(Location location);
    Location updateLocation(Location location);
}
