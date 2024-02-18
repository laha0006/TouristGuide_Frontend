package dev.tolana.touristguide_frontend.controller;

import dev.tolana.touristguide_frontend.model.TouristAttraction;
import dev.tolana.touristguide_frontend.service.TouristAttractionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/attractions")
public class TouristAttractionController {

    private final TouristAttractionService touristAttractionService;

    public TouristAttractionController(TouristAttractionService touristAttractionService) {
        this.touristAttractionService = touristAttractionService;
    }

    @GetMapping()
    public String attractions(Model model) {
        List<TouristAttraction> attractionList = touristAttractionService.getAll();
        model.addAttribute("attractionList", attractionList);
        model.addAttribute("activeLink", "attractions");
        return "attractionList";
    }

    //    @GetMapping("/{name}/tags")
    @GetMapping("/add")
    public String addAttraction(Model model) {
        model.addAttribute("activeLink", "add");
        return "addAttraction";
    }
//    @PostMapping("/save")
//    public String save() {
//        return "redirect:/attractions";
//    }
//
//    @GetMapping("/{name}/edit")
//    @PostMapping("/update")
//    @GetMapping("/{name}/delete")


}
