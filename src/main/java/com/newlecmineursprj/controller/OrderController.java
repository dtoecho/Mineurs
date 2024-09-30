package com.newlecmineursprj.controller;

import java.util.ArrayList;
import java.util.List;

import com.newlecmineursprj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecmineursprj.entity.Cart;
import com.newlecmineursprj.entity.Color;
import com.newlecmineursprj.entity.Order;
import com.newlecmineursprj.entity.OrderItem;
import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductItem;
import com.newlecmineursprj.entity.Size;

@Controller
@RequestMapping("order")
public class OrderController {

    @Autowired
    private ProductItemService productItemService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private CartService cartService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;


    @GetMapping("pay")
    public String pay(Model model, @ModelAttribute("cartList") List<Cart> cartList,
            @ModelAttribute("memberId") Long memberId) {

        List<Long> productItemIds = new ArrayList<>();
        List<ProductItem> productItemList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        List<Size> sizeList = new ArrayList<>();
        List<Color> colorList = new ArrayList<>();

        for (Cart cart : cartList)
            productItemIds.add(cart.getProductItemId());
        for (Long productItemId : productItemIds)
            productItemList.add(productItemService.getById(productItemId));
        for (ProductItem productItem : productItemList) {
            productList.add(productService.getById(productItem.getProductId()));
            sizeList.add(sizeService.getById(productItem.getSizeId()));
            colorList.add(colorService.getById(productItem.getColorId()));
        }

        int totalPrice = 0;
        for (int i = 0; i < cartList.size(); i++) {
            totalPrice += cartList.get(i).getQty() * productList.get(i).getPrice();
        }

        model.addAttribute("cartList", cartList);
        model.addAttribute("productList", productList);
        model.addAttribute("sizeList", sizeList);
        model.addAttribute("colorList", colorList);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("memberId", memberId);

        return "order/pay";
    }

    @PostMapping("pay")
    public String pay(@RequestParam("memberId") Long memberId) {

        List<Cart> cartList = cartService.getByMid(memberId);

        List<ProductItem> productItemList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        int totalPrice = 0;

        for (int i = 0; i < cartList.size(); i++) {
            Cart cart = cartList.get(i);
            ProductItem productItem = productItemService.getById(cart.getProductItemId());
            productItemList.add(productItem);
            Product product = productService.getById(productItem.getProductId());
            productList.add(product);

            int qty = cart.getQty();
            int price = product.getPrice();
            totalPrice += price * qty;
        }

        // order 테이블에 데이터 추가
        // 일단은 totalPrice, memberId만 저장
        Order order = new Order();
        order.setMemberId(memberId);
        order.setTotalProductPrice(totalPrice);
        orderService.add(order);


        for (int i = 0; i < productItemList.size(); i++) {
            Product product = productList.get(i);
            ProductItem productItem = productItemList.get(i);
            Cart cart = cartList.get(i);
            int qty = cart.getQty();
            int price = product.getPrice();

            // orderItem 테이블에 데이터 추가
            OrderItem orderItem = new OrderItem();
            orderItem.setQty(qty);
            orderItem.setTotalPrice(price * qty);
            orderItem.setOrderId(order.getId());
            orderItem.setOrderStateId((long) 1);
            orderItem.setProductItemId(productItem.getId());
            orderItemService.add(orderItem);

            // 주문한 갯수만큼 productItem 재고 감소
            productItemService.stockDecrease(qty, productItem.getId());
        }

        // 구매한 후 장바구니에서 품목 삭제
        cartService.deleteByMid(memberId);

        return "redirect:/order/payComplete";

    }

    @GetMapping("payComplete")
    public String payComplete() {
        return "order/payComplete";
    }
}
