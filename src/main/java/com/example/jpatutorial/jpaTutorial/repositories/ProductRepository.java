package com.example.jpatutorial.jpaTutorial.repositories;

import com.example.jpatutorial.jpaTutorial.entities.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByTitle(String title);

    // ways to create custom methods in Repository
    /*
    * {Return Type} {query subject}{queryPredicate}{Operator}(Input params);
    *  List<Product>  findBy_DateCreated_Between
    *
    * => some possible query methods/subjects
    *    (find..By, read..By, query..By, get..By
    *    Example (findByName, readByName, queryByName, getByName)
    *
    * => If we want to limit the number of returned query results, we can
    *    add the first or the top keyword before the first by word
    *    Example -> findFirstByName, readFirst2ByName, findTop10ByName
    *
    * => If we want to select unique results, we have to add the distinct keyword
    *    before the first By Word
    *    Example -> findDistinctByName, findNameDistinctBy
    *
    * => Combine property expression by AND and OR
    *    findByNameOrDescription, findByNameAndDescription
    *
    * Check spring data JPA documentation for more rules
    * */

    List<Product> findByCreatedAtAfter(LocalDateTime createdAtAfter);

    List<Product> findByQuantityAndPrice(Integer quantity, BigDecimal price);

    List<Product> findByPriceLessThan(BigDecimal price);

    List<Product> findByTitleLike(String title);

    List<Product> findByTitleContainingIgnoreCase(String title);

//    Optional<Product> findByTitleAndPrice(String redBull_energy_drink, BigDecimal valueOf);

//  Creating our own queries using JPQL
//    @Query("select e from Product e where e.title=?1 and e.price=?2")
//    Optional<Product> findByTitleAndPrice(String title, BigDecimal price);

    @Query("select e from Product e where e.title=:title and e.price=:price")
    Optional<Product> findByTitleAndPrice(String title, BigDecimal price);

    /*
        We can achieve sorting and pagination by the following ways
        1. Using OrderBy
    * */

    List<Product> findByOrderByPrice();

    /*
     There is another way we can sort the results from the DB, which is by using Sort class
     */

    List<Product> findBy(Sort sort);

}
