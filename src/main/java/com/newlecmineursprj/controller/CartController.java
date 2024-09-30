package com.newlecmineursprj.controller;

import com.newlecmineursprj.entity.*;
import com.newlecmineursprj.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("cart")
@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private ProductItemService productItemService;
    @Autowired
    private ProductService productService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private ColorService colorService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String listByMid(@RequestParam(name = "mid", required = false) Long mid,
            Model model) {

        List<Cart> cartList = cartService.getByMid(mid);
        List<Long> productItemIds = new ArrayList<>();
        List<ProductItem> productItemList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        List<Size> sizeList = new ArrayList<>();
        List<Color> colorList = new ArrayList<>();
        List<Category> categoryList = categoryService.getList();

        for (Cart cart : cartList)
            productItemIds.add(cart.getProductItemId());
        for (Long productItemId : productItemIds)
            productItemList.add(productItemService.getById(productItemId));
        for (ProductItem productItem : productItemList) {
            productList.add(productService.getById(productItem.getProductId()));
            sizeList.add(sizeService.getById(productItem.getSizeId()));
            colorList.add(colorService.getById(productItem.getColorId()));
        }
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("cartList", cartList);
        model.addAttribute("productList", productList);
        model.addAttribute("sizeList", sizeList);
        model.addAttribute("colorList", colorList);

        return "cart/list";
    }

    // 장바구니 상품 하나씩 구매 or 삭제
    @PostMapping("user-action-each")
    public String userActionEach(
            @RequestParam("userAction") int userAction,
            @RequestParam("cartId") Long cartId,
            @RequestParam("memberId") Long memberId) {

        if (userAction == 1) {

        } else if (userAction == 2) {
            cartService.delete(cartId);

            return "redirect:/cart?mid=" + memberId;
        }
        return "redirect:/cart?mid=" + memberId;
    }

    // 장바구니 상품 선택된것만 구매 or 삭제
    @PostMapping("user-action-selected")
    public String userActionSelected(
            @RequestParam("userAction") int userAction,
            @RequestParam(name = "cartId", defaultValue = "0") Long cartId,
            @RequestParam("memberId") Long memberId) {

        if (userAction == 1) {
            System.out.println("나오나1");
            System.out.println(cartId);
        } else if (userAction == 2) {
            System.out.println("나오나2");
            System.out.println(cartId);
            return "redirect:/cart?mid=" + memberId;
        }
        return "redirect:/cart?mid=" + memberId;
    }

    // 장바구니 상품 전체 구매 or 삭제
    @PostMapping("user-action-all")
    public String useActionAll(@RequestParam("memberId") Long memberId, @RequestParam("userAction") int userAction,
            RedirectAttributes redirectAttributes) {

        // userAction == 1 , 전체 구매
        if (userAction == 1) {
            List<Cart> cartList = cartService.getByMid(memberId);

            // 장바구니 비어있을때 예외처리
            if (cartList.isEmpty())
                return "redirect:/cart?mid=" + memberId;

            redirectAttributes.addFlashAttribute("memberId", memberId);
            redirectAttributes.addFlashAttribute("cartList", cartList);
            return "redirect:/order/pay";
            
        }

        // userAction == 2 장바구니 전체 삭제
        else if (userAction == 2) {
            cartService.deleteByMid(memberId);
            return "redirect:/cart?mid=" + memberId;
        }
        return "";
    }

    @PostMapping("qty")
    public String qty(@RequestParam("qtyJudge") int qtyJudge,
            @RequestParam("cartId") Long cartId,
            @RequestParam(defaultValue = "0", value = "qty") int qty,
            @RequestParam("mid") Long mid) {

        // Cart cart = cartService.getById(cartId);
        // ProductItem productItem =
        // productItemService.getById(cart.getProductItemId());
        // int stock = productItem.getQty();

        if (qtyJudge == 1)
            cartService.increase(cartId);
        else if (qtyJudge == 0 && qty > 1)
            cartService.decrease(cartId);

        return "redirect:/cart?mid=" + mid;
    }

}
