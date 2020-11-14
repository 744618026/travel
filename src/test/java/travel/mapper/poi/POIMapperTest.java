package travel.mapper.poi;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import travel.dao.poi.POI;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class POIMapperTest {
    @Autowired
    private POIMapper poiMapper;
    @Test
    void findByRegionId() {
       List<POI> poi =  poiMapper.findByRegionId("12345");
       poi.size();
       System.out.println(poi);
    }

    @Test
    void findByPOIId() {
    }

    @Test
    void insert() {
    }

    @Test
    void delete() {
    }

    @Test
    void updatePOIStock() {
    }

    @Test
    void updatePOITicketPrice() {
    }
}