package com.openclassrooms.ycyw.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ChatDTO {
    private Integer id;
    private Integer senderId;
    private Integer conversationId;
    private String content;
    private LocalDateTime timestamp;
    private String status;
}
