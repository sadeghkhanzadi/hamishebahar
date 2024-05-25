package com.hamishebahar.security.commonts.Dto;

import com.hamishebahar.security.panel.courses.entity.Courses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoursesDto {
    private Long id;
    private String courseCode;
    private String title;
    private String text;
    private TeacherDto teachers;//مدرس دوره
    private CategoryDto category;
    private String startTime;
    private String endTime;
    private String coste; //قیمت دوره
    private Boolean is_active = true;
    private Boolean is_deleted = false;
    private List<MediasDto> medias;
    private String createdAt;
    private String updatedAt;

    public static class Builder{
        private Long id;
        private String courseCode;
        private String title;
        private String text;
        private TeacherDto teachers;//مدرس دوره
        private CategoryDto category;
        private String startTime;
        private String endTime;
        private String coste; //قیمت دوره
        private Boolean is_active;
        private Boolean is_deleted;
        private List<MediasDto> medias;
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

        public Builder Teachers(TeacherDto teachers) {
            this.teachers = teachers;
            return this;
        }

        public Builder CategoryDto(CategoryDto category) {
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

        public Builder Medias(List<MediasDto> medias) {
            this.medias = medias;
            return this;
        }

        public Builder CreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder UpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public CoursesDto build(){
            return new CoursesDto(this);
        }
    }
    private CoursesDto(Builder builder) {
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
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public Courses convertToEntity() {
        return new Courses.Builder()
                .Id(id)
                .CourseCode(courseCode)
                .Title(title)
                .Text(text)
                .Teachers(teachers!= null ? teachers.convertToEntity() : null)
                .CourseCategory(category!= null ? category.convertToEntity() : null)
                .StartTime(startTime)
                .EndTime(endTime)
                .Coste(coste)
                .Is_active(getIs_active() != null ? getIs_active() : true)
                .Is_deleted(getIs_deleted() != null ? getIs_deleted() : false)
                .Medias(medias!= null && !medias.isEmpty() ? medias.stream()
                        .map(MediasDto::convertToEntity)
                        .collect(Collectors.toList()) : null)
                .build();
    }

    public CoursesDto updaterFields(CoursesDto dto) {
        return new CoursesDto.Builder()
                .Id(getId() != null ? getId()  : dto.getId())
                .CourseCode(getCourseCode() != null ? getCourseCode() : dto.getCourseCode())
                .Title(getTitle() != null ?  getTitle() : dto.getTitle())
                .Text(getText() != null ? getText() : dto.getText())
                .Teachers(getTeachers() != null ? getTeachers() : dto.getTeachers())
                .CategoryDto(getCategory() != null ? getCategory() : dto.getCategory())
                .StartTime(getStartTime() != null ? getStartTime() : dto.getStartTime())
                .EndTime(getEndTime() != null ? getEndTime() :  dto.getEndTime())
                .Coste(getCoste() != null ? getCoste() : dto.getCoste())
                .Is_active(getIs_active() != null &&
                        (getIs_active() == dto.getIs_active()) ? getIs_active() : dto.getIs_active())
                .Is_deleted(getIs_deleted() != null &&
                        (getIs_deleted() == dto.getIs_deleted()) ? getIs_deleted() : dto.getIs_deleted())
                .Medias(getMedias()!= null && !getMedias().isEmpty() ? getMedias() : dto.getMedias())
                .CreatedAt(getCreatedAt() != null ? getCreatedAt() : dto.getCreatedAt())
                .UpdatedAt(getUpdatedAt() != null ? getUpdatedAt() : dto.getUpdatedAt())
                .build();
    }
}
