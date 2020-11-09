package travel.service.serviceImpl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class POIServiceImplTest {
    @Autowired
    private POIServiceImpl pioService;
    @Test
    void findByRegionId() {
        String regionId = "123455";
        pioService.findByRegionId(regionId);
    }
}