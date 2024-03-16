package dev.tolana.touristguide_frontend.dto;

import java.util.Objects;

public class TagDTO {
    private long tag_id;
    private String name;
    public long getTag_id() {
        return tag_id;
    }

    public void setTag_id(long tag_id) {
        this.tag_id = tag_id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagDTO tagDTO = (TagDTO) o;
        return Objects.equals(name, tagDTO.name);
    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name);
//    }

    //    @Override
//    public String toString() {
//        return "TagDTO{" +
//                "name='" + name + '\'' +
//                '}';
//    }
}
