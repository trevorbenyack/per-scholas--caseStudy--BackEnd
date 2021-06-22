package org.perscholas.caseStudy.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.SimpleDateFormat;

@Configuration
public class Beans {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    // This registers the FasterXML Jackson Datatype Hibernate module which
    // supports "JSON serialization/deserialization of Hibernate datatype and
    // objects, especially lazy-loaded aspects"
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper mapper = converter.getObjectMapper();
        Hibernate5Module hibernate5Module = new Hibernate5Module();
        mapper.registerModule(hibernate5Module);
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return converter;
    }

}
