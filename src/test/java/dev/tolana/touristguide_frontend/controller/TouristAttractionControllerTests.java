package dev.tolana.touristguide_frontend.controller;

import dev.tolana.touristguide_frontend.model.City;
import dev.tolana.touristguide_frontend.model.Tag;
import dev.tolana.touristguide_frontend.model.TouristAttraction;
import dev.tolana.touristguide_frontend.repository.TouristAttractionRepository;
import dev.tolana.touristguide_frontend.service.TouristAttractionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TouristAttractionController.class)
@AutoConfigureMockMvc(addFilters = false)
public class TouristAttractionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TouristAttractionService service;

    @Test
    public void attractionsEndpointTest() throws Exception {
        mockMvc.perform(get("/attractions"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractionList"));
    }

    @Test
    public void addAttractionEndpointTest() throws Exception {
        mockMvc.perform(get("/attractions/add"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("attraction", new TouristAttraction()))
                .andExpect(view().name("addAttraction"));
    }

    @Test
    public void saveAttractionPostEndpointTest() throws Exception {
        mockMvc.perform(post("/attractions/save"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/attractions"));
    }

    @Test
    public void attractionTagEndpointTest() throws Exception {
        mockMvc.perform(get("/attractions/DenLilleFede/tags"))
                .andExpect(status().isOk())
                .andExpect(view().name("attractionTags"));
    }


    //can't test edit Endpoint with mocks ,
    //because tempalte processing fails,
    //since it's not getting a proper TouristAttraction
    //so *{name} on line 15 in editAttraction fails to process
//    @Test
//    public void attractionEditEndpointTest() throws Exception {
//        mockMvc.perform(get("/attractions/DenLilleFede/edit"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("editAttraction"));
//    }
}
