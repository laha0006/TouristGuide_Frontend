package dev.tolana.touristguide_frontend.repository;

import dev.tolana.touristguide_frontend.model.City;
import dev.tolana.touristguide_frontend.model.Tag;
import dev.tolana.touristguide_frontend.model.TouristAttraction;
import dev.tolana.touristguide_frontend.repository.TouristAttractionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class TouristAttractionRepositoryTests {

    private TouristAttractionRepository repository;

    private List<TouristAttraction> expectedResultList;

    @BeforeEach
    public void init() {
        List<TouristAttraction> temp = new ArrayList<>();
        temp.add(new TouristAttraction("DenLilleFede",
                "Fine dining når det er bedst.",
                City.COPENHAGEN,
                List.of(Tag.PAID)));
        temp.add(new TouristAttraction("McD",
                "Best coffee after Wine Course",
                City.COPENHAGEN,
                List.of(Tag.PAID,Tag.FAMILY_FRIENDLY)));
        expectedResultList = temp;
        repository = new TouristAttractionRepository();
    }


    @Test
    public void getAllTest() {
        // arrange
        int expectedSize = expectedResultList.size();
        String expectedFirstResultName = expectedResultList.get(0).getName();
        String expectedSecondResultName = expectedResultList.get(1).getName();

        // act
        List<TouristAttraction> resultList = repository.getAll();

        // assert
        assertEquals(expectedSize,resultList.size());
        assertEquals(expectedFirstResultName,resultList.get(0).getName());
        assertEquals(expectedSecondResultName,resultList.get(1).getName());

    }

    //get attraction
    @Test
    public void addAttractionTest() {
        //arrange
        TouristAttraction touristAttractionToAdd = new TouristAttraction("Den Lille Havfrue",
                "Greatest attraction in Copenhagen",City.COPENHAGEN,List.of(Tag.FREE));

        //act
        repository.addAttraction(touristAttractionToAdd);
        TouristAttraction resultAttraction = repository.getAttraction("Den Lille Havfrue");

        //assert
        assertEquals(touristAttractionToAdd.getName(),resultAttraction.getName());

    }
    //add attraction
    //update attraction
    //delete attraction
    //getTagsByName
    //

}
