package com.newlecmineursprj.controller;

import com.newlecmineursprj.error.ErrorResult;
import com.newlecmineursprj.entity.Member;
import com.newlecmineursprj.service.RegisterService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequestMapping("register")
@RequiredArgsConstructor
@Controller
public class RegisterController {

    private final RegisterService service;

    @GetMapping
    public String form(Model model) {
        model.addAttribute("member", new Member());
        return "register";
    }

    @PostMapping
    public String reg(@Validated Member member, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            log.info("Member Reg validation error: {}", bindingResult);
            model.addAttribute("member", member);
            return "register";
        }
        int reg = service.reg(member);

        String welcomeMsg = "";
        if (reg > 0) {
            welcomeMsg = URLEncoder.encode("회원가입을 축하합니다!!! 안전한 로그인을 위해 재접속 해주세요.", StandardCharsets.UTF_8);
        }
        return "redirect:/login?successfully=" + welcomeMsg;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public void duplicateKeyExHandler(DuplicateKeyException ex, HttpServletResponse response) throws IOException {
        //회원 가입 아이디 중복 시
        if (ex.getMessage().contains("membername")) {
            log.error("Member ID duplication [{}]", ex);
            ErrorResult memberDuplicatedError = new ErrorResult(ex, "이미 등록된 회원입니다.");
            String errorMessage = URLEncoder.encode(memberDuplicatedError.getDefaultMessage(), StandardCharsets.UTF_8);
            response.sendRedirect("/register?error=" + errorMessage);
            return;
        }
        //회원가입 이메일 중복 시
        log.error("Member Email duplication {}", ex);
        ErrorResult memberDuplicatedError = new ErrorResult(ex, "이미 등록된 이메일입니다.");
        response.sendRedirect("/register?error=" + URLEncoder.encode(memberDuplicatedError.getDefaultMessage(), StandardCharsets.UTF_8));
    }
}
