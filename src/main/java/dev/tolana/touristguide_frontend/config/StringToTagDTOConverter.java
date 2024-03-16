package dev.tolana.touristguide_frontend.config;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import dev.tolana.touristguide_frontend.dto.TagDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToTagDTOConverter implements Converter<String, TagDTO> {

    @Override
    public TagDTO convert(String source) {
        System.out.println("#### TAG SOURCE: " + source);
        TagDTO tagDTO = new TagDTO();
        String[] values = source.split(";");
        String name = values[0];
        long tag_id = Long.parseLong(values[1]);
        tagDTO.setName(name);
        tagDTO.setTag_id(tag_id);
        return tagDTO;
    }
}
