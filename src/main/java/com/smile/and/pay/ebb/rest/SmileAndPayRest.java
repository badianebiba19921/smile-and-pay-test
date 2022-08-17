package com.smile.and.pay.ebb.rest;

import com.smile.and.pay.ebb.models.Marchand;
import com.smile.and.pay.ebb.models.Product;
import com.smile.and.pay.ebb.repository.MarchandRepository;
import com.smile.and.pay.ebb.repository.ProductRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SmileAndPayRest {

    @Autowired
    MarchandRepository marchandRepository;

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/marchands")
    public ResponseEntity<List<Marchand>> getAllMarchands() {
        List<Marchand> marchands = marchandRepository.findAll();
        if (CollectionUtils.isEmpty(marchands)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(marchands, HttpStatus.OK);
    }

    @GetMapping("/marchands/{id}")
    public ResponseEntity<Marchand> getMarchandById(@PathVariable int id) {
        Optional<Marchand> marchand = marchandRepository.findById(id);
        if(marchand.isPresent()){
            return new ResponseEntity<>(marchand.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/marchands/{id}")
    public ResponseEntity<Marchand> updateMarchand(@PathVariable("id") int id, @RequestBody Marchand marchand) {
        Optional<Marchand> _marchand = marchandRepository.findById(id);
        if(_marchand.isPresent()){
            marchand.setId(_marchand.get().getId());
            return new ResponseEntity<>(marchandRepository.save(marchand), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (CollectionUtils.isEmpty(products)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id) {
        Optional<Product> product = productRepository.findById(id);
        if(product.isPresent()){
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") int id, @RequestBody Product product) {
        Optional<Product> _product = productRepository.findById(id);
        if(_product.isPresent()){
            product.setId(_product.get().getId());
            return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Marchand> exception(Exception e, Model model){
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
