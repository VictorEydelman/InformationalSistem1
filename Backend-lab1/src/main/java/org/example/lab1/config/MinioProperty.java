package org.example.lab1.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MinioProperty {
    private String port="9000";
    private String accessKey="FdHOvDBBA0xzKJFu9dVy";
    private final String secretKey="owjlHmxJJ9Ada4e36sTr59q1cCDya1IxyRxYIiqu";
    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder().endpoint("http://localhost:"+port)
                .credentials(accessKey, secretKey).build();
    }
    public ObjectMapper objectMapper(){
        var objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
        return objectMapper;
    }
}
