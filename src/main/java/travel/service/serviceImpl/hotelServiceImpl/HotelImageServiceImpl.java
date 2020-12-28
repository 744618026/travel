package travel.service.serviceImpl.hotelServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import travel.dao.hotel.Hotel;
import travel.dao.hotel.HotelImage;
import travel.enums.HotelEnum;
import travel.enums.ResultEnum;
import travel.exceptions.NullException;
import travel.mapper.hotel.HotelImageMapper;
import travel.mapper.hotel.HotelMapper;
import travel.service.hotel.HotelImageService;
import travel.service.hotel.HotelService;
import travel.utils.FileNameUtil;
import travel.utils.FileUploadUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class HotelImageServiceImpl implements HotelImageService {
    @Autowired
    private HotelImageMapper hotelImageMapper;
    @Autowired
    private HotelMapper hotelMapper;
    @Override
    public List<HotelImage> findByHotelIdAndCategory(String hotelId, Integer category) {
        Hotel hotel = hotelMapper.findByHotelId(hotelId);
        if(hotel==null){
            throw new NullException(ResultEnum.HOTEL_NOT_EXISTS.getMessage());
        }
        List<HotelImage> hotelImageList = hotelImageMapper.findByHotelIdAndCategory(hotelId,category);
        return hotelImageList;
    }

    @Override
    public boolean insert(HotelImage hotelImage){
        String hotelId = hotelImage.getHotelId();
        Hotel hotel = hotelMapper.findByHotelId(hotelId);
        if(hotel==null){
            return false;
        }
        int result = hotelImageMapper.insert(hotelImage);
        if(result>0){
            return true;
        }
        return false;
    }

    @Override
    public List<HotelImage> findByHotelIdAndProductId(String hotelId, String productId) {
        return hotelImageMapper.findByHotelIdAndProductId(hotelId,productId);
    }

    @Override
    public HotelImage findByImageId(Integer imageId) {
        return hotelImageMapper.findByImageId(imageId);
    }

    @Override
    public boolean deleteByImageId(Integer imageId) {
        int r = hotelImageMapper.deleteByImageId(imageId);
        if(r>0){
            return true;
        }
        return false;
    }
}
