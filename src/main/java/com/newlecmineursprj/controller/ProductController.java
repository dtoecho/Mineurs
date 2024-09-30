package com.newlecmineursprj.controller;

import com.newlecmineursprj.config.security.WebUserDetails;
import com.newlecmineursprj.entity.*;
import com.newlecmineursprj.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("products")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductService service;
    private final ColorService colorService;
    private final SizeService sizeService;
    private final ProductSubImgService productSubImgService;
    private final CategoryService categoryService;
    private final ProductItemService productItemService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final CartService cartService;
    private final LikeService likeService;

    @GetMapping("{id}")

    public String list(@PathVariable long id
                        , Model model
                        ,@AuthenticationPrincipal WebUserDetails webUserDetails) {

        //구매,장바구니 버튼 누르기전 로그인 되어있는지 검증하기 위해 값 전송
        if(webUserDetails != null)
            model.addAttribute("memberId", webUserDetails.getId());

        List<Category> categoryList = categoryService.getList();
        model.addAttribute("categoryList", categoryList);

        Product product = service.getById(id);
        model.addAttribute("product", product);

        List<ProductItem> productItemList = productItemService.getByProductId(id);
        Set<Color> colors = new HashSet<>();
        Set<Size> sizes = new HashSet<>();
        for (ProductItem productItem : productItemList) {
            Long colorId = productItem.getColorId();
            colors.add(colorService.getById(colorId));
            Long sizeId = productItem.getSizeId();
            sizes.add(sizeService.getById(sizeId));
        }

        model.addAttribute("colors", colors);
        model.addAttribute("sizes", sizes);

        List<ProductSubImg> subImgs = productSubImgService.getListByProductId(id);
        model.addAttribute("subImgs", subImgs);

        return "detail";
    }

    @PostMapping("user-action")
    public String userAction(
            @RequestParam("userAction") int userAction, 
            @RequestParam("productId") Long productId,
            // @RequestParam(value = "colorId", defaultValue = "0") Long colorId,
            // @RequestParam(value = "sizeId", defaultValue = "0") Long sizeId,
            @RequestParam(value = "colorName", defaultValue = "0") String colorName,
            @RequestParam(value = "sizeName", defaultValue = "0") String sizeName,
            @RequestParam(value = "quantity", defaultValue = "0") String quantity,
            @AuthenticationPrincipal WebUserDetails webUserDetails) {
        // 로그인 정보에서 memberId 얻기
        long memberId = webUserDetails.getId();

        Product product = service.getById(productId);

        // 여러개 주문에 대해 color, size, quantity 받기(String)
        String[] colors = colorName.split(",");
        String[] sizes = sizeName.split(",");
        String[] StringQuantities = quantity.split(",");

        List<Long> colorIds = new ArrayList<>();
        List<Long> sizeIds = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();
        List<ProductItem> productItems = new ArrayList<>();

        // 위에서 받은 color, size, quantity를 쓰기 편한 형태로 변환
        for (int i = 0; i < colors.length; i++) {
            Long colorId = colorService.getIdByKorName(colors[i]);
            Long sizeId = sizeService.getIdByEngName(sizes[i]);
            Integer tempQuantity = Integer.parseInt(StringQuantities[i]);
            ProductItem productItem = productItemService.getByForeignKeys(productId, sizeId, colorId);

            colorIds.add(colorId);
            sizeIds.add(sizeId);
            quantities.add(tempQuantity);
            productItems.add(productItem);
        }


        // 상품 구매 (userAction == 1)
        if (userAction == 1) {

            // order 테이블에 데이터 추가
            // 일단은 totalPrice, memberId만 저장
            Order order = new Order();
            int price = product.getPrice();
            int totalQuantity = 0;
            for (int tempQuantity : quantities)
                totalQuantity += tempQuantity;
            int totalPrice = price * totalQuantity;
            order.setMemberId(memberId);
            order.setTotalProductPrice(totalPrice);
            orderService.add(order);


            for (int i = 0; i < productItems.size(); i++) {
                int qty = quantities.get(i);
                ProductItem productItem = productItems.get(i);

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

            return "redirect:/order/payComplete";

        }

        // 장바구니에 추가 (userAction == 2)
        else if (userAction == 2) {

            for(int i=0; i<productItems.size(); i++){
                ProductItem productItem = productItems.get(i);
                int tempQuantity = quantities.get(i);
                Cart tempCart = cartService.getByForeignKeys(memberId, productItem.getId());
    
                if (tempCart == null) {
                    Cart cart = new Cart();
                    cart.setMemberId(memberId);
                    cart.setProductItemId(productItem.getId());
                    cart.setQty(tempQuantity);
                    cartService.add(cart);
                }
                else{
                    int newQty = tempCart.getQty() + tempQuantity;
                    tempCart.setQty(newQty);
                    cartService.update(tempCart);
                }
            }

            return "redirect:" + productId;
        }

        // wishList에 추가(userAction == 3)
        else if (userAction == 3) {

            Like tempLike = likeService.getByForeignKeys(memberId, productId);

            if (tempLike == null) {
                Like like = new Like();
                like.setMemberId(memberId);
                like.setProductId(productId);
                likeService.add(like);
            }

            return "redirect:" + productId;
        }
        return "redirect:" + productId;
    }
}
