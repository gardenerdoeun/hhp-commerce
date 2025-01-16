package kr.hhplus.be.server.infrastructure;

import kr.hhplus.be.server.domain.product.Product;
import kr.hhplus.be.server.domain.product.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface ProductStockJpaRepository extends JpaRepository<ProductStock, Long> {
//    Optional<Integer> findProductStockByProductId(Long productId);
//    Optional<ProductStock> findByProductId(Long productId);
}
