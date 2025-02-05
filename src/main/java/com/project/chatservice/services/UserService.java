package com.project.chatservice.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.chatservice.dto.UserEntityDTO;
import com.project.chatservice.dto.UserEntityCDC;
import com.project.chatservice.entities.UserEntity;
import com.project.chatservice.repositories.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Data
public class UserService {
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;


    @Transactional
    public void syncUserEntity(String message) throws JsonProcessingException {
        UserEntityCDC userDTO = objectMapper.convertValue(message, UserEntityCDC.class);
        UserEntity userEntity = UserEntity
                .builder()
                .username(userDTO.getUSERNAME())
                .email(userDTO.getEMAIL())
                .userEntityId(userDTO.getID())
                .profilePicture(userDTO.getPROFILE_PICTURE())
                .phone(userDTO.getPHONE())
                .build();
        userRepository.save(userEntity);
    }

    @Transactional
    public List<UserEntityDTO> getUsers() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserEntityDTO> userDTOs = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            UserEntityDTO userDTO = UserEntityDTO
                    .builder()
                    .username(userEntity.getUsername())
                    .email(userEntity.getEmail())
                    .userEntityId(userEntity.getUserEntityId())
                    .profilePicture(userEntity.getProfilePicture())
                    .phone(userEntity.getPhone())
                    .build();
            userDTOs.add(userDTO);
        }
        return userDTOs;
    }
}
