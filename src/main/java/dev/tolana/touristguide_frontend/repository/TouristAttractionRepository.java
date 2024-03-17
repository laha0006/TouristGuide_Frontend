package dev.tolana.touristguide_frontend.repository;

import dev.tolana.touristguide_frontend.dto.CityDTO;
import dev.tolana.touristguide_frontend.dto.TagDTO;
import dev.tolana.touristguide_frontend.dto.TouristAttractionDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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
        List<TouristAttractionDTO> attractions;

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

            attractions = mapResultSetToTouristAttractionDtoList(rs);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return attractions;
    }


    public TouristAttractionDTO getAttraction(String name) {
        TouristAttractionDTO touristAttractionDTO;

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

            touristAttractionDTO = mapResultSetToTouristAttractionDtoList(rs).get(0);

        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
        return touristAttractionDTO;
    }

    public TouristAttractionDTO addAttraction(TouristAttractionDTO attraction) {

        String insertAttractionSQL = """
                INSERT INTO attraction (name,description,city_id) VALUES(?,?,?)""";


        int tagCount = attraction.getTags().size();
        String insertAttractionTagsSQL = createAttractionTagsInsertSqlString(tagCount);


        try (Connection con = dataSource.getConnection()) {
            con.setAutoCommit(false);
            PreparedStatement attractionInsertPs = con.prepareStatement(insertAttractionSQL, Statement.RETURN_GENERATED_KEYS);

            attractionInsertPs.setString(1, attraction.getName());
            attractionInsertPs.setString(2, attraction.getDescription());
            attractionInsertPs.setLong(3, attraction.getCityId());

            int affectedRows = attractionInsertPs.executeUpdate();
            if (affectedRows != 1) {
                return new TouristAttractionDTO();
            }

            long attraction_id;
            ResultSet keys = attractionInsertPs.getGeneratedKeys();
            if (keys.next()) {
                attraction_id = keys.getLong(1);
                attraction.setAttractionID(attraction_id); //set id for return object.
            } else {
                return new TouristAttractionDTO();
            }

            PreparedStatement attractionTagsInsertPs = con.prepareStatement(insertAttractionTagsSQL);
            List<TagDTO> tags = attraction.getTags();
//            for (int i = 0; i < tagCount; i++) {
//                int attractionIdParamIndex = 1 + (2 * i); // first iteration 1, then 3,5,7...
//                int tagIdParamIndex = 2 + (2 * i);        // first iteration 2, then 4,6,8...
//                long tag_id = tags.get(i).getTag_id();
//
//                attractionTagsInsertPs.setLong(attractionIdParamIndex, attraction_id);
//                attractionTagsInsertPs.setLong(tagIdParamIndex, tag_id);
//            }
            setTagsPlaceHolderValues(attractionTagsInsertPs,tags,attraction_id);

            int affectedAttractionTagsRows = attractionTagsInsertPs.executeUpdate();
            if (affectedAttractionTagsRows == tagCount) {
                con.commit();
                con.setAutoCommit(true);
            } else {
                return new TouristAttractionDTO();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return attraction;
    }

    public TouristAttractionDTO updateAttraction(TouristAttractionDTO attraction) {
        String updateAttractionSQL = """
                UPDATE attraction
                SET description = ?, city_id = ?
                WHERE name = ?;
                """;
        String deleteTagsSQL = """
                DELETE FROM attraction_tags
                WHERE attraction_id = (SELECT attraction_id FROM attraction WHERE name = ?)
                """;
        int tagCount = attraction.getTags().size();
        String insertAttractionTagsSQL = createAttractionTagsInsertSqlString(tagCount);

        try (Connection con = dataSource.getConnection()) {
            con.setAutoCommit(false);

            PreparedStatement updateAttractionPs = con.prepareStatement(updateAttractionSQL);
            updateAttractionPs.setString(1,attraction.getDescription());
            updateAttractionPs.setLong(2,attraction.getCityId());
            updateAttractionPs.setString(3,attraction.getName());

            updateAttractionPs.executeUpdate();

            PreparedStatement deleteAttractionTagsPs = con.prepareStatement(deleteTagsSQL);
            deleteAttractionTagsPs.setString(1,attraction.getName());
            deleteAttractionTagsPs.executeUpdate();

            PreparedStatement insertAttractionTagsPs = con.prepareStatement(insertAttractionTagsSQL);
            setTagsPlaceHolderValues(insertAttractionTagsPs,attraction.getTags(),attraction.getAttractionID());
            int tagsInsertAffectedRows = insertAttractionTagsPs.executeUpdate();
            if(tagsInsertAffectedRows == tagCount) {
                con.commit();
                con.setAutoCommit(true);
            } else {
                return new TouristAttractionDTO();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return attraction;
    }

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


    private String createAttractionTagsInsertSqlString(int tagCount) {
        String insertAttractionTagsSQL = """
                INSERT INTO attraction_tags VALUES(?,?)""";

        StringBuilder placeholders = new StringBuilder();
        if (tagCount > 1) {
            placeholders.append(",(?,?)".repeat(tagCount - 1));
            insertAttractionTagsSQL += placeholders.toString();
        }
        return insertAttractionTagsSQL;
    }

    private void setTagsPlaceHolderValues(PreparedStatement attractionTagsInsertPs,List<TagDTO> tags,long attraction_id) throws SQLException {
        int tagCount = tags.size();
        for (int i = 0; i < tagCount; i++) {
            int attractionIdParamIndex = 1 + (2 * i); // first iteration 1, then 3,5,7...
            int tagIdParamIndex = 2 + (2 * i);        // first iteration 2, then 4,6,8...
            long tag_id = tags.get(i).getTag_id();

            attractionTagsInsertPs.setLong(attractionIdParamIndex, attraction_id);
            attractionTagsInsertPs.setLong(tagIdParamIndex, tag_id);
        }
    }
    private List<TouristAttractionDTO> mapResultSetToTouristAttractionDtoList(ResultSet rs) throws SQLException {
        List<TouristAttractionDTO> attractions = new ArrayList<>();
        TouristAttractionDTO touristAttractionDTO = null;

        long current = 0;
        while (rs.next()) {

            long attractionID = rs.getLong("attraction_id");
            if (current != attractionID) {
                current = attractionID;
                touristAttractionDTO = new TouristAttractionDTO();

                touristAttractionDTO.setAttractionID(attractionID);
                touristAttractionDTO.setName(rs.getString("attraction.name"));
                touristAttractionDTO.setDescription(rs.getString("description"));

                CityDTO cityDTO = new CityDTO();
                cityDTO.setName(rs.getString("city.name"));
                cityDTO.setCity_id(rs.getLong("city_id"));
                touristAttractionDTO.setCity(cityDTO);

                TagDTO tagDTO = new TagDTO();
                tagDTO.setName(rs.getString("tag.name"));
                tagDTO.setTag_id(rs.getLong("tag_id"));
                touristAttractionDTO.setTags(new ArrayList<>(List.of(tagDTO)));

                attractions.add(touristAttractionDTO);
            } else {
                if(touristAttractionDTO == null) {
                    throw new RuntimeException("touristAttractionDTO is null, when it shouldn't be.");
                }
                TagDTO tagDTO = new TagDTO();
                tagDTO.setName(rs.getString("tag.name"));
                tagDTO.setTag_id(rs.getLong("tag_id"));

                touristAttractionDTO.addTag(tagDTO);
            }
        }
        return attractions;
    }



}
