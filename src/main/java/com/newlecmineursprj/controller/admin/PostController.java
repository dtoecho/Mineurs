package com.newlecmineursprj.controller.admin;

import com.newlecmineursprj.entity.PostView;
import com.newlecmineursprj.entity.QnaCategory;
import com.newlecmineursprj.service.PostService;
import com.newlecmineursprj.service.QnaCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.server.Cookie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("admin/post")
@Controller("adminPostController")
@RequiredArgsConstructor
public class PostController {

private final PostService service;
private final QnaCategoryService categoryService;

    @GetMapping
    public String list(
            @RequestParam(required = false,defaultValue = "1") Integer page,
            @RequestParam(required = false,defaultValue = "") String searchMethod,
            @RequestParam(required = false,defaultValue = "") String searchKeyword,
            @RequestParam(required = false,defaultValue = "0") Integer boardId,
            @RequestParam(required = false,defaultValue = "") String qnaCategory,
            Model model) {

        int count = service.getCount(page,searchMethod,searchKeyword,boardId,qnaCategory);

        List<PostView> list = service.getList(page,searchMethod,searchKeyword.trim(),boardId,qnaCategory);
        List<QnaCategory> categories = categoryService.getList();


        model.addAttribute("list", list);
        model.addAttribute("count", count);
        model.addAttribute("categories", categories);

        return "admin/post/list";
    }
    @GetMapping("preview")
    public String regForm() {

        return "admin/post/preview";
    }
}
