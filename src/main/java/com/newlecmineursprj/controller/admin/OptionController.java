package com.newlecmineursprj.controller.admin;

import com.newlecmineursprj.entity.Color;
import com.newlecmineursprj.entity.Size;
import com.newlecmineursprj.service.ColorService;
import com.newlecmineursprj.service.SizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/options")
@Slf4j
public class OptionController {

    private final ColorService colorService;
    private final SizeService sizeService;

    @GetMapping
    public String list(Model model) {
        List<Color> colors = colorService.getList();
        List<Size> sizes = sizeService.getList();

        model.addAttribute("colors", colors);
        model.addAttribute("sizes", sizes);
        return "admin/option/list";
    }

    @PostMapping("add-color")
    public String addColor(Color color){
        colorService.reg(color);
        return "redirect:/admin/options";
    }

    @PostMapping("add-size")
    public String addColor(Size size){
        sizeService.reg(size);
        return "redirect:/admin/options";
    }

    @PostMapping("del-colors")
    public String delColors(@RequestParam("colorIds") List<Long> colorIds){
        colorService.deleteAllById(colorIds);
        return "redirect:/admin/options";
    }

    @PostMapping("del-sizes")
    public String delSizes(@RequestParam("sizeIds") List<Long> sizeIds){
        sizeService.deleteAllById(sizeIds);
        return "redirect:/admin/options";
    }

}
