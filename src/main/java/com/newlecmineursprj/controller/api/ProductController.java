package com.newlecmineursprj.controller.api;

import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/products")
@RequiredArgsConstructor
@RestController("productApiController")
@Slf4j
public class ProductController {
    private static final String PRODUCTS_VIEW = "/admin/products";
    private final ProductService service;

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Product product = service.getById(id);
        model.addAttribute("product", product);
        return PRODUCTS_VIEW + "/detail";
    }
    /*@PutMapping
    public String edit(@RequestBody Product product) {
        service.edit(product);
        return "success";
    }*/

    @PatchMapping
    public ResponseEntity<String> updateAll(@RequestBody List<Product> products) {
        log.debug("패치");
        log.debug("products: {}", products);

        int updatedRowCount = service.updateAll(products);
        if (updatedRowCount > 0) {
            log.debug("성공");
            return ResponseEntity.ok("product updated");
        }
        log.debug("실패");
        return ResponseEntity.notFound().build();


    }
}
