package org.example.ecomercerestapi.service;

import lombok.RequiredArgsConstructor;
import org.example.ecomercerestapi.model.Product;
import org.example.ecomercerestapi.repository.ProductRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product getProduct(Integer productId) {
        return productRepo.findById(productId).orElse(null);
    }

    public ResponseEntity<?> addProduct(Product product, MultipartFile image) {
        product.setImageName(image.getOriginalFilename());
        product.setImageType(image.getContentType());
        try {
            product.setImageData(image.getBytes());
            Product savedProduct = productRepo.save(product);
            return new ResponseEntity<>(savedProduct, HttpStatus.OK);
        } catch (IOException exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Product> updateProduct(Product product, MultipartFile image) {
        product.setImageType(image.getContentType());
        product.setImageName(image.getOriginalFilename());
        try {
            product.setImageData(image.getBytes());
            return new ResponseEntity<>(productRepo.save(product),HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> deleteProductById(Integer id) {
        Product product = this.getProduct(id);
        if(product != null) {
            productRepo.deleteById(id);
            return new ResponseEntity<>("deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("product does not exist",HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<List<Product>> searchByKeyword(String keyword) {
        List<Product> products = productRepo.searchProducts(keyword);
        if(products != null) {
            return new ResponseEntity<>(products,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
