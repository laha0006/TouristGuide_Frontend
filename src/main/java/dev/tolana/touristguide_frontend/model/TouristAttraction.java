package dev.tolana.touristguide_frontend.model;

import java.util.ArrayList;
import java.util.List;

public class TouristAttraction {
    private String name;
    private String description;
    private City city;

    private List<Tag> tags;

    public TouristAttraction() {
        //tags = new ArrayList<>();
    }

    public TouristAttraction(String name, String description,City city, List<Tag> tags) {
        this.name = name;
        this.description = description;
        this.city = city;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof TouristAttraction touristAttraction)) return false;
        if (name == null && touristAttraction.getName() == null ) return true;
        return name.equals(touristAttraction.getName());
    }

}
