package travel.service.hotel;

import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;
import travel.dao.hotel.Hotel;
import travel.dao.hotel.HotelImage;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface HotelImageService {

    List<HotelImage> findByHotelIdAndCategory(String hotelId,Integer category);

    boolean insert(String hotelId, MultipartFile file, HttpServletRequest request) throws IOException;

    List<HotelImage>  findByHotelIdAndProductId(String hotelId,String productId);

    HotelImage findByImageId(Integer imageId);

    boolean deleteByImageId(Integer imageId);
}
