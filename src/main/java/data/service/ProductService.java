package data.service;

import data.dto.response.ProductDto;

public interface ProductService {

    ProductDto getProduct(Long id);
}
