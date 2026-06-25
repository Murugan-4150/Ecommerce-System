package com.ecommerce_system.product_service.controller;

import com.ecommerce_system.product_service.dto.RequestProductDto;
import com.ecommerce_system.product_service.dto.ResponseProductDto;
import com.ecommerce_system.product_service.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<ResponseProductDto> addProduct(@RequestBody RequestProductDto product) {
        ResponseProductDto response = service.save(product);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/getProduct/{productId}")
    public ResponseEntity<?> getProductById(@PathVariable Long productId) {
        try {

            return ResponseEntity.ok().body(service.get(productId));
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    //Test git cherry-pick

    @GetMapping("/getProduct")
    public List<RequestProductDto> getAllProducts() {
        return service.get();
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(service.delete(productId));
    }

    @DeleteMapping("/delete/{productId}")
    public String deleteProductById2(@PathVariable Long productId) {
        return service.delete(productId);
    }
}
