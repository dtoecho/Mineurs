package com.newlecmineursprj.controller.board;

import com.newlecmineursprj.config.security.WebUserDetails;
import com.newlecmineursprj.entity.Category;
import com.newlecmineursprj.entity.Qna;
import com.newlecmineursprj.entity.QnaCategory;
import com.newlecmineursprj.entity.QnaView;
import com.newlecmineursprj.service.CategoryService;
import com.newlecmineursprj.service.MemberService;
import com.newlecmineursprj.service.QnaCategoryService;
import com.newlecmineursprj.service.QnaService;
import com.newlecmineursprj.util.CustomPageImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("qna")
public class QnaController {

    private final QnaService service;
    private final MemberService MemberService;
    private final QnaCategoryService qnaCategoryService;
    private final CategoryService categoryService;

    @GetMapping
    public String list(@RequestParam(value= "p", defaultValue = "1")int pageNumber
                        , @RequestParam(value = "s", defaultValue = "12")int pageSize
                        , @RequestParam(required = false) String searchMethod
                        , @RequestParam(defaultValue = "") String searchKeyword
                        , @RequestParam(defaultValue = "0") int categoryId
                        , @RequestParam(defaultValue = "0") int dueDate
                        , Model model) {
        CustomPageImpl<QnaView> list = service.getList(pageNumber, pageSize,5,searchMethod, searchKeyword, categoryId,dueDate);
        List<Category> categoryList = categoryService.getList();
        

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("list", list);
        return "/board/qna/list";
    }

    @GetMapping("reg")
    public String regForm() {

        return "board/qna/new";
    }

    @PostMapping("reg")
    public String reg(Qna qna) {
        //사용자 정보 조회
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WebUserDetails webUserDetails = getPrincipal(authentication);
        if (webUserDetails != null) {
            qna.setMemberId(webUserDetails.getId());
        } else {
            qna.setMemberId(null) ;
        }
        service.reg(qna);

        return "redirect:/qna";
    }

    private static WebUserDetails getPrincipal(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof WebUserDetails) {
            return (WebUserDetails)principal;
        }
        return null;
    }

    @GetMapping("{id}")
    public String detail(Model model, @PathVariable Long id , @CookieValue(value = "access_granted", defaultValue = "false") String accessGranted) {
        /*관리자 직접접근 추가*/
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WebUserDetails webUserDetails = getPrincipal(authentication);
        boolean hasRoleAdmin = webUserDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if (!accessGranted.equals("true") && !hasRoleAdmin) {
            return "redirect:/error";
        }
        Qna qna = service.getById(id);
        QnaCategory category = qnaCategoryService.getById(qna.getQnaCategoryId());
        service.increase(id);

        model.addAttribute("qna", service.getById(id));
        if (webUserDetails != null) {
            model.addAttribute("userName", webUserDetails.getUsername());
        }
        model.addAttribute("category", category);

        if (qna.getMemberId() != null)
            model.addAttribute("member", MemberService.getById(qna.getMemberId()));

        return "board/qna/detail";
    }

    @PostMapping("edit")
    public String edit(Qna qna) {
        service.edit(qna);
        return "redirect:/admin/post";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable long id) {
        service.delete(id);
        System.out.println("hi");
        return "redirect:/qna";
    }

    @GetMapping("secretForm")
    public String secretForm(Long id, Model model, @ModelAttribute("errorMessage")Optional<String> errorMessage) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WebUserDetails webUserDetails = getPrincipal(authentication);
        boolean hasRoleAdmin = webUserDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        if(hasRoleAdmin)
            return "redirect:/qna/"+id;

        model.addAttribute("id", id);
        errorMessage.ifPresent(msg -> model.addAttribute("errorMessage",msg));
        return "board/qna/secretForm";
    }

    @PostMapping("secret")
    public String secret(Long id, String password, RedirectAttributes redirectAttributes, HttpServletResponse response) {

        int result = service.getByPassword(id, password);
        if (result == 1) {
            Cookie cookie = new Cookie("access_granted", "true");
            cookie.setMaxAge(60 * 30);
            response.addCookie(cookie);
            return "redirect:/qna/" + id;
        }
        else {
            redirectAttributes.addFlashAttribute("errorMessage", "비밀번호가 잘못되었습니다.");
            redirectAttributes.addAttribute("id", id);
            return "redirect:/qna/secretForm";
        }
    }
}
