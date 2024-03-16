//package dev.tolana.touristguide_frontend.repository;
//
//import dev.tolana.touristguide_frontend.model.City;
//import dev.tolana.touristguide_frontend.model.Tag;
//import dev.tolana.touristguide_frontend.model.TouristAttraction;
//import dev.tolana.touristguide_frontend.repository.TouristAttractionRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TouristAttractionRepositoryTests {
//
//    private TouristAttractionRepository repository;
//
////    @BeforeEach
////    public void init() {
////        repository = new TouristAttractionRepository();
////    }
//
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
//
//}
