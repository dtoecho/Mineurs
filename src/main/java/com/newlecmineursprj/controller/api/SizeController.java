package com.newlecmineursprj.controller.api;

import com.newlecmineursprj.entity.Size;
import com.newlecmineursprj.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/sizes")
@RequiredArgsConstructor
@RestController("apiSizeController")
public class SizeController {

    private final SizeService service;

    @GetMapping
    public ResponseEntity<List<Size>> list(@RequestParam(value="kor-name") String query){
        return ResponseEntity.ok(service.getListByKorName(query));
    }
}
