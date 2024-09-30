package com.newlecmineursprj.controller;

import com.newlecmineursprj.config.security.WebUserDetails;
import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.service.LoginService;
import com.newlecmineursprj.util.MailService;
import com.newlecmineursprj.util.RandomPasswordGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequestMapping("login")
@RequiredArgsConstructor
@Controller
public class LoginController {

    final private LoginService service;
    final private MailService mailService;

    @GetMapping
    public String login(@AuthenticationPrincipal WebUserDetails webUserDetails) {
        if (webUserDetails == null) {
            return "/login";
        }
        return "redirect:/";
    }


    @GetMapping("find-id")
    public String findIdForm(@ModelAttribute("errorMessage") String errorMessage,Model model) {
        model.addAttribute("error", errorMessage);
        return "find-id";
    }

    @PostMapping("find-id")
    public String findId(Member member, Model model, RedirectAttributes redirectAttributes) {

        Member result = service.getById(member);
        if (result != null) {
            model.addAttribute("member", result);
            return "/id-found";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "일치하는 정보가 없습니다.");
            return "redirect:/login/find-id";
        }
    }

    @GetMapping("find-pw")
    public String findPasswordForm(@ModelAttribute("errorMessage") String errorMessage, Model model) {

         model.addAttribute("error", errorMessage);
        return "find-pw";
    }

    @PostMapping("find-pw")
    public String findPw(Member member, Model model, RedirectAttributes redirectAttributes) {
        Member result = service.getById(member);
        if (result != null) {
            String tempPassword = RandomPasswordGenerator.generate(10);
            service.updatePassword(result,tempPassword);
            model.addAttribute("member", result);
            mailService.sendMail(result,tempPassword);
            return "/pw-found";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "일치하는 정보가 없습니다.");
            return "redirect:/login/find-pw";
        }
    }

}