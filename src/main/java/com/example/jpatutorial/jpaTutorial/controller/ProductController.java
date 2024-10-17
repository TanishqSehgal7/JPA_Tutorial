package com.example.jpatutorial.jpaTutorial.controller;
import com.example.jpatutorial.jpaTutorial.entities.Product;
import com.example.jpatutorial.jpaTutorial.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    private final int PAGE_SIZE = 10;
    private final int PAGE_NUMBER = 1;

    private final ProductRepository productRepository;
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping()
    public List<Product> getAllProducts(@RequestParam(defaultValue = "id") String sortBy,
    @RequestParam(defaultValue = "") String title
    , @RequestParam(defaultValue = "0") int pageNumber) {
//        return productRepository.findByOrderByPrice();

        /* if 2 items have the same value of sortBy parameters, then result will be sorted
        by parameters mentioned in properties */
//        return productRepository.findBy(Sort.by(
//                Sort.Direction.ASC,sortBy, "price", "quantity"));

//        return productRepository.findBy(Sort.by(
//                Sort.Order.desc(sortBy),
//                Sort.Order.asc("title")
//        ));

        Pageable pageable = PageRequest.of(pageNumber, PAGE_SIZE, Sort.by(sortBy));
//        return productRepository.findAll(pageable).getContent();

        return productRepository.findByTitleContainingIgnoreCase(title,pageable);

    }

}