package dev.tolana.touristguide_frontend.controller;

import dev.tolana.touristguide_frontend.dto.CityDTO;
import dev.tolana.touristguide_frontend.dto.TagDTO;
import dev.tolana.touristguide_frontend.dto.TouristAttractionDTO;
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
        List<TouristAttractionDTO> attractionList = touristAttractionService.getAll();
        System.out.println(attractionList);
        model.addAttribute("attractionList", attractionList);
        model.addAttribute("activeLink", "attractions");
        return "attractionList";
    }

    @GetMapping("/{name}/tags")
    public String getTagsForAttraction(@PathVariable String name, Model model) {
        System.out.println("### NAME : " + name);
        List<TagDTO> tags = touristAttractionService.getTagsByName(name);
        model.addAttribute("tags", tags);
        model.addAttribute("name", name);
        return "attractionTags";
    }
//    @GetMapping("/add")
//    public String addAttraction(Model model) {
//        model.addAttribute("activeLink", "add");
//        model.addAttribute("attraction", new TouristAttraction());
//        List<Tag> tags = touristAttractionService.getTags();
//        model.addAttribute("tags",tags);
//        List<City> cities = touristAttractionService.getCities();
//        model.addAttribute("cities",cities);
//
//
//        return "addAttraction";
//    }
//    @PostMapping("/save")
//    public String save(@ModelAttribute TouristAttraction attraction) {
//
//        touristAttractionService.addAttraction(attraction);
//        return "redirect:/attractions";
//    }
////
    @GetMapping("/{name}/edit")
    public String editAttraction(@PathVariable String name,Model model) {
        TouristAttractionDTO attraction = touristAttractionService.getAttraction(name);
        model.addAttribute("attraction",attraction);
        List<TagDTO> tags = touristAttractionService.getTags();
        model.addAttribute("tags",tags);
        List<CityDTO> cities = touristAttractionService.getCities();
        System.out.println("#### CITIES.LENGTH " + cities.size());
        System.out.println("#### CITIES[0].name " + cities.get(0).getName());
        System.out.println("#### CITIES[0].id " + cities.get(0).getCity_id());
        model.addAttribute("cities",cities);
        return "editAttraction";
    }
    @PostMapping("/update")
    public String updateAttraction(@ModelAttribute TouristAttractionDTO attraction) {
        System.out.println(attraction.getName());
        System.out.println(attraction.getDescription());
        System.out.println(attraction.getCity().getName());
        System.out.println(attraction.getTags().get(0).getName());
        //touristAttractionService.updateAttraction(attraction);
        return "redirect:/attractions";
    }
//    @GetMapping("/{name}/delete")
//    public String deleteAttraction(@PathVariable String name) {
//        touristAttractionService.deleteAttraction(name);
//        return "redirect:/attractions";
//    }

}
