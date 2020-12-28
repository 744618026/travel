package travel.service.hotel;


import travel.dao.hotel.Product;

import java.util.List;

public interface ProductService {
    List<Product> findByHotelId(String hotelId);

    boolean insert(Product product);

    boolean update(Product product);
}
