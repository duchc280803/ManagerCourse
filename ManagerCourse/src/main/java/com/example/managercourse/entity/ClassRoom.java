package com.example.managercourse.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "class_room")
public class ClassRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String codeClassRoom;

    private String classRoomName;

    private Integer typeClassRoom;

    private Integer quantityStudent;

    private Integer floor;

    private Integer status;

    @OneToMany(mappedBy = "classRoom", fetch = FetchType.LAZY)
    private List<Schedule> scheduleList;

}
