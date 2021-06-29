//package com.sampleAuth.server.controller;
//
//import com.example.sampleFrontEnd.models.Product;
//import com.example.sampleFrontEnd.service.DataSamplerService;
//import com.example.sampleFrontEnd.service.ProductQueryService;
//import com.example.sampleFrontEnd.service.ProductUpdateService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:4200")
//@Slf4j
//public class ProductQueryController {
//    @Autowired
//    ProductQueryService productQueryService;
//    @Autowired
//    DataSamplerService dataSamplerService;
//    @Autowired
//    ProductUpdateService productUpdateService;
//
//    @GetMapping("/products")
//    public ResponseEntity<List<Product>> getAllTutorials(@RequestParam(required = false) String title) {
//        try {
//            List<Product> products = new ArrayList<Product>();
//
//            if (title == null)
//                productQueryService.findAll().forEach(products::add);
//            else
//                productQueryService.findByTitle(title).forEach(products::add);
//
//            if (products.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(products, HttpStatus.OK);
//        } catch (Exception e) {
//            System.out.println(e);
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/products/{id}")
//    public ResponseEntity<Boolean> getTutorialById(@PathVariable("id") long id) {
//        Boolean isPrime = true;
//            for (int primeCheck = 5 ; primeCheck < id/2; primeCheck+=2) {
//                if (id < 4) {
//                    break;
//
//                } else if( id % 2 == 0 ||id % primeCheck == 0){
//                    isPrime = false;
//                    break;
//                }
//            }
//        return ResponseEntity.ok(isPrime);
//
//
////        Optional<Product> tutorialData = productQueryService.findById(id);
////
////        if (tutorialData.isPresent()) {
////            return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
////        } else {
////            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
////        }
//    }
//
//    @GetMapping("/sampler/{id}")
//    public ResponseEntity<List<Product>> createSampleData(@PathVariable("id") Integer id) {
//        System.out.println(dataSamplerService.createData(id));
//        return new ResponseEntity<>(productQueryService.findAll(), HttpStatus.OK);
//    }
//
//    @PostMapping("/products")
//    public ResponseEntity<Product> createTutorial(@RequestBody Product product) throws JsonProcessingException {
//        System.out.println(new ObjectMapper().writeValueAsString(product));
//        try {
//            Boolean _tutorial = productUpdateService.save(product);
//            return new ResponseEntity<>(product, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/products")
//    public ResponseEntity<Boolean> updateTutorial(@RequestBody Product product) throws JsonProcessingException {
//        System.out.println(new ObjectMapper().writeValueAsString(product));
//
//        Optional<Product> tutorialData = productQueryService.findById(product.getId());
//
//        if (tutorialData.isPresent()) {
//            return new ResponseEntity<>(productUpdateService.save(product), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/products/{id}")
//    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
//
//        try {
//            productUpdateService.delete(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/products")
//    public ResponseEntity<HttpStatus> deleteAllTutorials() {
//
//        try {
//            productUpdateService.delete();
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
//
//    @GetMapping("/products/published")
//    public ResponseEntity<List<Product>> findByPublished() {
//
//        try {
//            List<Product> products = productQueryService.findIfAvailable(true);
//
//            if (products.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//            return new ResponseEntity<>(products, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//}