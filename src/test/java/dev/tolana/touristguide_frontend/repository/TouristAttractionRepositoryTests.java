package dev.tolana.touristguide_frontend.repository;

import dev.tolana.touristguide_frontend.dto.CityDTO;
import dev.tolana.touristguide_frontend.dto.TagDTO;
import dev.tolana.touristguide_frontend.dto.TouristAttractionDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
@Testcontainers
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TouristAttractionRepositoryTests {

    @Container
    @ServiceConnection
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0.36").withInitScript("init.sql");


    @Autowired
    TouristAttractionRepository repo;
    @Test
    public void getAllTest() {
        List<TouristAttractionDTO> attractions = repo.getAll();
        assertEquals(6,attractions.size());
    }

    @Test
    public void addAttraction() {
        TouristAttractionDTO attractionToAdd = new TouristAttractionDTO();

        CityDTO cityDTO = new CityDTO();
        cityDTO.setName("København");
        cityDTO.setCity_id(1);

        attractionToAdd.setCity(cityDTO);
        attractionToAdd.setDescription("Hello world test");
        attractionToAdd.setName("TestAttraction");

        TagDTO tagDTO = new TagDTO();
        tagDTO.setTag_id(2);
        tagDTO.setName("Free");
        attractionToAdd.setTags(List.of(tagDTO));

        repo.addAttraction(attractionToAdd);

        List<TouristAttractionDTO> attractions = repo.getAll();
        TouristAttractionDTO attraction = repo.getAttraction("TestAttraction");

        assertEquals(7,attractions.size()); // 6 original size, so if we add one, we expect 7.
        assertAll(
                () -> assertEquals(attractionToAdd.getName(),attraction.getName()),
                () -> assertEquals(attractionToAdd.getCityId(),attraction.getCityId()),
                () -> assertEquals(attractionToAdd.getDescription(),attraction.getDescription())
        );
    }


//    @Test
//    public void getAllTest() {
//        // arrange
//        List<TouristAttraction> expectedResultList = new ArrayList<>();
//        expectedResultList.add(new TouristAttraction("DenLilleFede",
//                "Fine dining når det er bedst.",
//                City.COPENHAGEN,
//                List.of(Tag.PAID)));
//        expectedResultList.add(new TouristAttraction("McD",
//                "Best coffee after Wine Course",
//                City.COPENHAGEN,
//                List.of(Tag.PAID, Tag.FAMILY_FRIENDLY)));
//        int expectedSize = expectedResultList.size();
//        String expectedFirstResultName = expectedResultList.get(0).getName();
//        String expectedSecondResultName = expectedResultList.get(1).getName();
//
//        // act
//        List<TouristAttraction> resultList = repository.getAll();
//
//        // assert
//        assertEquals(expectedSize, resultList.size());
//        assertEquals(expectedFirstResultName, resultList.get(0).getName());
//        assertEquals(expectedSecondResultName, resultList.get(1).getName());
//
//    }
//
//    @Test
//    public void addAttractionTest() {
//        //arrange
//        TouristAttraction touristAttractionToAdd = new TouristAttraction("Den Lille Havfrue",
//                "Greatest attraction in Copenhagen", City.COPENHAGEN, List.of(Tag.FREE));
//
//        //act
//        repository.addAttraction(touristAttractionToAdd);
//        TouristAttraction resultAttraction = repository.getAttraction("Den Lille Havfrue");
//
//        //assert
//        assertEquals(touristAttractionToAdd.getName(), resultAttraction.getName());
//    }
//
//    @Test
//    public void updateAttractionTest() {
//        //arrange
//        String updatedDescription = "Mcdonalds is not that great";
//        TouristAttraction attractionToUpdate = repository.getAttraction("McD");
//        attractionToUpdate.setDescription(updatedDescription);
//
//        //act
//        repository.updateAttraction(attractionToUpdate);
//        TouristAttraction resultAttraction = repository.getAttraction("McD");
//        String resultDescription = resultAttraction.getDescription();
//
//        //assert
//        assertEquals(updatedDescription, resultDescription);
//    }
//
//    @Test
//    public void deleteAttractionTest() {
//        //arrange
//        String attractionToDelete = "McD";
//
//        //act
//        repository.deleteAttraction("McD");
//        List<TouristAttraction> result = repository.getAll();
//        int resultSize = result.size();
//        TouristAttraction McD = repository.getAttraction("McD");
//
//        //assert
//        assertNull(McD);
//        assertEquals(1, resultSize);
//    }
//
//    @Test
//    public void getTagsByNameTest() {
//        //arrange
//        String nameOfTagsToGet = "McD";
//
//        //act
//        List<String> result = repository.getTagsByName(nameOfTagsToGet);
//        int tagsCount = result.size();
//        //assert
//
//        assertEquals(2, tagsCount);
//        assertTrue(result.contains(Tag.PAID));
//        assertTrue(result.contains(Tag.FAMILY_FRIENDLY));
//    }

}
