package com.newlecmineursprj.controller.board;

import com.newlecmineursprj.config.security.WebUserDetails;
import com.newlecmineursprj.entity.Notice;
import com.newlecmineursprj.service.NoticeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("notices")
@Controller
@RequiredArgsConstructor
@Slf4j
public class NoticeController {

    private final NoticeService service;
    @GetMapping
    public String list(Model model){

        model.addAttribute("searchMethods", service.findAllSearchMethods());
        model.addAttribute("list", service.findAll());
        return "board/notice/list";

    }



    @GetMapping("{id}")
    public String detail(Model model, @PathVariable Long id){
        model.addAttribute("notice", service.findById(id));
        return "board/notice/detail";
    }

    @GetMapping("reg")
    public String regForm(){
        return "board/notice/new";
    }

    @PostMapping("reg")
    public String reg(Notice notice, @AuthenticationPrincipal WebUserDetails webUserDetails){
        if (webUserDetails != null) {

            service.reg(notice, webUserDetails.getId());
            return "redirect:/notices";
        }
        return "redirect:/notices";
    }

    @PostMapping("delete")
    public String delete(@RequestParam List<Long> noticeIds){
        log.debug("noticeIds: {}", noticeIds);
        service.deleteAll(noticeIds);

        return "redirect:/notices";
    }

    @GetMapping("{id}/edit")
    public String editForm(Model model, @PathVariable Long id){
        model.addAttribute("notice", service.findById(id));
        return "/board/notice/edit";
    }
    @PostMapping("{id}/edit")
    public String edit(@PathVariable Long id, Notice notice, @AuthenticationPrincipal WebUserDetails webUserDetails){
        if (webUserDetails != null) {
            notice.setId(id);
            service.update(notice, webUserDetails.getId());
            return "redirect:/notices";
        }
        return "redirect:/notices";
    }
}
