package dev.tolana.touristguide_frontend.service;

import dev.tolana.touristguide_frontend.dto.CityDTO;
import dev.tolana.touristguide_frontend.dto.TagDTO;
import dev.tolana.touristguide_frontend.dto.TouristAttractionDTO;
import dev.tolana.touristguide_frontend.repository.TouristAttractionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TouristAttractionService {
    private final TouristAttractionRepository repository;

    public TouristAttractionService(TouristAttractionRepository repository) {
        this.repository = repository;
    }

    public List<TouristAttractionDTO> getAll() {
        return repository.getAll();
    }

    public TouristAttractionDTO getAttraction(String name) {
        return repository.getAttraction(name);
    }

    public TouristAttractionDTO addAttraction(TouristAttractionDTO attraction) {
        return repository.addAttraction(attraction);
    }

    public TouristAttractionDTO updateAttraction(TouristAttractionDTO attraction) {
        return repository.updateAttraction(attraction);
    }

    public TouristAttractionDTO deleteAttraction(String name) {
        return repository.deleteAttraction(name);
    }

    public List<TagDTO> getTagsByName(String name) {
        return repository.getTagsByName(name);
    }

    public List<TagDTO> getTags() {
        return repository.getTags();
    }

    public List<CityDTO> getCities() {
        return repository.getCities();
    }
}
