package com.Vending.Repository;

import com.Vending.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> { // Find products by name containing a certain keyword
    List<Product> findByNameContaining(String keyword);
    List<Product> findByStockGreaterThan(int quantity);
    List<Product> findByStockLessThan(int quantity);
    List<Product> findByStockBetween(int minQuantity, int maxQuantity);
    List<Product> findByPriceInPenceLessThan(int priceInPence);
    List<Product> findByPriceInPenceGreaterThan(int priceInPence);
    List<Product> findByPriceInPenceBetween(int minPriceInPence, int maxPriceInPence);

}