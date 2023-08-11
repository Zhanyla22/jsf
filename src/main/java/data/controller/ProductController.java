package data.controller;

import data.dto.response.ProductDto;
import data.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;

@Component
@ViewScoped
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductDto getById(Long id) {
        return productService.getProduct(id);
    }

}
