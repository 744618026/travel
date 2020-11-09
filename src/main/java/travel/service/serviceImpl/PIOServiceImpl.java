package travel.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import travel.dao.PIO;
import travel.service.PIOService;

import java.util.List;

public class PIOServiceImpl implements PIOService {
    @Autowired
    private PIOService pioService;
    @Override
    public List<PIO> findByRegionId(String regionId) {
        return pioService.findByRegionId(regionId);
    }
}
