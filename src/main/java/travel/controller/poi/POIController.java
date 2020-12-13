package travel.controller.poi;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import travel.dao.poi.POI;
import travel.dao.poi.POIComment;
import travel.dao.poi.POIImage;
import travel.service.region.RegionService;
import travel.service.serviceImpl.poi.POICommentServiceImpl;
import travel.service.serviceImpl.poi.POIImageServiceImpl;
import travel.service.serviceImpl.poi.POIServiceImpl;
import travel.utils.ResultUtil;
import travel.vo.POICommentVo;
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
    @Autowired
    private POICommentServiceImpl poiCommentService;

    @GetMapping("/list")
    @Cacheable(cacheNames = "poi",key = "'poi/list?'+#regionId+'?'+#page")
    public ResultVo getPoiList(@RequestParam("regionId")String regionId,@RequestParam(value = "page",defaultValue = "1")Integer page,
                               @RequestParam(value = "size",defaultValue = "15")Integer size){
        try {
            regionService.findByRegionId(regionId);
            List<POI> poiList = poiService.findByRegionId(regionId,page,size);
            List<POIVo> poiVoList = new ArrayList<>();
            for(POI poi : poiList){
                POIVo poiVo = new POIVo();
                BeanUtils.copyProperties(poi, poiVo);
                poiVoList.add(poiVo);
            }
            return ResultUtil.success(poiVoList);
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }
    @GetMapping("/totalPage")
    @Cacheable(cacheNames = "poi/totalPage")
    public ResultVo totalPage(@RequestParam("region")String regionId,@RequestParam(value = "size",defaultValue = "15")Integer size){
        try{
            Integer poiList = poiService.findByRegionId(regionId);
            double x = (poiList*1.0)/(size*1.0);
            Double d = Math.ceil(x);
            Integer page = d.intValue();
            return ResultUtil.success(page);
        }catch (Exception e){
            return ResultUtil.success(0);
        }
    }
    @GetMapping("/comments")
    public ResultVo getPOIComment(@RequestParam("POIId")String poiId){
        try{
            List<POIComment> poiCommentList = poiCommentService.findByPOIId(poiId);
            List<POICommentVo> poiCommentVoList = new ArrayList<>();
            for(POIComment poiComment : poiCommentList){
                POICommentVo poiCommentVo = new POICommentVo();
                BeanUtils.copyProperties(poiComment,poiCommentVo);
                poiCommentVoList.add(poiCommentVo);
            }
            return ResultUtil.success(poiCommentVoList);
        }catch (Exception e){
            return ResultUtil.fail(e.getMessage());
        }
    }
    @Cacheable(cacheNames = "poi/images",key = "#poiId")
    @GetMapping("/images")
    public ResultVo getImages(@RequestParam("poiId")String poiId){
        List<POIImage> poiImageList = poiImageService.findByPOIId(poiId);
        List<POIImageVo> poiImageVoList = new ArrayList<>();
        for(POIImage poiImage: poiImageList){
            POIImageVo poiImageVo = new POIImageVo();
            BeanUtils.copyProperties(poiImage,poiImageVo);
            poiImageVoList.add(poiImageVo);
        }
        return ResultUtil.success(poiImageVoList);
    }
}
