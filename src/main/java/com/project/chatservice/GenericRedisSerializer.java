package com.project.chatservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.project.chatservice.dto.WsConnection;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.IOException;

//CUSTOM REDIS SERIALIZER CLASS TO HANDLE LOCAL DATE TIME
@Data
public class GenericRedisSerializer<T> implements RedisSerializer<T> {

    private ObjectMapper objectMapper;

    public GenericRedisSerializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }

    @Override
    public byte[] serialize(T obj) throws SerializationException {
        try {
            return objectMapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            return objectMapper.readValue(bytes, objectMapper.constructType(Object.class));
        } catch (IOException e) {
            e.printStackTrace();
            throw new SerializationException("Error deserializing LocalDateTime", e);
        }
    }
}
