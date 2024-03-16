package dev.tolana.touristguide_frontend.repository;

import dev.tolana.touristguide_frontend.dto.CityDTO;
import dev.tolana.touristguide_frontend.dto.TagDTO;
import dev.tolana.touristguide_frontend.dto.TouristAttractionDTO;
import dev.tolana.touristguide_frontend.model.TouristAttraction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import javax.swing.text.html.HTML;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristAttractionRepository {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    private final DataSource dataSource;

    public TouristAttractionRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<TouristAttractionDTO> getAll() {
        List<TouristAttractionDTO> attractions = new ArrayList<>();
        String SQL = """
                SELECT *
                FROM attraction
                     JOIN      city
                               ON attraction.city_id = city.city_id
                     LEFT JOIN attraction_tags
                               ON attraction.attraction_id = attraction_tags.attraction_id
                     LEFT JOIN tag
                               ON attraction_tags.tag_id = tag.tag_id;
                               """;
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            TouristAttractionDTO touristAttractionDTO = new TouristAttractionDTO();
            long current = 0;
            while (rs.next()) {

                long attractionID = rs.getLong("attraction_id");
                String attractionName = rs.getString("attraction.name");
                String description = rs.getString("description");

                String cityName = rs.getString("city.name");
                long city_id = rs.getLong("city_id");

                String tagName = rs.getString("tag.name");
                long tag_id = rs.getLong("tag_id");

                if (current != attractionID) {
                    if (current != 0) {
                        touristAttractionDTO = new TouristAttractionDTO();
                    }
                    current = attractionID;
                    attractions.add(touristAttractionDTO);

                    touristAttractionDTO.setAttractionID(attractionID);
                    touristAttractionDTO.setName(attractionName);
                    touristAttractionDTO.setDescription(description);

                    CityDTO cityDTO = new CityDTO();
                    cityDTO.setName(cityName);
                    cityDTO.setCity_id(city_id);
                    touristAttractionDTO.setCity(cityDTO);

                    TagDTO tagDTO = new TagDTO();
                    tagDTO.setName(tagName);
                    tagDTO.setTag_id(tag_id);
                    touristAttractionDTO.setTags(List.of(tagDTO));

                } else {
                    TagDTO tagDTO = new TagDTO();
                    tagDTO.setName(tagName);
                    tagDTO.setTag_id(tag_id);
                    touristAttractionDTO.addTag(tagDTO);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return attractions;
    }

    //
    public TouristAttractionDTO getAttraction(String name) {
        TouristAttractionDTO touristAttractionDTO = new TouristAttractionDTO();
        String SQL = """
                SELECT *
                FROM attraction
                     JOIN      city
                               ON attraction.city_id = city.city_id
                     LEFT JOIN attraction_tags
                               ON attraction.attraction_id = attraction_tags.attraction_id
                     LEFT JOIN tag
                               ON attraction_tags.tag_id = tag.tag_id
                WHERE attraction.name = ?;
                """;
        try (Connection con = dataSource.getConnection()) {

            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            List<TagDTO> tags = new ArrayList<>();
            long current = 0;

            while (rs.next()) {

                long attractionID = rs.getLong("attraction_id");
                String attractionName = rs.getString("attraction.name");
                String description = rs.getString("description");

                String cityName = rs.getString("city.name");
                long city_id = rs.getLong("city_id");

                String tagName = rs.getString("tag.name");
                long tag_id = rs.getLong("tag_id");

                if (current != attractionID) {
                    current = attractionID;

                    touristAttractionDTO.setAttractionID(attractionID);
                    touristAttractionDTO.setName(attractionName);
                    touristAttractionDTO.setDescription(description);

                    CityDTO cityDTO = new CityDTO();
                    cityDTO.setName(cityName);
                    cityDTO.setCity_id(city_id);
                    touristAttractionDTO.setCity(cityDTO);

                    TagDTO tagDTO = new TagDTO();
                    tagDTO.setName(tagName);
                    tagDTO.setTag_id(tag_id);
                    touristAttractionDTO.setTags(List.of(tagDTO));

                } else {
                    TagDTO tagDTO = new TagDTO();
                    tagDTO.setName(tagName);
                    tagDTO.setTag_id(tag_id);
                    touristAttractionDTO.addTag(tagDTO);
                }
            }
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        return touristAttractionDTO;
    }

    //
//    public TouristAttraction addAttraction(TouristAttractionDTO attraction) {
//        String insertAttractionSQL = "INSERT INTO attraction (name,description,city_id) VALUES(?,?,?)";
//        String insertAttractionTagsSQL = "INSERT INTO attraction_tags VALUES(?,?,?)";
//    }
//
//    public TouristAttractionDTO updateAttraction(TouristAttractionDTO attraction) {
//        String updateAttractionSQL;
//        String updateTags
//    }

    //    }
//
//    public TouristAttraction deleteAttraction(String name) {
//        String DeleteAttractionTagsSQL = "DELETE\n" +
//                "FROM attraction_tags\n" +
//                "WHERE attraction_id = (SELECT attraction_id FROM attraction WHERE name = name)\n";
//        String DeleteAttractionSQL = "DELETE\n" +
//                "FROM attraction\n" +
//                "WHERE attraction_id = (SELECT attraction_id FROM attraction WHERE name = name)";
//    }

    //
    public List<TagDTO> getTagsByName(String name) {
//        touristAttractionTagsDTO.setName(name);
        List<TagDTO> tags = new ArrayList<>();
        String SQL = "SELECT name\n" +
                "FROM attraction_tags\n" +
                "     JOIN tag\n" +
                "          ON attraction_tags.tag_id = tag.tag_id\n" +
                "WHERE attraction_id = (SELECT attraction_id FROM attraction WHERE name = ?);";
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement ps = con.prepareStatement(SQL);
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TagDTO tagDTO = new TagDTO();
                tagDTO.setName(rs.getString("name"));
                tags.add(tagDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tags;
    }


    //
    public List<TagDTO> getTags() {
        List<TagDTO> tags = new ArrayList<>();
        String SQL = "SELECT * FROM tag;";
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                TagDTO tagDTO = new TagDTO();
                tagDTO.setName(rs.getString("name"));
                tagDTO.setTag_id(rs.getLong("tag_id"));
                tags.add(tagDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tags;
    }

    public List<CityDTO> getCities() {
        List<CityDTO> cities = new ArrayList<>();
        String SQL = "SELECT * FROM city;";
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement ps = con.prepareStatement(SQL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CityDTO cityDTO = new CityDTO();
                String cityName = rs.getString("name");
                long city_id = rs.getLong("city_id");
                cityDTO.setName(cityName);
                cityDTO.setCity_id(city_id);
                cities.add(cityDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }
}
