package com.example.managercourse.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "status_subject")
public class StatusSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String subjectEnum;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;
}
