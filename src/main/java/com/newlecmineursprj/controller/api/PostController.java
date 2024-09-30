package com.newlecmineursprj.controller.api;

import com.newlecmineursprj.entity.PostView;
import com.newlecmineursprj.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/post")
@RestController("adminRestPostController")
public class PostController {

    @Autowired
    PostService service;

    @GetMapping("{id}")
    public PostView preview(@PathVariable Long id) {
        return service.getById(id);
    }

}
