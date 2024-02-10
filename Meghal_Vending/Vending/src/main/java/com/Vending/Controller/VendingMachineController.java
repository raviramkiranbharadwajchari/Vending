package com.Vending.Controller;

import com.Vending.Entity.Coin;
import com.Vending.Entity.Product;
import com.Vending.Service.VendingMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vending-machine")
public class VendingMachineController {
    @Autowired
    private VendingMachineService vendingMachineService;

    @GetMapping("/coins")
    public ResponseEntity<List<Coin>> getAllCoins() {
        List<Coin> coins = vendingMachineService.getAllCoins();
        return ResponseEntity.ok(coins);
    }

    @PostMapping("/coins")
    public ResponseEntity<Coin> createCoin(@RequestBody Coin coin) {
        Coin savedCoin = vendingMachineService.addCoin(coin);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCoin);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = vendingMachineService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @PostMapping("/products")
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product savedProduct = vendingMachineService.addProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }
}
