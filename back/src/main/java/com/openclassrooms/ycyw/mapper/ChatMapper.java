package com.openclassrooms.ycyw.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.openclassrooms.ycyw.dto.ChatDTO;
import com.openclassrooms.ycyw.models.Chat;

@Mapper(componentModel = "spring")
public interface ChatMapper {

    @Mapping(source = "sender.id", target = "senderId")
    ChatDTO toDTO(Chat chat);

    @Mapping(source = "senderId", target = "sender.id")
    Chat toEntity(ChatDTO chatDTO);
}

