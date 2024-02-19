package dev.tolana.touristguide_frontend.service;

import dev.tolana.touristguide_frontend.model.City;
import dev.tolana.touristguide_frontend.model.Tag;
import dev.tolana.touristguide_frontend.model.TouristAttraction;
import dev.tolana.touristguide_frontend.repository.TouristAttractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristAttractionService {
    private final TouristAttractionRepository repository;

    public TouristAttractionService(TouristAttractionRepository repository) {
        this.repository = repository;
    }

    public List<TouristAttraction> getAll() {
        return repository.getAll();
    }

    public TouristAttraction getAttraction(String name) {
        return repository.getAttraction(name);
    }

    public TouristAttraction addAttraction(TouristAttraction attraction) {
        return repository.addAttraction(attraction);
    }

    public TouristAttraction updateAttraction(TouristAttraction attraction) {
        return repository.updateAttraction(attraction);
    }

    public TouristAttraction deleteAttraction(String name) {
        return repository.deleteAttraction(name);
    }

    public List<Tag> getTagsByName(String name) {
        return repository.getTagsByName(name);
    }

    public List<Tag> getTags() {
        return repository.getTags();
    }

    public List<City> getCities() {
        return repository.getCities();
    }
}
