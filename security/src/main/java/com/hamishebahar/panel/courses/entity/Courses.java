package com.hamishebahar.panel.courses.entity;

import com.commonts.Dto.CoursesDto;
import com.commonts.Dto.MediasDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hamishebahar.panel.category.entity.CourseCategory;
import com.hamishebahar.panel.media.entity.Medias;
import com.hamishebahar.panel.teachers.entity.Teachers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    @OneToOne
    private CourseCategory category;//دسته بندی
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


    public static class Builder{
        private Long id;
        private String courseCode;
        private String title;
        private String text;
        private Teachers teachers;//مدرس دوره
        private CourseCategory category;
        private String startTime;
        private String endTime;
        private String coste; //قیمت دوره
        private Boolean is_active;
        private Boolean is_deleted;
        private List<Medias> medias;
        private String createdAt;
        private String updatedAt;

        public Builder Id(Long id) {
            this.id = id;
            return this;
        }

        public Builder CourseCode(String courseCode) {
            this.courseCode = courseCode;
            return this;
        }

        public Builder Title(String title) {
            this.title = title;
            return this;
        }

        public Builder Text(String text) {
            this.text = text;
            return this;
        }

        public Builder Teachers(Teachers teachers) {
            this.teachers = teachers;
            return this;
        }

        public Builder CourseCategory(CourseCategory category) {
            this.category = category;
            return this;
        }

        public Builder StartTime(String startTime) {
            this.startTime = startTime;
            return this;
        }

        public Builder EndTime(String endTime) {
            this.endTime = endTime;
            return this;
        }

        public Builder Coste(String coste) {
            this.coste = coste;
            return this;
        }

        public Builder Is_active(Boolean is_active) {
            this.is_active = is_active;
            return this;
        }

        public Builder Is_deleted(Boolean is_deleted) {
            this.is_deleted = is_deleted;
            return this;
        }

        public Builder Medias(List<Medias> medias) {
            this.medias = medias;
            return this;
        }

        public Courses build(){
            return new Courses(this);
        }
    }

    public Courses(Builder builder) {
        this.id = builder.id;
        this.courseCode = builder.courseCode;
        this.title = builder.title;
        this.text = builder.text;
        this.teachers = builder.teachers;
        this.category = builder.category;
        this.startTime = builder.startTime;
        this.endTime = builder.endTime;
        this.coste = builder.coste;
        this.is_active = builder.is_active;
        this.is_deleted = builder.is_deleted;
        this.medias = builder.medias;
    }

    public CoursesDto convertToDto() {
        return new CoursesDto.Builder()
                .Id(id)
                .CourseCode(courseCode)
                .Title(title)
                .Text(text)
                .Teachers(teachers!= null ? teachers.convertToDto() : null)
                .CategoryDto(category!= null ? category.convertToDto() : null)
                .StartTime(startTime)
                .EndTime(endTime)
                .Coste(coste)
                .Is_active(getIs_active() != null ? getIs_active() : true)
                .Is_deleted(getIs_deleted() != null ? getIs_deleted() : false)
                .Medias(medias!= null && !medias.isEmpty() ? medias.stream()
                        .map(Medias::convertToDto)
                        .collect(Collectors.toList()) : null)
                .CreatedAt(getCreatedAt() != null ? getCreatedAt().
                        format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)) : null)
                .UpdatedAt(getUpdatedAt() != null ? getUpdatedAt()
                        .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)) : null)
                .build();
    }
}
