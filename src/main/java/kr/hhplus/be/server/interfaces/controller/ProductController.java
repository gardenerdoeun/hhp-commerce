package kr.hhplus.be.server.interfaces.controller;

import kr.hhplus.be.server.interfaces.response.HitProductResponse;
import kr.hhplus.be.server.interfaces.response.ProductResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping("/{productId}")
    public ProductResponse getProductById(@PathVariable("productId") Long productId) {
        return new ProductResponse(productId, "product A", 4500.0, 10);
    }

    @GetMapping("/hitProducts")
    public List<HitProductResponse> getHitProducts() {
        return Arrays.asList(
                new HitProductResponse(1L, "product A", 30),
                new HitProductResponse(2L, "product B", 40),
                new HitProductResponse(3L, "product C", 50),
                new HitProductResponse(4L, "product D", 60),
                new HitProductResponse(5L, "product E", 70)
        );
    }
}
