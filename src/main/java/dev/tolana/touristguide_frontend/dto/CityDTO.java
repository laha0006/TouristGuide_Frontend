package dev.tolana.touristguide_frontend.dto;

import java.util.Objects;

public class CityDTO {
    private long city_id;
    private String name;

    public long getCity_id() {
        return city_id;
    }

    public void setCity_id(long city_id) {
        this.city_id = city_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityDTO cityDTO = (CityDTO) o;
        return city_id == cityDTO.city_id && Objects.equals(name, cityDTO.name);
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(city_id, name);
//    }
}
