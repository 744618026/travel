package travel.service;

import travel.dao.PIO;

import java.util.List;

public interface PIOService {
    List<PIO> findByRegionId(String regionId);
}
