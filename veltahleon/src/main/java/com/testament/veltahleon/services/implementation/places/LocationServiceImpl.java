package com.testament.veltahleon.services.implementation.places;

import com.testament.veltahleon.model.entities.places.Location;
import com.testament.veltahleon.repositories.places.LocationRepository;
import com.testament.veltahleon.services.ifc.places.LocationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LocationServiceImpl implements LocationService {

    @Autowired
    private LocationRepository locationRepository;


    @Override
    public Collection<Location> getLocationsWithPagination(int pageNumber, int numberOfRecords) {
        return locationRepository.findAll(PageRequest.of(pageNumber, numberOfRecords)).toList();
    }

    @Override
    public Collection<Location> getLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location getLocationByID(Long id) {
        return locationRepository.findById(id).orElseThrow();
    }

    @Override
    public Location getLocationByName(String name) {
        return locationRepository.findByName(name);
    }

    @Override
    public Boolean deleteLocationByID(Long id) {
        locationRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location updateLocation(Long id, Location location) {
        Location newLocation = locationRepository.findById(id).orElseThrow();

        if(location.getName() != null && newLocation.getName() != location.getName()) {
            newLocation.setName(location.getName());
        }

        if(location.getDescription() != null && newLocation.getDescription() != location.getDescription()) {
            newLocation.setDescription(location.getDescription());
        }

        if(location.getNation() != null && newLocation.getNation() != location.getNation()) {
            newLocation.setNation(location.getNation());
        }

        return locationRepository.save(newLocation);
    }
}
