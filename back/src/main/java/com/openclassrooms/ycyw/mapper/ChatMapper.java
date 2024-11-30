package com.openclassrooms.ycyw.mapper;

import com.openclassrooms.ycyw.dto.ChatDTO;
import com.openclassrooms.ycyw.models.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ChatMapper {
    @Mapping(source = "sender.id", target = "senderId")
    ChatDTO toDto(Chat chat);

    @Mapping(source = "senderId", target = "sender.id")
    Chat toEntity(ChatDTO chatDTO);
}
