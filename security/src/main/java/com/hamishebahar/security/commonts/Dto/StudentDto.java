package com.hamishebahar.security.commonts.Dto;

import com.hamishebahar.security.panel.media.entity.Medias;
import com.hamishebahar.security.panel.students.entity.Students;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Long id;

    private String studentCode; //کد تحصیلی
    private String first_name;
    private String last_name;
    private String nationalCode;
    private String phoneNumber;
    private String emergencyPhoneNumber;
    private String specialDisease;
    private String importantDetails;
    private String studentAge;

    //Parents
    private List<UsersDto> studentParents;//todo

    //period
    // دوره تحصیلی - دبستان - پیش دبستان
    private List<PeriodsDto> studentPeriods;//todo

    //Media
    //عکس دانش آموز
    //کارت ملی
    //شناسنامه
    private List<MediasDto> documentFiles;

    private Boolean is_active = true;
    private Boolean is_deleted = false;

    private String createdAt;
    private String updatedAt;

    public static class Builder {
        private Long id;

        private String studentCode; //کد تحصیلی
        private String first_name;
        private String last_name;
        private String nationalCode;
        private String phoneNumber;
        private String emergencyPhoneNumber;

        private String studentAge;
        private String specialDisease;
        private String importantDetails;

        //Parents
        private List<UsersDto> studentParents;

        //period
        // دوره تحصیلی - دبستان - پیش دبستان
        private List<PeriodsDto> studentPeriods;

        //Media
        //عکس دانش آموز
        //کارت ملی
        //شناسنامه
        private List<MediasDto> documentFiles;

        private Boolean is_active;
        private Boolean is_deleted;

        private String createdAt;
        private String updatedAt;

        public Builder Id(Long id) {
            this.id = id;
            return this;
        }

        public Builder StudentCode(String studentCode) {
            this.studentCode = studentCode;
            return this;
        }

        public Builder First_name(String first_name) {
            this.first_name = first_name;
            return this;
        }

        public Builder Last_name(String last_name) {
            this.last_name = last_name;
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

        public Builder EmergencyPhoneNumber(String emergencyPhoneNumber) {
            this.emergencyPhoneNumber = emergencyPhoneNumber;
            return this;
        }

        public Builder StudentAge(String studentAge) {
            this.studentAge = studentAge;
            return this;
        }

        public Builder SpecialDisease(String specialDisease) {
            this.specialDisease = specialDisease;
            return this;
        }

        public Builder ImportantDetails(String importantDetails) {
            this.importantDetails = importantDetails;
            return this;
        }

        public Builder StudentParents(List<UsersDto> studentParents) {
            this.studentParents = studentParents;
            return this;
        }

        public Builder StudentPeriods(List<PeriodsDto> studentPeriods) {
            this.studentPeriods = studentPeriods;
            return this;
        }

        public Builder DocumentFiles(List<MediasDto> documentFiles) {
            this.documentFiles = documentFiles;
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

        public Builder CreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder UpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public StudentDto build() {
            return new StudentDto(this);
        }
    }

    public StudentDto(Builder builder){
        this.id = builder.id;
        this.studentCode = builder.studentCode;
        this.first_name = builder.first_name;
        this.last_name = builder.last_name;
        this.nationalCode = builder.nationalCode;
        this.phoneNumber = builder.phoneNumber;
        this.emergencyPhoneNumber = builder.emergencyPhoneNumber;
        this.studentAge = builder.studentAge;
        this.specialDisease = builder.specialDisease;
        this.importantDetails =  builder.importantDetails;
        this.studentParents = builder.studentParents;
        this.studentPeriods = builder.studentPeriods;
        this.documentFiles = builder.documentFiles;
        this.is_active = builder.is_active;
        this.is_deleted = builder.is_deleted;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public Students convertToEntity() {
        return new Students.Builder()
                .Id(getId() != null ? getId() : null)
                .StudentCode(getStudentCode() != null ? getStudentCode() : null)
                .First_name(getFirst_name() != null ? getFirst_name() : null)
                .Last_name(getLast_name() != null ? getLast_name() : null)
                .NationalCode(getNationalCode() != null ? getNationalCode() : null)
                .PhoneNumber(getPhoneNumber() != null ? getPhoneNumber() : null)
                .EmergencyPhoneNumber(getEmergencyPhoneNumber() != null ? getEmergencyPhoneNumber() : null)
                .StudentAge(getStudentAge() != null ? getStudentAge() : null)
                .SpecialDisease(getSpecialDisease() != null ? getSpecialDisease() : null)
                .ImportantDetails(getImportantDetails() != null ? getImportantDetails() : null)
                .StudentParents(getStudentParents() != null ? getStudentParents().stream()
                        .map(UsersDto::convertToEntity)
                        .collect(Collectors.toList()) : null)
                .StudentPeriods(getStudentPeriods() != null ? getStudentPeriods().stream()
                        .map(PeriodsDto::convertToEntity)
                        .collect(Collectors.toList()) : null)
                .DocumentFiles(getDocumentFiles() != null ? getDocumentFiles().stream()
                        .map(MediasDto::convertToEntity)
                        .collect(Collectors.toList()) : null)
                .Is_active(getIs_active() != null ? getIs_active() : true)
                .Is_deleted(getIs_deleted() != null ? getIs_deleted() : false)
                .build();
    }

    public StudentDto updaterFields(StudentDto dto){
        return new StudentDto.Builder()
                .Id(getId() != null ? getId() : dto.getId())
                .StudentCode(getStudentCode() != null ? getStudentCode() : dto.getStudentCode())
                .First_name(getFirst_name() != null ? getFirst_name() : dto.getFirst_name())
                .Last_name(getLast_name() != null ? getLast_name() : dto.getLast_name())
                .NationalCode(getNationalCode() != null ? getNationalCode() : dto.getNationalCode())
                .PhoneNumber(getPhoneNumber() != null ? getPhoneNumber() : dto.getPhoneNumber())
                .EmergencyPhoneNumber(getEmergencyPhoneNumber() != null ?
                        getEmergencyPhoneNumber() : dto.getEmergencyPhoneNumber())
                .StudentAge(getStudentAge() != null ? getStudentAge() : dto.getStudentAge())
                .SpecialDisease(getSpecialDisease() != null ? getSpecialDisease() : null)
                .ImportantDetails(getImportantDetails() != null ? getImportantDetails() : null)
                .StudentParents(getStudentParents() != null ? getStudentParents() : dto.getStudentParents())
                .StudentPeriods(getStudentPeriods() != null ? getStudentPeriods() : dto.getStudentPeriods())
                .DocumentFiles(getDocumentFiles() != null ? getDocumentFiles() : dto.getDocumentFiles())
                .Is_active(getIs_active() != null &&
                        (getIs_active() == dto.getIs_active()) ? getIs_active() : dto.getIs_active())
                .Is_deleted(getIs_deleted() != null &&
                        (getIs_deleted() == dto.getIs_deleted()) ? getIs_deleted() : dto.getIs_deleted())
                .build();
    }
}
