package travel.service.serviceImpl.hotelServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import travel.dao.hotel.Product;
import travel.mapper.hotel.ProductMapper;
import travel.service.hotel.ProductService;
import travel.utils.KeyUtil;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> findByHotelId(String hotelId) {
        return productMapper.findByHotelId(hotelId);
    }
    @Override
    public boolean insert(Product product) {
        int result = productMapper.insert(product);
        if(result >0){
            return true;
        }
        return false;
    }
    @Override
    public boolean update(Product product) {
        int result = productMapper.update(product);
        if(result>0){
            return true;
        }
        return false;
    }
}
