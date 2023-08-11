package data.controller;

import data.dto.response.ProductDto;
import data.service.ProductService;
import org.ocpsoft.rewrite.annotation.Join;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;

@Scope(value = "session")
@Component(value = "productController")
//@ELBeanName(value = "productController")
@Join(path = "/product", to = "/test.xhtml")
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductDto getById(Long id) {
        return productService.getProduct(id);
    }

}
