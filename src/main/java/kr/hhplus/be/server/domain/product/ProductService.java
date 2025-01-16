package kr.hhplus.be.server.domain.product;

import kr.hhplus.be.server.exception.AppException;
import kr.hhplus.be.server.exception.ErrorCode;
import kr.hhplus.be.server.infrastructure.inMemory.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final kr.hhplus.be.server.infrastructure.inMemory.ProductRepository repo;
    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product createProduct(String name, int price, String status, int stockQty) {
        Product p = new Product();
        p.setProductName(name);
        p.setProductPrice(java.math.BigDecimal.valueOf(price));
        p.setProductStatus(ProductStatus.valueOf(status));
        repo.saveProduct(p);

        ProductStock s = new ProductStock();
        s.setProductId(p.getProductId());
        s.setStockQuantity(stockQty);
        repo.saveProductStock(s);

        return p;
    }

    public Product getProduct(Long productId) {
        return repo.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    public ProductStock getStock(Long productId) {
        return repo.findByProductStockId(productId)
                .orElseThrow(() -> new AppException(ErrorCode.STOCK_NOT_FOUND));
    }

    public void decreaseStock(Long productId, int qty) {
        ProductStock s = getStock(productId);
        if (s.getStockQuantity() < qty) {
            throw new AppException(ErrorCode.OUT_OF_STOCK);
        }
        s.setStockQuantity(s.getStockQuantity() - qty);
        repo.saveProductStock(s);
    }

    public List<Product> getProducts(int page, int size) {
        List<Product> all = repo.findAllProducts();
        return all.stream()
                .skip((long)(page - 1) * size)
                .limit(size)
                .collect(Collectors.toList());
    }
}


