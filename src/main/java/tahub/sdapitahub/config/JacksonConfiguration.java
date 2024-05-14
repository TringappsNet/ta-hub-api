package tahub.sdapitahub.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import tahub.sdapitahub.serializer.LocalDateTimeSerializer;

import java.time.LocalDateTime;

@Configuration
public class JacksonConfiguration {
    @Bean
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        objectMapper.findAndRegisterModules();
        objectMapper.registerModule(new SimpleModule().addSerializer(LocalDateTime.class, new LocalDateTimeSerializer()));
        return objectMapper;
    }
}
