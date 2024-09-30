package com.newlecmineursprj.controller.admin;

import com.newlecmineursprj.config.security.WebUserDetails;
import com.newlecmineursprj.entity.OrderItem;
import com.newlecmineursprj.entity.OrderState;
import com.newlecmineursprj.entity.OrderView;
import com.newlecmineursprj.entity.Product;
import com.newlecmineursprj.entity.ProductItem;
import com.newlecmineursprj.entity.Size;
import com.newlecmineursprj.entity.Color;
import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.service.ColorService;
import com.newlecmineursprj.service.MemberService;
import com.newlecmineursprj.service.OrderItemService;
import com.newlecmineursprj.service.OrderService;
import com.newlecmineursprj.service.OrderStateService;
import com.newlecmineursprj.service.ProductItemService;
import com.newlecmineursprj.service.ProductService;
import com.newlecmineursprj.service.SizeService;
import com.newlecmineursprj.util.CustomPageImpl;
import com.newlecmineursprj.util.SearchModuleUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestMapping("admin/order")
@Controller("adminOrderController")
@RequiredArgsConstructor
public class OrderController {
    private static final String ORDER_VIEW = "/admin/order";

    private final OrderService service;
    private final OrderItemService orderItemService;
    private final ProductService productService;
    private final ProductItemService productItemService;
    private final SizeService sizeService;

    private final ColorService colorService;
    private final OrderStateService orderStateService;
    private final MemberService memberService;

    @GetMapping
    public String list(Model model, @RequestParam(value = "p", defaultValue = "1") Integer page,
            @RequestParam(value = "s", defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) String searchMethod,
            @RequestParam(defaultValue = "") String searchKeyword,
            @RequestParam(defaultValue = "") String calendarStart,
            @RequestParam(defaultValue = "") String calendarEnd,
            @RequestParam(required = false) Long memberId) {

        int count = service.getCount(searchMethod, searchKeyword.trim());

        String startDate = SearchModuleUtil.getStartDate();

        CustomPageImpl<OrderView> list = service.getList(
                page, pageSize, "ordered_datetime", "DESC", 5,
                searchMethod, searchKeyword, memberId,
                calendarStart, calendarEnd, startDate);
        model.addAttribute("list", list);
        model.addAttribute("count", count);
        model.addAttribute("calendarStart", calendarStart);
        model.addAttribute("calendarEnd", calendarEnd);
        model.addAttribute("startDate", startDate);
        return ORDER_VIEW + "/list";
    }

    @GetMapping("detail")
    public String orderDetail(@AuthenticationPrincipal WebUserDetails webUserDetails
                            ,@RequestParam(value = "id") Long orderId
                            , Model model) {
        long memberId = webUserDetails.getId();

        OrderView orderView = service.getById(orderId);
        List<OrderItem> orderItemList = orderItemService.getByOrderId(orderId);

        List<Long> productItemIds = new ArrayList<>();
        List<ProductItem> productItemList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        List<Size> sizeList = new ArrayList<>();
        List<Color> colorList = new ArrayList<>();

        for (OrderItem orderItem : orderItemList)
            productItemIds.add(orderItem.getProductItemId());
        for (Long productItemId : productItemIds)
            productItemList.add(productItemService.getById(productItemId));
        for (ProductItem productItem : productItemList) {
            productList.add(productService.getById(productItem.getProductId()));
            sizeList.add(sizeService.getById(productItem.getSizeId()));
            colorList.add(colorService.getById(productItem.getColorId()));
        }

        int totalPrice = 0;
        for (int i = 0; i < orderItemList.size(); i++) {
            totalPrice += orderItemList.get(i).getQty() * productList.get(i).getPrice();
        }

        List<OrderState> orderStateList = orderStateService.getList();

        Member member = memberService.getByName(orderView.getUserName());
        
        model.addAttribute("orderItemList", orderItemList);
        model.addAttribute("productList", productList);
        model.addAttribute("sizeList", sizeList);
        model.addAttribute("colorList", colorList);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("memberId", memberId);
        model.addAttribute("orderView", orderView);
        model.addAttribute("orderStateList", orderStateList);
        model.addAttribute("orderId", orderId);
        model.addAttribute("member", member);

        return "admin/order/detail";
    }

    @PostMapping("orderState")
    public String orderState(@RequestParam("orderId") Long orderId
                            ,@RequestParam("orderState") String orderStateName){

        OrderState orderState = orderStateService.getByName(orderStateName);
        List<OrderItem> orderItemList = orderItemService.getByOrderId(orderId);

        for(OrderItem orderItem : orderItemList){
            orderItemService.changeOrderState(orderState.getId(), orderItem.getId());
        }

        return "redirect:detail?id=" + orderId;
    }

    @GetMapping("excel")
    public void excel(HttpServletResponse response, @RequestParam(defaultValue = "0") List<Long> orderId)
            throws IOException {
        Workbook workbook = new XSSFWorkbook();
        CreationHelper creationHelper = workbook.getCreationHelper();
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        Sheet sheet = workbook.createSheet("게시판글들");
        int rowNo = 0;

        Row headerRow = sheet.createRow(rowNo++);
        headerRow.createCell(0).setCellValue("주문일시");
        headerRow.createCell(1).setCellValue("상품코드");
        headerRow.createCell(2).setCellValue("주문자");
        headerRow.createCell(3).setCellValue("상품명");
        headerRow.createCell(4).setCellValue("총금액");
        headerRow.createCell(5).setCellValue("실결제금액");
        headerRow.createCell(6).setCellValue("결제수단");
        headerRow.createCell(7).setCellValue("주문상태");

        for (Long id : orderId) {
            OrderView orderView = service.getById(id);

            String productName = "";
            int productCount = orderView.getProductsCount();

            if (orderView.getProductNames().size() > 0)
                productName = orderView.getProductNames().get(0);
            if (productCount > 1) {
                productCount -= 1;
                productName = productName + " 외 " + productCount + "개";
            }

            Row row = sheet.createRow(rowNo++);
            Cell cell0 = row.createCell(0);
            cell0.setCellValue(orderView.getOrderedDatetime());
            cell0.setCellStyle(dateCellStyle);
            row.createCell(1).setCellValue(orderView.getCode());
            row.createCell(2).setCellValue(orderView.getUserName());
            row.createCell(3).setCellValue(productName);
            row.createCell(4).setCellValue(orderView.getTotalProductPrice());
            row.createCell(5).setCellValue(orderView.getTotalProductPrice() - orderView.getTotalDiscountAmount());
            row.createCell(6).setCellValue(orderView.getPaymentMethod());
            row.createCell(7).setCellValue(orderView.getOrderState());

        }

        sheet.setColumnWidth(0, 6000);
        sheet.setColumnWidth(1, 3000);
        sheet.setColumnWidth(2, 3000);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 3000);
        sheet.setColumnWidth(5, 3000);
        sheet.setColumnWidth(6, 3000);
        sheet.setColumnWidth(7, 3000);

        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=orderList.xlsx");

        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
