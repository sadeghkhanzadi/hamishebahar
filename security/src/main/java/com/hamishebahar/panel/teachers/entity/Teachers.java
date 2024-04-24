package com.hamishebahar.panel.teachers.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hamishebahar.panel.courses.entity.Courses;
import com.hamishebahar.panel.media.entity.Medias;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Teachers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teacherCode;
    private String firstName;
    private String lastName;
    private String age;
    private String workExperience; // تعداد سال سابقه کاری
    @ManyToMany
    private List<Medias> documentFiles; //فایل های رزومه و عکس و کارت ملی و ...
    private String jobTitle;
    private String nationalCode;
    private String phoneNumber;

    private Boolean is_active;
    private Boolean is_deleted;

    @ManyToMany(mappedBy = "courses")
    private List<Courses> courses; // لیست دوره های هر مدرس

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
