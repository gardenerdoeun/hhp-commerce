package kr.hhplus.be.server.domain.product;

import kr.hhplus.be.server.infrastructure.inMemory.ProductRepository;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductServiceTest {

    @Test
    void testCreateAndDecreaseStock() {
        ProductRepository repo = new ProductRepository();
        ProductService service = new ProductService(repo);

        Product p = service.createProduct("Pen", 500, "SELLING", 10);
        assertNotNull(p.getProductId());

        service.decreaseStock(p.getProductId(), 3);
        assertEquals(7, service.getStock(p.getProductId()).getStockQuantity());
    }
}
