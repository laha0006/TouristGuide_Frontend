package dev.tolana.touristguide_frontend.controller;

import dev.tolana.touristguide_frontend.model.City;
import dev.tolana.touristguide_frontend.model.Tag;
import dev.tolana.touristguide_frontend.model.TouristAttraction;
import dev.tolana.touristguide_frontend.service.TouristAttractionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{name}/tags")
    public String getTagsForAttraction(@PathVariable String name, Model model) {
        List<Tag> tags = touristAttractionService.getTagsByName(name);
        model.addAttribute("tags", tags);
        return "attractionTags";
    }
    @GetMapping("/add")
    public String addAttraction(Model model) {
        model.addAttribute("activeLink", "add");
        model.addAttribute("attraction", new TouristAttraction());
        List<Tag> tags = touristAttractionService.getTags();
        model.addAttribute("tags",tags);
        List<City> cities = touristAttractionService.getCities();
        model.addAttribute("cities",cities);


        return "addAttraction";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute TouristAttraction attraction) {
        System.out.println("Attraction: " + attraction);
        touristAttractionService.addAttraction(attraction);
        return "redirect:/attractions";
    }
//
//    @GetMapping("/{name}/edit")
//    @PostMapping("/update")
//    @GetMapping("/{name}/delete")


}
