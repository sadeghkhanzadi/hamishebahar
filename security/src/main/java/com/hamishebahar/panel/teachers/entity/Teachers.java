package com.hamishebahar.panel.teachers.entity;

import com.commonts.Dto.MediasDto;
import com.commonts.Dto.TeacherDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hamishebahar.panel.courses.entity.Courses;
import com.hamishebahar.panel.media.entity.Medias;
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
public class Teachers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teacherCode;
    private String firstName;
    private String lastName;
    private String age;
    private String workExperience; // تعداد سال سابقه کاری
    private String jobTitle;
    private String nationalCode;
    private String phoneNumber;
    private Boolean is_active;
    private Boolean is_deleted;
    @ManyToMany
    private List<Medias> documentFiles; //فایل های رزومه و عکس و کارت ملی و ...
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
    
    public static class Builder{
        private Long id;
        private String teacherCode;
        private String firstName;
        private String lastName;
        private String age;
        private String workExperience; // تعداد سال سابقه کاری
        private String jobTitle;
        private String nationalCode;
        private String phoneNumber;
        private Boolean is_active;
        private Boolean is_deleted;
        private List<Medias> documentFiles; //فایل های رزومه و عکس و کارت ملی و ...
        private List<Courses> courses; // لیست دوره های هر مدرس

        public Builder Id(Long id) {
            this.id = id;
            return this;
        }

        public Builder TeacherCode(String teacherCode) {
            this.teacherCode = teacherCode;
            return this;
        }

        public Builder FirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder LastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder Age(String age) {
            this.age = age;
            return this;
        }

        public Builder WorkExperience(String workExperience) {
            this.workExperience = workExperience;
            return this;
        }

        public Builder JobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
            return this;
        }

        public Builder NationalCode(String nationalCode) {
            this.nationalCode = nationalCode;
            return this;
        }

        public Builder PhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
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

        public Builder DocumentFiles(List<Medias> documentFiles) {
            this.documentFiles = documentFiles;
            return this;
        }

        public Builder Courses(List<Courses> courses) {
            this.courses = courses;
            return this;
        }

        public Teachers build(){
            return new Teachers(this);
        }
    }

    private Teachers(Builder builder){
        this.id = builder.id;
        this.teacherCode = builder.teacherCode;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.workExperience = builder.workExperience;
        this.jobTitle = builder.jobTitle;
        this.nationalCode = builder.nationalCode;
        this.phoneNumber = builder.phoneNumber;
        this.is_active = builder.is_active;
        this.is_deleted = builder.is_deleted;
        this.documentFiles = builder.documentFiles;
        this.courses = builder.courses;
    }

    public TeacherDto convertToDto(){
        return new TeacherDto.Builder()
                .Id(getId() != null ? getId() : null)
                .TeacherCode(getTeacherCode()!= null ? getTeacherCode() : null)
                .FirstName(getFirstName())
                .LastName(getLastName())
                .Age(getAge())
                .WorkExperience(getWorkExperience())
                .JobTitle(getJobTitle())
                .NationalCode(getNationalCode())
                .PhoneNumber(getPhoneNumber())
                .Is_active(getIs_active() != null ? getIs_active() : true)
                .Is_deleted(getIs_deleted() != null ? getIs_deleted() : false)
                .DocumentFiles(getDocumentFiles() != null ? getDocumentFiles().stream()
                        .map(Medias::convertToDto)
                        .collect(Collectors.toList()) : null)
                .Courses(getCourses() != null ? getCourses().stream()
                        .map(Courses::convertToDto)
                        .collect(Collectors.toList()) : null)
                .CreatedAt(getCreatedAt() != null ? getCreatedAt().
                        format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)) : null)
                .UpdatedAt(getUpdatedAt() != null ? getUpdatedAt()
                        .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)) : null)
                .build();
    }

}
