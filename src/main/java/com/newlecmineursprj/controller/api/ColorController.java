package com.newlecmineursprj.controller.api;

import com.newlecmineursprj.entity.Color;
import com.newlecmineursprj.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/colors")
@RequiredArgsConstructor
@RestController("apiColorController")
public class ColorController {

    private final ColorService service;

    @GetMapping
    public ResponseEntity<List<Color>> list(@RequestParam(value="kor-name") String query){
        return ResponseEntity.ok(service.getListByKorName(query));
    }

    @PostMapping
    public ResponseEntity<Color> reg(Color color){
        service.reg(color);

        return ResponseEntity.ok(color);
    }
}
