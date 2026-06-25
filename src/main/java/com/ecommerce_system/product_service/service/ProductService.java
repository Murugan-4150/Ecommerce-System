package com.ecommerce_system.product_service.service;

import com.ecommerce_system.product_service.dto.RequestProductDto;
import com.ecommerce_system.product_service.dto.ResponseProductDto;
import com.ecommerce_system.product_service.entity.Product;
import com.ecommerce_system.product_service.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public ResponseProductDto save(RequestProductDto product) {
        Product request = new Product();
        request.setName(product.getName());
        request.setPrice(product.getPrice());
        request.setStock(product.getStock());
        Product savedProduct = repository.save(request);
        return new ResponseProductDto(savedProduct .getProductId());
    }

    public RequestProductDto get(Long productId) {
        Product response = repository.findById(productId).orElseThrow();
        RequestProductDto responseProductDto = new RequestProductDto();
        responseProductDto.setName(response.getName());
        responseProductDto.setPrice(response.getPrice());
        responseProductDto.setStock(response.getStock());
        return responseProductDto;
    }

    public List<RequestProductDto> get() {
        List<Product> products = repository.findAll();

        return products.stream()
                .map(product -> new RequestProductDto(
                        product.getName(),
                        product.getPrice(),
                        product.getStock()
                ))
                .toList();
    }

    //Test conflict

    public String delete(Long productId) {
        repository.deleteById(productId);
        return "Product removed successfully";
    }
}
