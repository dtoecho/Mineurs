package com.newlecmineursprj.controller;

import com.newlecmineursprj.config.security.WebUserDetails;
import com.newlecmineursprj.dto.ProductListDTO;
import com.newlecmineursprj.entity.Category;
import com.newlecmineursprj.service.CategoryService;
import com.newlecmineursprj.service.MemberService;
import com.newlecmineursprj.service.PostService;
import com.newlecmineursprj.service.ProductService;
import com.newlecmineursprj.util.CustomPageImpl;
import com.newlecmineursprj.util.SearchModuleUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

private final ProductService service;
private final CategoryService categoryService;
private final MemberService memberService;
private final PostService postService;

    @GetMapping
    public String index(@RequestParam(value= "p", defaultValue = "1")int pageNumber
            , @RequestParam(value = "s", defaultValue = "12")int pageSize
            , @RequestParam(value = "sm", defaultValue = "reg_date")String sortMethod
            , @RequestParam(value = "sd", defaultValue = "DESC")String sortDirection
            , @RequestParam(defaultValue = "") String searchMethod
            , @RequestParam(defaultValue = "") String searchKeyword
            , @RequestParam(defaultValue = "0") Long categoryId
            , @RequestParam(defaultValue = "") String calendarStart
            , @RequestParam(defaultValue = "") String calendarEnd
            , @RequestParam(defaultValue = "진열함") String selectedDisplayStatus
            , @RequestParam(defaultValue = "") String selectedSellStatus
            , @AuthenticationPrincipal WebUserDetails webUserDetails
            , Model model) {

        List<Category> categoryList = categoryService.getList();
        model.addAttribute("categoryList",categoryList);

        Integer sellStatusResults = SearchModuleUtil.searchBySellStatus(selectedSellStatus);
        String startDate = SearchModuleUtil.getStartDate();
        Integer displayStatusResult = SearchModuleUtil.searchByDisplayStatus(selectedDisplayStatus);

        Long memberId = null;
        if (webUserDetails != null){
            memberId = webUserDetails.getId();
        }
        CustomPageImpl<ProductListDTO> productPage = service.getList(
                pageNumber, pageSize, sortMethod, sortDirection,
                5, searchMethod, searchKeyword, categoryId,
                startDate, calendarStart, calendarEnd, displayStatusResult, sellStatusResults, memberId
        );
        model.addAttribute("productPage", productPage);

        String categoryName = "All";
        if (categoryId != 0)
            categoryName = categoryService.getById(categoryId).getName();
        model.addAttribute("categoryName", categoryName);

        return "list";
    }

}
