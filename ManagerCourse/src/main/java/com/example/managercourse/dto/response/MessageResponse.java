package com.example.managercourse.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageResponse {

    private String message;

    private String messageUsername;

    private String messageEmail;

    public MessageResponse(String message) {
        this.message = message;
    }
}
