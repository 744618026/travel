package travel.controller.poi;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import travel.authority.JwtUtils;
import travel.configuration.RedisBasePrefix;
import travel.dao.poi.POI;
import travel.dao.poi.POIComment;
import travel.dao.poi.POIImage;
import travel.enums.ResultEnum;
import travel.service.region.RegionService;
import travel.service.serviceImpl.poi.POICommentServiceImpl;
import travel.service.serviceImpl.poi.POIImageServiceImpl;
import travel.service.serviceImpl.poi.POIServiceImpl;
import travel.utils.CommentKeyUtil;
import travel.utils.PageVoUtil;
import travel.utils.RedisUtil;
import travel.utils.ResultUtil;
import travel.vo.*;

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
    @Autowired
    private RedisTemplate redisTemplate;

    private Logger LOG = LoggerFactory.getLogger(POIController.class);
    @GetMapping("/list")
    public ResultVo getPoiList(@RequestParam("regionId")String regionId,@RequestParam(value = "page",defaultValue = "1")Integer page,
                               @RequestParam(value = "size",defaultValue = "15")Integer size){
        RedisBasePrefix prefix = new RedisBasePrefix("travel".concat(":").concat(":").concat("poi").concat(":").concat(regionId));
        ResultVo resultVo = (ResultVo) RedisUtil.get(prefix,page.toString(),redisTemplate);
        if(resultVo==null){
            try {
                regionService.findByRegionId(regionId);
                List<POI> poiList = poiService.findByRegionId(regionId,page,size);
                List<POIVo> poiVoList = new ArrayList<>();
                for(POI poi : poiList) {
                    POIVo poiVo = new POIVo();
                    BeanUtils.copyProperties(poi, poiVo);
                    poiVoList.add(poiVo);
                }
                ResultVo resultVo1 = ResultUtil.success(PageVoUtil.getPage(page,size,poiList,poiVoList));
                RedisUtil.set(redisTemplate,prefix,page.toString(),resultVo1);
                return resultVo1;
            }catch (Exception e){
                return ResultUtil.fail(e.getMessage());
            }
        }else{
            return resultVo;
        }
    }
    @GetMapping("/comments")
    public ResultVo getPOIComment(@RequestParam("poiId")String poiId,@RequestParam(value = "page",defaultValue = "1")Integer page,
                                  @RequestParam(value = "size",defaultValue = "15")Integer size){
        RedisBasePrefix prefix = new RedisBasePrefix("travel".concat(":").concat(":").concat("poi").concat(":").concat("comment").concat(":").concat(poiId));
        ResultVo resultVo = (ResultVo) RedisUtil.get(prefix,page.toString(),redisTemplate);
        if(resultVo==null){
            try{
                List<POIComment> poiCommentList = poiCommentService.findByPOIId(poiId,page,size);
                List<POICommentVo> poiCommentVoList = new ArrayList<>();
                for(POIComment poiComment : poiCommentList){
                    POICommentVo poiCommentVo = new POICommentVo();
                    BeanUtils.copyProperties(poiComment,poiCommentVo);
                    poiCommentVoList.add(poiCommentVo);
                }
                resultVo = ResultUtil.success(PageVoUtil.getPage(page,size,poiCommentList,poiCommentVoList));
                RedisUtil.set(redisTemplate,prefix,page.toString(),resultVo);
            }catch (Exception e){
                return ResultUtil.fail(e.getMessage());
            }
        }
        return resultVo;
    }
    @PostMapping("/comment")
    public ResultVo PoiComment(@RequestParam("username")String username,@RequestParam("token")String token,@RequestParam("content")String content,
                               @RequestParam("poiId")String poiId){
        RedisBasePrefix prefix = new RedisBasePrefix("travel".concat(":").concat(":").concat("poi").concat(":").concat("comment").concat(":").concat(poiId));
        boolean re = RedisUtil.deleteByPrefix(prefix,redisTemplate);
        if(!re){
            LOG.info("request:/poi/comment清除缓存失败！");
        }
//        LOG.info("request:/poi/comment清除缓存成功！");
        String tokens = token.replace(JwtUtils.TOKEN_PREFIX,"");
        if(JwtUtils.isExpiration(tokens)){
            return ResultUtil.fail(ResultEnum.TOKEN_EXPIRED.getMessage());
        }
        String usernames = JwtUtils.getUsername(tokens);
        if(username.equals(usernames)){
            POIComment poiComment = new POIComment();
            poiComment.setContent(content);
            poiComment.setUserName(username);
            poiComment.setPoiId(poiId);
            poiComment.setPoiCommentId(CommentKeyUtil.getUniqueKey());
            boolean exist = poiCommentService.check(username,poiId);

            boolean result = poiCommentService.insert(poiComment);
            if(result){
                return ResultUtil.success();
            }else {
                return ResultUtil.fail();
            }
        }else{
            return ResultUtil.fail(ResultEnum.USER_NOT_MATCH.getMessage());
        }
    }
    @GetMapping("/images")
    public ResultVo getImages(@RequestParam("poiId")String poiId){
        RedisBasePrefix prefix = new RedisBasePrefix("travel".concat(":").concat(":").concat("poi").concat(":").concat("images"));
        ResultVo resultVo = (ResultVo) RedisUtil.get(prefix,poiId,redisTemplate);
        if(resultVo==null){
            List<POIImage> poiImageList = poiImageService.findByPOIId(poiId);
            List<POIImageVo> poiImageVoList = new ArrayList<>();
            for(POIImage poiImage: poiImageList){
                POIImageVo poiImageVo = new POIImageVo();
                BeanUtils.copyProperties(poiImage,poiImageVo);
                poiImageVoList.add(poiImageVo);
            }
            resultVo = ResultUtil.success(poiImageVoList);
            boolean result = RedisUtil.set(redisTemplate,prefix,poiId,resultVo);
            if(!result){
                LOG.info("request:/poi/images缓存失败！");
            }
            LOG.info("request:/poi/images缓存成功！");
        }
        LOG.info("request:/poi/images获取缓存成功！");
        return resultVo;
    }
}
