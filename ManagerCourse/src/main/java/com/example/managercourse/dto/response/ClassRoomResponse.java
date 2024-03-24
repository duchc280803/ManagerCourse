package com.example.managercourse.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassRoomResponse {

    private Integer id;

    private String codeClassRoom;

    private String classRoomName;

    private Integer typeClassRoom;

    private Integer quantityStudent;

    private Integer floor;

    private Integer status;

    public ClassRoomResponse(Integer id, String classRoomName) {
        this.id = id;
        this.classRoomName = classRoomName;
    }
}
