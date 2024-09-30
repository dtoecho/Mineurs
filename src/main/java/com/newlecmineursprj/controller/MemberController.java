package com.newlecmineursprj.controller;

import com.newlecmineursprj.config.security.WebUserDetails;
import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("member")
@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping("modify")
    public String modifyForm(Model model,
            @AuthenticationPrincipal WebUserDetails webUserDetails) {
        long memberId = webUserDetails.getId();
        Member member = memberService.getById(memberId);
        model.addAttribute("member", member);
        return "member/modify";
    }

    @PostMapping("modify")
    public String modify(@Validated Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("Member Modify Error: {}", bindingResult);
            return "member/modify";
        }

        memberService.update(member);
        return "redirect:/myshop";
    }

}
