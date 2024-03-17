package dev.tolana.touristguide_frontend.dto;

import java.util.List;

public class TouristAttractionDTO {
    public long getAttractionID() {
        return attractionID;
    }

    public void setAttractionID(long attractionID) {
        this.attractionID = attractionID;
    }

    private long attractionID;
    private String name;
    private String description;
    private CityDTO city;
    private List<TagDTO> tags;



    public void addTag(TagDTO tagDTO) {
        tags.add(tagDTO);
    }
    public List<TagDTO> getTags() {
        return tags;
    }
    public void setTags(List<TagDTO> tags) {
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

    public long getCityId() {
        return city.getCity_id();
    }
}
