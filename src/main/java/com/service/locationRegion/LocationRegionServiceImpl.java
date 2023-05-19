package com.service.locationRegion;

import com.model.LocationRegion;
import com.repository.jwt.LocationRegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class LocationRegionServiceImpl implements LocationRegionService{

    @Autowired
    private LocationRegionRepository locationRegionRepository;
    @Override
    public List<LocationRegion> findAll() {
        return locationRegionRepository.findAll();
    }

    @Override
    public Optional<LocationRegion> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Boolean existById(Long id) {
        return null;
    }

    @Override
    public LocationRegion save(LocationRegion locationRegion) {
        return locationRegionRepository.save(locationRegion);
    }

    @Override
    public void delete(LocationRegion locationRegion) {
        locationRegionRepository.delete(locationRegion);
    }

    @Override
    public void deleteById(Long deleteById) {
        locationRegionRepository.deleteById(deleteById);
    }
}
