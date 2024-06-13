package com.hamishebahar.security.panel.aboutUs.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hamishebahar.security.commonts.Dto.AboutUsDto;
import com.hamishebahar.security.commonts.Dto.MediasDto;
import com.hamishebahar.security.panel.media.entity.Medias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class AboutUs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;
    private String address;

    private String phoneNumberCompany;
    private String mobileNumber;
    private String emailAddress;

    private Boolean is_active = true;
    private Boolean is_deleted = false;

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

        private String text;
        private String address;

        private String phoneNumberCompany;
        private String mobileNumber;
        private String emailAddress;

        private Boolean is_active;
        private Boolean is_deleted;

        private List<Medias> medias;

        public Builder Id(Long id) {
            this.id = id;
            return this;
        }

        public Builder Text(String text) {
            this.text = text;
            return this;
        }

        public Builder Address(String address) {
            this.address = address;
            return this;
        }

        public Builder PhoneNumberCompany(String phoneNumberCompany) {
            this.phoneNumberCompany = phoneNumberCompany;
            return this;
        }

        public Builder MobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public Builder EmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
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

        public AboutUs build(){
            return new AboutUs(this);
        }
    }

    private AboutUs(Builder builder) {
        this.id = builder.id;
        this.text = builder.text;
        this.address = builder.address;
        this.phoneNumberCompany = builder.phoneNumberCompany;
        this.mobileNumber = builder.mobileNumber;
        this.emailAddress = builder.emailAddress;
        this.is_active = builder.is_active;
        this.is_deleted = builder.is_deleted;
        this.medias = builder.medias;
    }

    public AboutUsDto convertToDto(){
        return new AboutUsDto.Builder()
                .Id(id)
                .Text(text)
                .Address(address)
                .PhoneNumberCompany(phoneNumberCompany)
                .MobileNumber(mobileNumber)
                .EmailAddress(emailAddress)
                .Is_active(is_active)
                .Is_deleted(is_deleted)
                .Medias(medias.stream().map(Medias::convertToDto).collect(Collectors.toList()))
                .build();
    }
}
