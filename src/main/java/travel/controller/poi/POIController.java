package travel.controller.poi;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import travel.dao.poi.POI;
import travel.dao.poi.POIImage;
import travel.service.region.RegionService;
import travel.service.serviceImpl.poi.POIImageServiceImpl;
import travel.service.serviceImpl.poi.POIServiceImpl;
import travel.utils.ResultUtil;
import travel.vo.POIImageVo;
import travel.vo.POIVo;
import travel.vo.ResultVo;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/poi")
public class POIController {
    @Autowired
    private POIServiceImpl poiService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private POIImageServiceImpl poiImageService;
    @Cacheable(cacheNames = "pois",key = "1")
    @GetMapping("/list")
    public ResultVo getPoiList(@RequestParam("regionId")String regionId){
        try {
            regionService.findByRegionId(regionId);
            List<POI> poiList = poiService.findByRegionId(regionId);
            List<POIVo> poiVoList = new ArrayList<>();
            for(POI poi : poiList){
                POIVo poiVo = new POIVo();
                //
                List<POIImage> poiImageList = poiImageService.findByPOIId(poi.getPoiId());
                List<POIImageVo> poiImageVoList = new ArrayList<>();
                for(POIImage poiImage: poiImageList){
                    POIImageVo poiImageVo = new POIImageVo();
                    BeanUtils.copyProperties(poiImage,poiImageVo);
                    poiImageVoList.add(poiImageVo);
                }
                BeanUtils.copyProperties(poi, poiVo);
                poiVo.setPoiImageList(poiImageVoList);
                poiVoList.add(poiVo);
            }
            return ResultUtil.success(poiVoList);
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }

}
