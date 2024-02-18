package dev.tolana.touristguide_frontend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/attractions")
public class TouristAttractionController {

//    private final TouristAttractionService

    @GetMapping()
    public String attractions() {
        return "attractions";
    }

//    @GetMapping("/{name}/tags")
//    @GetMapping("/add")
//    @PostMapping("/save")
//    public String save() {
//        return "redirect:/attractions";
//    }
//
//    @GetMapping("/{name}/edit")
//    @PostMapping("/update")
//    @GetMapping("/{name}/delete")


}
