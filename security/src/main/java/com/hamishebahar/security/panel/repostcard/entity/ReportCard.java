package com.hamishebahar.security.panel.repostcard.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hamishebahar.security.panel.media.entity.Medias;
import com.hamishebahar.security.panel.periods.entity.Periods;
import com.hamishebahar.security.panel.students.entity.Students;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ReportCard { //کارنامه
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;

    @ManyToOne
    private Students students;
    @OneToOne
    private Periods periods;
    @OneToOne
    private Medias medias;

    private Boolean is_active;
    private Boolean is_deleted;

    @Column(
            name = "created_time",
            updatable = false
    )
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(
            name = "updated_time",
            updatable = true
    )
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
