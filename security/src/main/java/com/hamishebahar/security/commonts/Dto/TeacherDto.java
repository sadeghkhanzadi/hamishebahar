package com.hamishebahar.security.commonts.Dto;

import com.hamishebahar.security.panel.teachers.entity.Teachers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
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
    private List<MediasDto> documentFiles; //فایل های رزومه و عکس و کارت ملی و ...
    private String createdAt;
    private String updatedAt;

    public static class Builder {
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
        private List<MediasDto> documentFiles; //فایل های رزومه و عکس و کارت ملی و ...
        private List<CoursesDto> courses; // لیست دوره های هر مدرس
        private String createdAt;
        private String updatedAt;

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

        public Builder DocumentFiles(List<MediasDto> documentFiles) {
            this.documentFiles = documentFiles;
            return this;
        }

        public Builder Courses(List<CoursesDto> courses) {
            this.courses = courses;
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

        public TeacherDto build() {
            return new TeacherDto(this);
        }
    }

    private TeacherDto(Builder builder) {
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
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public Teachers convertToEntity() {
        return new Teachers.Builder()
                .Id(getId() != null ? getId() : null)
                .TeacherCode(getTeacherCode() != null ? getTeacherCode() : null)
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
                        .map(MediasDto::convertToEntity)
                        .collect(Collectors.toList()) : null)
                .build();
    }

    public TeacherDto updaterFields(TeacherDto dto) {
        return new TeacherDto.Builder()
                .Id(getId() != null ? getId() : dto.getId())
                .TeacherCode(getTeacherCode() != null ? getTeacherCode() : dto.getTeacherCode())
                .FirstName(getFirstName() != null ? getFirstName() : dto.getFirstName())
                .LastName(getLastName() != null ? getLastName() : dto.getLastName())
                .Age(getAge() != null ? getAge() : dto.getAge())
                .WorkExperience(getWorkExperience() != null ? getWorkExperience() : dto.getWorkExperience())
                .JobTitle(getJobTitle() != null ?  getJobTitle() : dto.getJobTitle())
                .NationalCode(getNationalCode() != null ? getNationalCode() : dto.getNationalCode())
                .PhoneNumber(getPhoneNumber() != null ? getPhoneNumber() : dto.getPhoneNumber())
                .Is_active(getIs_active() != null &&
                        (getIs_active() == dto.getIs_active()) ? getIs_active() : dto.getIs_active())
                .Is_deleted(getIs_deleted() != null &&
                        (getIs_deleted() == dto.getIs_deleted()) ? getIs_deleted() : dto.getIs_deleted())
                .DocumentFiles(getDocumentFiles() != null ? getDocumentFiles() : dto.getDocumentFiles())
                .build();
    }
}
