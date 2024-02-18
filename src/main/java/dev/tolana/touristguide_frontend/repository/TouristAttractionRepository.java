package dev.tolana.touristguide_frontend.repository;

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
                "Fine dining når det er bedst."));
        attractionList.add(new TouristAttraction("McD","Best coffee after Wine Course"));

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
                ta.setDescription(attraction.getDescription());
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
}
