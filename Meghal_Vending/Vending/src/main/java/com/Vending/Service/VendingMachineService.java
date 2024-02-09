package com.Vending.Service;

import com.Vending.Entity.Coin;
import com.Vending.Entity.Product;
import com.Vending.Exception.VendingMachineException;
import com.Vending.Repository.CoinRepository;
import com.Vending.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendingMachineService {

    private final CoinRepository coinRepository;
    private final ProductRepository productRepository;

    @Autowired
    public VendingMachineService(CoinRepository coinRepository, ProductRepository productRepository) {
        this.coinRepository = coinRepository;
        this.productRepository = productRepository;
    }

    public List<Coin> getAllCoins() {
        return coinRepository.findAll();
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new VendingMachineException("Product not found"));

        if (product.getStock() <= 0) {
            throw new VendingMachineException("Product is out of stock");
        }

        return product;
    }

    public void addCoins(List<Coin> coins) {
        coinRepository.saveAll(coins);
    }

    public void processPurchase(Product product, List<Coin> payment) {
        int totalPayment = calculateTotalPayment(payment);

        if (totalPayment < product.getPriceInPence()) {
            throw new VendingMachineException("Insufficient payment");
        }

        int newStock = product.getStock() - 1;
        if (newStock < 0) {
            throw new VendingMachineException("Product is out of stock");
        }

        product.setStock(newStock);
        productRepository.save(product);

        // You can implement logic to return change if needed
    }

    private int calculateTotalPayment(List<Coin> payment) {
        return payment.stream()
                .mapToInt(coin -> getDenominationValue(coin.getDenomination()))
                .sum();
    }

    private int getDenominationValue(String denomination) {
        // You can implement logic to map denomination strings to their corresponding values
        // For simplicity, assuming denominations are in multiples of pence
        return Integer.parseInt(denomination.replace("p", ""));
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
