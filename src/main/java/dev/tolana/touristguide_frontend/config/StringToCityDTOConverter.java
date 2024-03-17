package dev.tolana.touristguide_frontend.config;

import dev.tolana.touristguide_frontend.dto.CityDTO;
import dev.tolana.touristguide_frontend.dto.TagDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class StringToCityDTOConverter implements Converter<String, CityDTO> {
    @Override
    public CityDTO convert(String source) {
//        System.out.println("###### SOURCE : " + source);
//        System.out.println("###### SOURCE.type : " + source.getClass().getName());
        CityDTO cityDTO = new CityDTO();
        if(source.isEmpty() || source.isBlank()) {
            return cityDTO;
        }
        String[] values = source.split(";");
//        System.out.println("########### VALUES: "+values);
        String name = values[0];
        long city_id = Long.parseLong(values[1]);
        cityDTO.setName(name);
        cityDTO.setCity_id(city_id);
        return cityDTO;
    }
}
