package com.example.managercourse.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassRoomRequest {

    @NotBlank(message = "Code class room is not required")
    private String codeClassRoom;

    @NotBlank(message = "class room name is not required")
    private String classRoomName;

    @NotNull(message = "Type class room is not required")
    private Integer typeClassRoom;

    @NotNull(message = "Quantity student is not required")
    private Integer quantityStudent;

    @NotNull(message = "Floor is not required")
    private Integer floor;

    @NotNull(message = "Status is not required")
    private Integer status;

}
