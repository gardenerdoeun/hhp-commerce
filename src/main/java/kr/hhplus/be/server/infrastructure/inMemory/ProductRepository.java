package kr.hhplus.be.server.infrastructure.inMemory;

import kr.hhplus.be.server.domain.product.Product;
import kr.hhplus.be.server.domain.product.ProductStock;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {
    private final Map<Long, Product> products = new ConcurrentHashMap<>();
    private final Map<Long, ProductStock> stocks = new ConcurrentHashMap<>();
    private final AtomicLong stockIdGenerator = new AtomicLong();
    private final AtomicLong productIdGenerator = new AtomicLong();

    public Product saveProduct(Product product) {
        if(product.getProductId() == null) {
            product.setProductId(productIdGenerator.incrementAndGet());
        }
        products.put(product.getProductId(), product);
        return product;
    }

    public ProductStock saveProductStock(ProductStock productStock) {
        if(productStock.getStockId() == null) {
            productStock.setStockId(stockIdGenerator.incrementAndGet());
        }
        stocks.put(productStock.getStockId(), productStock);
        return productStock;
    }

    public Optional<Product> findById(long productId) {
        return Optional.ofNullable(products.get(productId));
    }

    public Optional<ProductStock> findByProductStockId(long productStockId) {
        return Optional.ofNullable(stocks.get(productStockId));
    }
}
