//package dev.tolana.touristguide_frontend.controller;
//
//import dev.tolana.touristguide_frontend.model.City;
//import dev.tolana.touristguide_frontend.model.Tag;
//import dev.tolana.touristguide_frontend.model.TouristAttraction;
//import dev.tolana.touristguide_frontend.repository.TouristAttractionRepository;
//import dev.tolana.touristguide_frontend.service.TouristAttractionService;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentMatchers;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.List;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.mockito.ArgumentMatchers.contains;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//import static org.mockito.BDDMockito.given;
//@WebMvcTest(TouristAttractionController.class)
//public class TouristAttractionControllerTests {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private TouristAttractionService service;
//
//    private TouristAttraction testAttraction = new TouristAttraction("Test",
//            "tested",City.COPENHAGEN,
//            List.of(Tag.FREE,Tag.PAID));
//
//    @Test
//    public void attractionsEndpointTest() throws Exception {
//        mockMvc.perform(get("/attractions"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("attractionList"));
//    }
//
//    @Test
//    public void addAttractionEndpointTest() throws Exception {
//        mockMvc.perform(get("/attractions/add"))
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("attraction", new TouristAttraction()))
//                .andExpect(view().name("addAttraction"));
//    }
//
//    @Test
//    public void saveAttractionPostEndpointTest() throws Exception {
//        mockMvc.perform(post("/attractions/save"))
//                .andExpect(status().is3xxRedirection())
//                .andExpect(view().name("redirect:/attractions"));
//    }
//
//    @Test
//    public void attractionTagEndpointTest() throws Exception {
//        mockMvc.perform(get("/attractions/DenLilleFede/tags"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("DenLilleFede")))
//                .andExpect(view().name("attractionTags"));
//    }
//
//
//
//    @Test
//    public void attractionEditEndpointTest() throws Exception {
//        //    have to mock the service method calls,
//        //    getAttraction, getTags, getCities
//        //    since we're mocking the service class, this is necessary.
//        TouristAttraction attractionToEdit = new TouristAttraction("DenLilleFede",
//                "Fine dining når det er bedst.",
//                City.COPENHAGEN,
//                List.of(Tag.PAID));
//        given(service.getAttraction(ArgumentMatchers.any())).willReturn(attractionToEdit);
//        given(service.getCities()).willReturn(List.of(City.values()));
//        given(service.getTags()).willReturn(List.of(Tag.values()));
//
//        mockMvc.perform(get("/attractions/DenLilleFede/edit"))
//                .andExpect(status().isOk())
//                .andExpect(model().attribute("attraction",attractionToEdit))
//                .andExpect(model().attribute("tags",List.of(Tag.values())))
//                .andExpect(model().attribute("cities",List.of(City.values())))
//                .andExpect(view().name("editAttraction"));
//    }
//
//}
