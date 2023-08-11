package data.service.impl;

import data.dto.response.ProductDto;
import data.entity.ProductEntity;
import data.service.ProductService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class ProductServiceImpl implements ProductService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ProductDto getProduct(Long id) {
        ProductEntity productEntity = entityManager.find(ProductEntity.class, id);
        return ProductDto.builder()
                .name(productEntity.getName())
                .quantity(productEntity.getQuantity())
                .price(productEntity.getPrice())
                .build();
    }
}
