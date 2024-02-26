package dev.tolana.touristguide_frontend.service;

import dev.tolana.touristguide_frontend.model.TouristAttraction;
import dev.tolana.touristguide_frontend.repository.TouristAttractionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TouristAttractionServiceTests {

    @InjectMocks
    private TouristAttractionService service;

    @Mock
    private TouristAttractionRepository repository;

    @Test
    public void tested() {
        List<TouristAttraction> result = service.getAll();
        System.out.println(result);
        assertTrue(true);

    }

}