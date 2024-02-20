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
        model.addAttribute("name", name);
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
        System.out.println(attraction.getTags().size());
        System.out.println(attraction.getTags());
        touristAttractionService.addAttraction(attraction);
        return "redirect:/attractions";
    }
//
    @GetMapping("/{name}/edit")
    public String editAttraction(@PathVariable String name,Model model) {
        TouristAttraction attraction = touristAttractionService.getAttraction(name);
        model.addAttribute("attraction",attraction);
        List<Tag> tags = touristAttractionService.getTags();
        model.addAttribute("tags",tags);
        List<City> cities = touristAttractionService.getCities();
        model.addAttribute("cities",cities);
        return "editAttraction";
    }
    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttraction attraction) {
        touristAttractionService.updateAttraction(attraction);
        return "redirect:/attractions";
    }
    @GetMapping("/{name}/delete")
    public String deleteAttraction(@PathVariable String name) {
        touristAttractionService.deleteAttraction(name);
        return "redirect:/attractions";
    }

}
