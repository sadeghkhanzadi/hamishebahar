package com.hamishebahar.security.commonts.Dto;

import com.hamishebahar.security.panel.aboutUs.entity.AboutUs;
import com.hamishebahar.security.panel.media.entity.Medias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AboutUsDto {
    private Long id;

    private String text;
    private String address;

    private String phoneNumberCompany;
    private String mobileNumber;
    private String emailAddress;

    private Boolean is_active = true;
    private Boolean is_deleted = false;

    private List<MediasDto> medias;

    public static class Builder{
        private Long id;

        private String text;
        private String address;

        private String phoneNumberCompany;
        private String mobileNumber;
        private String emailAddress;

        private Boolean is_active;
        private Boolean is_deleted;

        private List<MediasDto> medias;

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

        public Builder Medias(List<MediasDto> medias) {
            this.medias = medias;
            return this;
        }

        public AboutUsDto build(){
            return new AboutUsDto(this);
        }
    }

    private AboutUsDto(Builder builder) {
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

    public AboutUs convertToEntity(){
        return new AboutUs.Builder()
                .Id(id)
                .Text(text)
                .Address(address)
                .PhoneNumberCompany(phoneNumberCompany)
                .MobileNumber(mobileNumber)
                .EmailAddress(emailAddress)
                .Is_active(is_active)
                .Is_deleted(is_deleted)
                .Medias(medias.stream().map(MediasDto::convertToEntity).collect(Collectors.toList()))
                .build();
    }

    public AboutUsDto updaterFields(AboutUsDto dto) {
        return new AboutUsDto.Builder()
                .Id(id)
                .Text(text)
                .Address(address)
                .PhoneNumberCompany(phoneNumberCompany)
                .MobileNumber(mobileNumber)
                .EmailAddress(emailAddress)
                .Is_active(getIs_active() != null &&
                        (getIs_active() == dto.getIs_active()) ? getIs_active() : dto.getIs_active())
                .Is_deleted(getIs_deleted() != null &&
                        (getIs_deleted() == dto.getIs_deleted()) ? getIs_deleted() : dto.getIs_deleted())
                .Medias(getMedias()!= null ? getMedias() : dto.getMedias())
                .build();
    }
}
