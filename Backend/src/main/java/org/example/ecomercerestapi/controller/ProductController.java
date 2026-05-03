package org.example.ecomercerestapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.ecomercerestapi.model.Product;
import org.example.ecomercerestapi.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api") // used here because all URL exist in it /api
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<>(
                productService.getAllProducts(), HttpStatus.ACCEPTED
        );
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer productId) {
        Product product = productService.getProduct(productId);
        if (product != null) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart("imageFile") MultipartFile image) {
        return productService.addProduct(product, image);
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getProductImageById(@PathVariable("productId") Integer id) {
        Product product = productService.getProduct(id);
        if (product != null && product.getImageData() != null) {
            return new ResponseEntity<>(product.getImageData(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProductById(@RequestPart Product product, @RequestPart("imageFile") MultipartFile image) {
        return productService.updateProduct(product, image);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable("productId") Integer id) {
        return productService.deleteProductById(id);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchByKeyword(@RequestParam String keyword) {
        return productService.searchByKeyword(keyword);
    }

}
