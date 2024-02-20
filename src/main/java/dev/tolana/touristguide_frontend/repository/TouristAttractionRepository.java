package dev.tolana.touristguide_frontend.repository;

import dev.tolana.touristguide_frontend.model.City;
import dev.tolana.touristguide_frontend.model.Tag;
import dev.tolana.touristguide_frontend.model.TouristAttraction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristAttractionRepository {
    private final List<TouristAttraction> attractionList;

    public TouristAttractionRepository() {
        attractionList = new ArrayList<>();
        attractionList.add(new TouristAttraction("DenLilleFede",
                "Fine dining når det er bedst.",
                City.COPENHAGEN,
                List.of(Tag.PAID)));
        attractionList.add(new TouristAttraction("McD","Best coffee after Wine Course",
                City.COPENHAGEN,
                List.of(Tag.PAID,Tag.FAMILY_FRIENDLY)));

    }

    public List<TouristAttraction> getAll() {
        return attractionList;
    }

    public TouristAttraction getAttraction(String name) {
        for(TouristAttraction ta : attractionList) {
            if (ta.getName().equalsIgnoreCase(name)) {
                return ta;
            }
        }
        return null;
    }

    public TouristAttraction addAttraction(TouristAttraction attraction) {
        attractionList.add(attraction);
        return attraction;
    }

    public TouristAttraction updateAttraction(TouristAttraction attraction) {
        for(TouristAttraction ta : attractionList) {
            if(ta.getName().equalsIgnoreCase(attraction.getName())) {
                ta.setCity(attraction.getCity());
                ta.setDescription(attraction.getDescription());
                ta.setTags(attraction.getTags());
                return ta;
            }
        }
        return null;
    }

    public TouristAttraction deleteAttraction(String name) {
        for(TouristAttraction ta : attractionList) {
            if(ta.getName().equalsIgnoreCase(name)) {
                attractionList.remove(ta);
                return ta;
            }
        }
        return null;
    }

    public List<Tag> getTagsByName(String name) {
        for(TouristAttraction attraction : attractionList) {
            if(attraction.getName().equalsIgnoreCase(name)) {
                return attraction.getTags();
            }
        }
        return null;
    }

    public List<Tag> getTags() {
        return new ArrayList<>(List.of(Tag.values()));
    }

    public List<City> getCities() {
        return new ArrayList<>(List.of(City.values()));
    }
}
