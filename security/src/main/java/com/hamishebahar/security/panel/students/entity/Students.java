package com.hamishebahar.security.panel.students.entity;



import com.hamishebahar.security.commonts.Dto.StudentDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hamishebahar.security.panel.media.entity.Medias;
import com.hamishebahar.security.panel.periods.entity.Periods;
import com.hamishebahar.security.users.entity.Users;
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
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentCode; //کد تحصیلی
    private String first_name;
    private String last_name;
    private String nationalCode;
    private String phoneNumber;
    private String emergencyPhoneNumber;
    private String specialDisease;//بیماری خاص
    private String importantDetails;//ملاحضات

    private String studentAge;
    //Parents
    @ManyToMany
    private List<Users> studentParents;

    //period
    // دوره تحصیلی - دبستان - پیش دبستان
    @ManyToMany
    private List<Periods> studentPeriods;

    //Media
    //عکس دانش آموز
    //کارت ملی
    //شناسنامه
    @ManyToMany
    private List<Medias> documentFiles;

    //todo توانمندی و مهارت ها
    //todo هزینه ها و واریزی ها به ازای هر فرزند باید توی والدین هم اضافه بشه البته باید توی فاکتور کیف پول گزاشته بشه
    //Todo امتیاز و جوایز

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

    public static class Builder {
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
        private List<Users> studentParents;
        private List<Periods> studentPeriods;

        //Media
        //عکس دانش آموز
        //کارت ملی
        //شناسنامه
        @ManyToMany
        private List<Medias> documentFiles;

        //todo توانمندی و مهارت ها
        //todo هزینه ها و واریزی ها به ازای هر فرزند باید توی والدین هم اضافه بشه البته باید توی فاکتور کیف پول گزاشته بشه
        //Todo امتیاز و جوایز

        private Boolean is_active;
        private Boolean is_deleted;

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

        public Builder StudentParents(List<Users> studentParents) {
            this.studentParents = studentParents;
            return this;
        }

        public Builder StudentPeriods(List<Periods> studentPeriods) {
            this.studentPeriods = studentPeriods;
            return this;
        }

        public Builder DocumentFiles(List<Medias> documentFiles) {
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

        public Students build() {
            return new Students(this);
        }
    }

    private Students(Builder builder){
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
    }

    public StudentDto convertToDto() {
        return new StudentDto.Builder()
                .Id(getId() != null ? getId() : null)
                .StudentCode(getStudentCode() != null ? getStudentCode() : null)
                .First_name(getFirst_name() != null ? getFirst_name() : null)
                .Last_name(getLast_name() != null ? getLast_name() : null)
                .NationalCode(getNationalCode() != null ? getNationalCode() : null)
                .PhoneNumber(getPhoneNumber() != null ? getPhoneNumber() : null)
                .EmergencyPhoneNumber(getEmergencyPhoneNumber() != null ?
                        getEmergencyPhoneNumber() : null)
                .StudentAge(getStudentAge() != null ? getStudentAge() : null)
                .SpecialDisease(getSpecialDisease() != null ? getSpecialDisease() : null)
                .ImportantDetails(getImportantDetails() != null ? getImportantDetails() : null)
                .StudentParents(getStudentParents() != null ? getStudentParents().stream()
                        .map(Users::convertToDto)
                        .collect(Collectors.toList()) : null)
                .StudentPeriods(getStudentPeriods() != null ? getStudentPeriods().stream()
                        .map(Periods::convertToDto)
                        .collect(Collectors.toList()) : null)
                .DocumentFiles(getDocumentFiles() != null ? getDocumentFiles().stream()
                        .map(Medias::convertToDto)
                        .collect(Collectors.toList()) : null)
                .Is_active(getIs_active() != null ? getIs_active() : true)
                .Is_deleted(getIs_deleted() != null ? getIs_deleted() : false)
                .CreatedAt(getCreatedAt() != null ? getCreatedAt().toString(): null)
                .UpdatedAt(getUpdatedAt() != null ? getUpdatedAt().toString() : null)
                .build();
    }
}
