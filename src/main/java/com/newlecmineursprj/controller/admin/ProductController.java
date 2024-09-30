package com.newlecmineursprj.controller.admin;

import com.newlecmineursprj.config.security.WebUserDetails;
import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.dto.ProductQtyDTO;
import com.newlecmineursprj.dto.ProductRegDTO;
import com.newlecmineursprj.entity.*;
import com.newlecmineursprj.service.*;
import com.newlecmineursprj.util.CustomPageImpl;
import com.newlecmineursprj.util.SearchModuleUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequestMapping("admin/products")
@Controller("adminProductController")
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private static final String PRODUCTS_VIEW = "/admin/products";
    private static final String REDIRECT = "redirect:";

    private final ProductService service;
    private final CategoryService categoryService;
    private final ProductSubImgService productSubImgService;
    private final ProductItemService productItemService;
    private final ColorService colorService;
    private final SizeService sizeService;

    @GetMapping
    public String list(
            @RequestParam(value = "p", defaultValue = "1") Integer page,
            @RequestParam(value = "s", defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String searchMethod,
            @RequestParam(defaultValue = "") String searchKeyword,
            @RequestParam(defaultValue = "0") Long categoryId,
            @RequestParam(defaultValue = "") String calendarStart,
            @RequestParam(defaultValue = "") String calendarEnd,
            @RequestParam(defaultValue = "") String selectedDisplayStatus,
            @RequestParam(defaultValue = "") String selectedSellStatus,
            @AuthenticationPrincipal WebUserDetails webUserDetails,
            Model model) {

        int count = service.getCount(searchMethod, searchKeyword.trim(), categoryId);
        List<Category> categories = categoryService.getList();

        Integer sellStatusResult = SearchModuleUtil.searchBySellStatus(selectedSellStatus);
        Integer displayStatusResult = SearchModuleUtil.searchByDisplayStatus(selectedDisplayStatus);
        String startDate = SearchModuleUtil.getStartDate();

        Long memberId = null;
        if (webUserDetails != null)
            memberId = webUserDetails.getId();

        CustomPageImpl<ProductListDTO> productPage = service.getList(
                page, pageSize, "reg_date", 5
                , searchMethod, searchKeyword.trim(), categoryId
                , startDate, calendarStart, calendarEnd, displayStatusResult, sellStatusResult, memberId
        );

        model.addAttribute("productPage", productPage);
        model.addAttribute("count", count);
        model.addAttribute("categories", categories);
        model.addAttribute("sellStatusList", SearchModuleUtil.sellStatusList());
        model.addAttribute("displayStatusList", SearchModuleUtil.DisplayStatusList());
        model.addAttribute("startDate", startDate);
        model.addAttribute("calendarStart", calendarStart);
        model.addAttribute("calendarEnd", calendarEnd);
        return PRODUCTS_VIEW + "/list";
    }

    @GetMapping("/reg")
    public String regForm(Model model) {
        List<Category> categories = categoryService.getList();
        model.addAttribute("categories", categories);
        model.addAttribute("productRegDTO", new ProductRegDTO());
        return PRODUCTS_VIEW + "/reg";
    }

    @PostMapping
    public String reg(@Validated @ModelAttribute ProductRegDTO productRegDTO, BindingResult bindingResult, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            // 필드 검증 실패시 form을 다시 띄울 때 categories가 필요하므로 그걸 넣어주는 역할
            ifCategoryNull(Product.createProduct(productRegDTO), model);
            log.error("Reg Form Error : {}", bindingResult + "\n");
            return PRODUCTS_VIEW + "/reg";
        }
        service.reg(productRegDTO);
        return REDIRECT + PRODUCTS_VIEW;
    }

    private void ifCategoryNull(Product product, Model model) {
        if (product.getCategoryId() == null) {
            List<Category> categories = categoryService.getList();
            model.addAttribute("categories", categories);
        }
    }


    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Product product = service.getById(id);
        List<Category> categories = categoryService.getList();
        List<ProductSubImg> subImgs = productSubImgService.getListByProductId(product.getId());

        List<ProductItem> productItems = productItemService.getByProductId(id);
        List<ProductQtyDTO> productQtyDTOs = new ArrayList<>();
        for (ProductItem productItem : productItems) {
            Color color = colorService.getById(productItem.getColorId());
            Size size = sizeService.getById(productItem.getSizeId());
            ProductQtyDTO productQtyDTO = new ProductQtyDTO();
            productQtyDTO.setQty(productItem.getQty());
            productQtyDTO.setColorId(color.getId());
            productQtyDTO.setColor(color.getKorName());
            productQtyDTO.setSizeId(size.getId());
            productQtyDTO.setSize(size.getEngName());
            productQtyDTOs.add(productQtyDTO);
        }

        Comparator<ProductQtyDTO> comparator = Comparator
                .comparing(ProductQtyDTO::getColorId)  // colorId로 정렬
                .thenComparing(ProductQtyDTO::getSizeId); // 동일한 colorId 내에서 sizeId로 정렬

        // 정렬 적용
        Collections.sort(productQtyDTOs, comparator);

        model.addAttribute("categories", categories);
        model.addAttribute("product", product);
        model.addAttribute("subImgs", subImgs);
        model.addAttribute("productQtyList", productQtyDTOs);
        return PRODUCTS_VIEW + "/detail";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam List<Long> deleteId) {
        service.deleteAllById(deleteId);
        return REDIRECT + PRODUCTS_VIEW;
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Product updateProduct, MultipartFile updateImg, List<MultipartFile> updateSubImgs) throws IOException {
        service.update(updateProduct, updateImg, updateSubImgs);
        return REDIRECT + PRODUCTS_VIEW;
    }
}
