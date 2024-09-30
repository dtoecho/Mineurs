package com.newlecmineursprj.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecmineursprj.entity.Coupon;
import com.newlecmineursprj.service.CouponService;

@RequestMapping("admin/coupon")
@Controller("adminCouponController")
public class CouponController {
    
    private static final String COUPON_VIEW = "/admin/coupon";
    @Autowired
    private CouponService service;

    @GetMapping("detail")
    public String detail(@RequestParam(name = "memberId", required = false, defaultValue = "1") Long memberId,
                        Model model){

        List<Coupon> list = service.getInvalidByMemberId(memberId);

        model.addAttribute("list", list);
        model.addAttribute("memberId", memberId);

        return COUPON_VIEW + "/detail";
    }    

}
