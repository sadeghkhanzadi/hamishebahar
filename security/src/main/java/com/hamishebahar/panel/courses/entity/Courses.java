package com.hamishebahar.panel.courses.entity;

import com.commonts.Dto.CoursesDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hamishebahar.panel.media.entity.Medias;
import com.hamishebahar.panel.teachers.entity.Teachers;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Courses {//لیست آموزش ها
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseCode;
    private String title;
    private String text;
    @ManyToOne
    private Teachers teachers;//مدرس دوره
    private String startTime;
    private String endTime;
    private String coste; //قیمت دوره
    private Boolean is_active;
    private Boolean is_deleted;

    @ManyToMany
    private List<Medias> medias;

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


    public static CoursesDto convertToDto(Medias medias) {
        return new CoursesDto();
    }
}
